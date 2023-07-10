package com.airline.reservation.repository;

import com.airline.reservation.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface FlightBookingRepository extends JpaRepository<FlightBooking,Long> {

    List<FlightBooking> findByFlightId(long flightId);
    @Query(value = "select MAX(book_seats) from flight_booking", nativeQuery = true)
    int getMaxBookSeats();

    boolean existsByPassengerIdAndFlightId(long passengerId, long flightId);

    //@Query(value = "select * from FlightBooking where FlightBooking.passengerId=passengerId And FlightBooking.flightId=flightId",nativeQuery = trur)
}