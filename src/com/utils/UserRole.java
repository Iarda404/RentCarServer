package com.utils;

/**
 * The {@code UserRole} enum class represents the roles of users, which can be either "User" or "Admin".
 */

public enum UserRole {
    USER("User"),
    ADMIN("Admin");

    private final String role;

    /**
     * Constructs a new {@code UserRole} enum with the specified role.
     *
     * @param role the role value
     */
    UserRole(String role){
        this.role = role;
    }

    /**
     * Getter for role value.
     *
     * @return the role value
     */
    public String getRole(){
        return this.role;
    }
}