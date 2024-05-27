package com.dao;

import com.models.User;
import com.utils.ConnectionPoolManager;
import com.utils.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * The {@code UserDao} class provides methods for interacting with the "user" table in the database.
 * <p>
 * It follows the singleton pattern where one object of this class corresponds to one table in the database.
 * </p>
 */
public class UserDao {
    private static final String SAVE_SQL;
    private static final String FIND_SQL;
    private static final UserDao INSTANCE;

    static {
        INSTANCE = new UserDao();
        SAVE_SQL = "INSERT INTO \"user\" (username,password,role) VALUES (?,?,?)";
        FIND_SQL = "SELECT * FROM \"user\" WHERE username = ?";
    }

    private UserDao(){}

    /**
     * Retrieves the singleton instance of the {@code UserDao} class.
     *
     * @return the singleton instance of {@code UserDao}
     */
    public static UserDao getInstance(){
        return INSTANCE;
    }

    /**
     * Saves a user to the database.
     *
     * @param user the user to be saved
     * @throws RuntimeException
     */
    public void saveUser(User user){
        try(Connection connection  = ConnectionPoolManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL)){

            connection.setSchema("public");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().getRole());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds a user in the database by username.
     *
     * @param username the username of the user to find
     * @return an {@code Optional<User>} containing the found user, or {@code Optional.empty()} if not found
     * @throws RuntimeException
     */
    public Optional<User> findUserByUsername(String username){
        try(Connection connection  = ConnectionPoolManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_SQL)){

            connection.setSchema("public");
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    return Optional.of(
                      new User(
                              resultSet.getInt("id_user"),
                              resultSet.getString("username"),
                              resultSet.getString("password"),
                              UserRole.valueOf(
                                      resultSet.getString("role").
                                      toUpperCase()
                              )
                      )
                    );
                } else {
                    return Optional.empty();
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}