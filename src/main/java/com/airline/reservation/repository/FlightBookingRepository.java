package com.airline.reservation.repository;

import com.airline.reservation.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface FlightBookingRepository extends JpaRepository<FlightBooking,Long> {
    boolean existsByFlightId(long flightId);

    List<FlightBooking> findByFlightId(long oldFlightId);

    boolean existsByPassengerIdAndFlightId(long passengerId, long flightId);
    @Query(value = "select MAX(book_seats) from flight_booking where flight_id=:flight_Id",nativeQuery=true)
    int getLastBookedSeats(@Param("flight_Id") long flight_Id);

    //@Query(value = "select * from FlightBooking where FlightBooking.passengerId=passengerId And FlightBooking.flightId=flightId",nativeQuery = trur)
}