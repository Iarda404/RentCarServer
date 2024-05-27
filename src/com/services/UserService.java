package com.services;

import com.dao.UserDao;
import com.models.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Optional;

/**
 * The {@code UserService} class provides methods for managing user-related operations.
 * <p>
 * It includes functionality to register new users with password hashing and find users by username.
 * </p>
 */

public class UserService {
    private static final int MIN_LENGTH_PASSWORD;

    static {
        MIN_LENGTH_PASSWORD = 8;
    }

    private final UserDao userDao = UserDao.getInstance();

    /**
     * Validates the password.
     *
     * @param password the password to validate
     * @return {@code true} if the password is valid, {@code false} otherwise
     */
    private boolean validatePassword(String password){
        // Validation logic:
        return password.length() >= MIN_LENGTH_PASSWORD;
    }

    /**
     * Hashes the password using SHA-256 algorithm.
     *
     * @param password the password to hash
     * @return the hashed password
     */
    private String hashPassword(String password){
        return DigestUtils.sha256Hex(password);
    }

    /**
     * Registers a new user with password hashing.
     *
     * @param user the user to register
     * @throws RuntimeException if the password is not valid
     */
    public void registerNewUser(User user){
        if(this.validatePassword(user.getPassword())){
            // Password hashing before saving to DB:
            String hashPassword = this.hashPassword(user.getPassword());
            user.setPassword(hashPassword);
            this.userDao.saveUser(user);
        } else {
            throw new RuntimeException("Password is not valid");
        }
    }

    /**
     * Finds a user by username.
     *
     * @param user the user object containing the username
     * @return an {@code Optional<User>} containing the found user, or {@code Optional.empty()} if not found
     */
    public Optional<User> findByUsername(User user){
        return this.userDao.findUserByUsername(user.getUsername());
    }
}