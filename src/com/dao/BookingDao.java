package com.dao;

import com.models.Booking;
import com.utils.ConnectionPoolManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code BookingDao} class provides methods for interacting with the "booking" table in the database.
 * <p>
 * It follows the singleton pattern where one object of this class corresponds to one table in the database.
 * </p>
 */
public class BookingDao {
    private static final String SAVE_SQL;
    private static final String FIND_SQL;
    private static final BookingDao INSTANCE;

    static {
        INSTANCE = new BookingDao();
        SAVE_SQL = "INSERT INTO \"booking\" (idUser,idCar,from_date,to_date) VALUES (?,?,?,?)";
        FIND_SQL = "SELECT * FROM \"booking\" WHERE id_car = ?";
    }

    private BookingDao(){}

    /**
     * Retrieves the singleton instance of the {@code BookingDao} class.
     *
     * @return the singleton instance of {@code BookingDao}
     */
    public static BookingDao getInstance(){
        return INSTANCE;
    }

    /**
     * Saves a booking to the database.
     *
     * @param booking the booking to be saved
     * @throws RuntimeException
     */
    public void saveBooking(Booking booking){
        try(Connection connection  = ConnectionPoolManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL)){

            connection.setSchema("public");
            statement.setInt(1, booking.getIdUser());
            statement.setInt(2, booking.getIdCar());
            statement.setDate(3, Date.valueOf(booking.getFromDate()));
            statement.setDate(4, Date.valueOf(booking.getToDate()));

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds bookings for a specified car ID in the database.
     *
     * @param id_car the car ID to find bookings for
     * @return a list of {@code Booking} objects associated with the specified car ID
     * @throws RuntimeException
     */
    public List<Booking> findBookingByCarId(int id_car){
        List<Booking> bookings = new ArrayList<>();

        try(Connection connection  = ConnectionPoolManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_SQL)){

            connection.setSchema("public");
            statement.setInt(1, id_car);

            ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    Booking booking = new Booking(
                            resultSet.getInt("id_booking"),
                            resultSet.getInt("idUser"),
                            resultSet.getInt("idCar"),
                            resultSet.getDate("from_date").toLocalDate(),
                            resultSet.getDate("to_date").toLocalDate()
                    );
                    bookings.add(booking);
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookings;
    }
}