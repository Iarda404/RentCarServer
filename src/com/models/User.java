package com.models;

import lombok.Getter;
import lombok.Setter;

import com.utils.UserRole;
import java.util.Objects;

/**
 * The {@code User} class represents a user with an identifier, username, password, and role.
 * Class includes overload constructors for creating user objects, and overrides {@code equals}, {@code hashCode},
 * and {@code toString} methods from the {@code Object} class.
 * <p>
 * Lombok annotations {@code @Getter} and {@code @Setter} are used to generate getter and setter
 * methods for the attributes.
 * </p>
 */

@Getter
@Setter
public class User {
    private Integer id_user;
    private String username;
    private String password;
    private UserRole role;

    /**
     * Constructs a new {@code User} object with the specified identifier, username, password, and role.
     *
     * @param id_user the identifier of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param role the role of the user
     */
    public User(Integer id_user, String username, String password, UserRole role){
        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Constructs a new {@code User} object with the specified username, password, and role.
     * The identifier is set to {@code null}.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param role the role of the user
     */
    public User(String username, String password, UserRole role){
        this(null,username, password, role);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Overrides the {@code equals} method from the {@code Object} class.
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this object is the same as the obj argument, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        return Objects.equals(this, obj);
    }

    /**
     * Returns a hashcode value for the object.
     * Overrides the {@code hashCode} method from the {@code Object} class.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode(){
        return Objects.hash(
                this.id_user,
                this.username,
                this.password,
                this.role);
    }

    /**
     * Returns a string representation of the user.
     * Overrides the {@code toString} method from the {@code Object} class.
     *
     * @return a string representation of the user
     */
    @Override
    public String toString(){
        return "User id: " + this.id_user
                + ", Username: " + this.username
                + ", Role: " + this.role;
    }
}