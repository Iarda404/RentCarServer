package com.utils;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * The {@code ConnectionPoolManager} class is a utility class for managing a connection pool.
 * It initializes a pool of database connections based on the properties specified in the
 * "application.properties" file and provides methods to get and close connections.
 * <p>
 * The class uses {@code Proxy} to wrap each connection object, intercepting the {@code close} method
 * to return the connection back to the pool instead of closing it.
 * </p>
 */

public final class ConnectionPoolManager {
    private final static Queue<Connection> CONNECTION_POOL;
    private final static List<Connection> CONNECTION_LIST;

    private static final String URL = PropertiesManager.getProperty("db.url");
    private static final String USERNAME = PropertiesManager.getProperty("db.username");
    private static final String PASSWORD = PropertiesManager.getProperty("db.password");
    private static final int POOL_SIZE =
            Integer.parseInt(
                    PropertiesManager.getProperty("connection.pool_size")
            );;

    static {
        CONNECTION_POOL = new ArrayBlockingQueue<>(POOL_SIZE); // Thread safe concurrent collection
        CONNECTION_LIST = new ArrayList<>(POOL_SIZE); // List to close connection pool

        // Initialization org.postgresql.Driver for web archive (WAR)
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        openConnectionPool();
    }

    /**
     * Utility class means private constructor.
     * */
    private ConnectionPoolManager(){}

    /**
     * Method to open connection pool.
     * It uses {@code Proxy} to wrap each connection object, intercepting the {@code close} method
     * to return the connection back to the pool instead of closing it.
     *
     * @throws RuntimeException
     * */
    private static void openConnectionPool(){
        for(int i = 0;i < POOL_SIZE;i++){
            try {
            Connection connection = openConnection();

            Connection proxyConnection = (Connection)
                    Proxy.newProxyInstance(
                    ConnectionPoolManager.class.getClassLoader(),
                    new Class<?>[]{Connection.class},
                    (proxy, method, args)
                        -> method.getName().equals("close")
                        ? CONNECTION_POOL.add((Connection) proxy)
                        : method.invoke(connection, args)
            );

            ((ArrayBlockingQueue<Connection>) CONNECTION_POOL).put(proxyConnection);
            CONNECTION_LIST.add(connection);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Opens a new connection to the database.
     * <p>
     * This method creates a new database connection using the URL, username, and password
     * retrieved from the application properties file.
     * </p>
     *
     * @return a new {@code Connection} object to the database
     * @throws RuntimeException
     */
    private static Connection openConnection(){
        Connection connection;
        try {
            connection =
                    DriverManager.getConnection(
                    URL, USERNAME, PASSWORD
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * Takes a connection from the connection pool.
     * <p>
     * This method takes a connection from the pool and returns it to the caller.
     * If no connection is available in the pool, this method will block until a connection becomes available.
     * </p>
     *
     * @return a {@code Connection} object from the connection pool
     * @throws RuntimeException
     */
    public static Connection getConnection(){
        try {
            return ((ArrayBlockingQueue<Connection>) CONNECTION_POOL).take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the connection pool.
     * <p>
     * This method iterates through all connections in the pool and closes them.
     * Any exception encountered during the closing process is wrapped in a {@code RuntimeException}.
     * </p>
     *
     * @throws RuntimeException
     */
    public static void closePool(){
        CONNECTION_LIST.forEach(
                connection -> {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}