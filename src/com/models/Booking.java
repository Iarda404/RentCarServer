package com.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The {@code Booking} class represents a booking with an identifier, user ID, car ID, start date, and end date.
 * Class includes override constructors for creating booking objects, and overrides {@code equals}, {@code hashCode},
 * and {@code toString} methods from the {@code Object} class.
 * <p>
 * Lombok annotations {@code @Getter} and {@code @Setter} are used to generate getter and setter
 * methods for the attributes.
 * </p>
 */

@Getter
@Setter
public class Booking {
    private Integer id_booking;
    private int idUser;
    private int idCar;
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructs a new {@code Booking} object with the specified identifier, user ID, car ID, start date, and end date.
     *
     * @param id_booking the identifier of the booking
     * @param idUser the identifier of the user
     * @param idCar the identifier of the car
     * @param fromDate the start date of the booking
     * @param toDate the end date of the booking
     */
    public Booking(Integer id_booking, int idUser, int idCar, LocalDate fromDate, LocalDate toDate){
        this.id_booking = id_booking;
        this.idUser = idUser;
        this.idCar = idCar;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Constructs a new {@code Booking} object with the specified user ID, car ID, start date, and end date.
     * The identifier is set to {@code null}.
     *
     * @param idUser the identifier of the user
     * @param idCar the identifier of the car
     * @param fromDate the start date of the booking
     * @param toDate the end date of the booking
     */
    public Booking(int idUser, int idCar, LocalDate fromDate, LocalDate toDate){
        this(null,idUser,idCar,fromDate, toDate);
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
                this.id_booking,
                this.idUser,
                this.idCar,
                this.fromDate,
                this.toDate
        );
    }

    /**
     * Returns a string representation of the user.
     * Overrides the {@code toString} method from the {@code Object} class.
     *
     * @return a string representation of the user
     */
    @Override
    public String toString(){
        return "Booking id: " + this.id_booking
                + ", Car id: " + this.idCar
                + ", User id: " + this.idUser
                + ", from: " + this.fromDate
                + ", to: " + this.toDate;
    }
}