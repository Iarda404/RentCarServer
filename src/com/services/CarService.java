package com.services;

import com.dao.CarDao;
import com.models.Car;

import java.util.List;

/**
 * The {@code CarService} class provides methods for managing car-related operations.
 */

public class CarService {
    private final CarDao carDao= CarDao.getInstance();

    /**
     * Returns all available cars.
     *
     * @return a list of available {@code Car} objects
     */
    public List<Car> getAllAvailableCars(){
        return this.carDao.findAllAvailableCars();
    }

    /**
     * Saves a car to the database.
     *
     * @param car the car to be saved
     */
    public void saveCar(Car car){
        this.carDao.saveCar(car);
    }
}