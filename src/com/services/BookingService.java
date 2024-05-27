package com.services;

import com.dao.BookingDao;
import com.models.Booking;

import java.util.List;

/**
 * The {@code BookingService} class provides methods for managing booking-related operations.
 */

public class BookingService {
    private final BookingDao bookingDao = BookingDao.getInstance();

    /**
     * Books a car.
     *
     * @param booking the booking to be saved
     */
    public void bookCar(Booking booking){
        this.bookingDao.saveBooking(booking);
    }

    /**
     * Returns bookings for a specified car ID.
     *
     * @param id_car the car ID to find bookings for
     * @return a list of {@code Booking} objects associated with the specified car ID
     */
    public List<Booking> getBookingByCarId(int id_car){
        return this.bookingDao.findBookingByCarId(id_car);
    }
}