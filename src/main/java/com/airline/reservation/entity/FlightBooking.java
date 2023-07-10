package com.airline.reservation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@Table(name = "FlightBooking")
public class FlightBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;
    private long passengerId;
    private String firstName;
    private String lastName;
    private long flightId;
    private String flightName;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private  long fare;
    private  int bookSeats;
}
