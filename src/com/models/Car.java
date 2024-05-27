package com.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * The {@code Car} class represents a car with an identifier, model, and availability status.
 * Class includes overload constructors for creating car objects, and overrides {@code equals}, {@code hashCode},
 * and {@code toString} methods from the {@code Object} class.
 * <p>
 * Lombok annotations {@code @Getter} and {@code @Setter} are used to generate getter and setter
 * methods for the attributes.
 * </p>
 */

@Getter
@Setter
public class Car {
    private Integer id_car;
    private String model;
    private boolean statusAvailable;

    /**
     * Constructs a new {@code Car} object with the specified identifier, model, and availability status.
     *
     * @param id_car the identifier of the car
     * @param model the model of the car
     * @param statusAvailable the availability status of the car
     */
    public Car(Integer id_car, String model, boolean statusAvailable){
        this.id_car = id_car;
        this.model = model;
        this.statusAvailable = statusAvailable;
    }

    /**
     * Constructs a new {@code Car} object with the specified model and availability status.
     * The identifier is set to {@code null}.
     *
     * @param model the model of the car
     * @param statusAvailable the availability status of the car
     */
    public Car(String model, boolean statusAvailable){
        this(null, model, statusAvailable);
    }

    /**
     * Returns the availability status of the car.
     *
     * @return {@code true} if the car is available, {@code false} otherwise
     */
    public boolean getStatusAvailable(){
        return this.statusAvailable;
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
                this.id_car,
                this.model,
                this.statusAvailable
        );
    }

    /**
     * Returns a string representation of the car.
     * Overrides the {@code toString} method from the {@code Object} class.
     *
     * @return a string representation of the car
     */
    @Override
    public String toString(){
        return "Car id: " + this.id_car
                + ", Model: " + this.model
                + ", Status: " + this.statusAvailable;
    }
}