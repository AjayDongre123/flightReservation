package com.airline.reservation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name="flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long flightId;
    private  String flightName;
    private  String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private long fare;
    private boolean cancelFlight;
    private  int totalSeats;
    private int bookseats;
}
