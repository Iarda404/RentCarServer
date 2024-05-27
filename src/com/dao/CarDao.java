package com.dao;

import com.models.Car;
import com.utils.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code CarDao} class provides methods for interacting with the "car" table in the database.
 * <p>
 * It follows the singleton pattern where one object of this class corresponds to one table in the database.
 * </p>
 */
public class CarDao {
    private static final String SAVE_SQL;
    private static final String FIND_All_AVAILABLE_SQL;
    private static final CarDao INSTANCE;

    static {
        INSTANCE = new CarDao();
        SAVE_SQL = "INSERT INTO \"car\" (model,status_available) VALUES (?,?)";
        FIND_All_AVAILABLE_SQL = "SELECT * FROM \"car\" WHERE status_available = true";
    }

    private CarDao(){}

    /**
     * Retrieves the singleton instance of the {@code CarDao} class.
     *
     * @return the singleton instance of {@code CarDao}
     */
    public static CarDao getInstance(){
        return INSTANCE;
    }

    /**
     * Saves a car to the database.
     *
     * @param car the car to be saved
     * @throws RuntimeException
     */
    public void saveCar(Car car){
        try(Connection connection  = ConnectionPoolManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL)){

            connection.setSchema("public");
            statement.setString(1, car.getModel());
            statement.setBoolean(2, car.getStatusAvailable());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds all available cars in the database.
     *
     * @return a list of available {@code Car} objects
     * @throws RuntimeException
     */
    public List<Car> findAllAvailableCars(){
        List<Car> availableCars = new ArrayList<>();

        try(Connection connection  = ConnectionPoolManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_All_AVAILABLE_SQL)){

            connection.setSchema("public");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Car car = new Car(
                        resultSet.getInt("id_car"),
                        resultSet.getString("model"),
                        resultSet.getBoolean("status_available")
                );
                availableCars.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return availableCars;
    }
}