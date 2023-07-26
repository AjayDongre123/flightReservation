package com.airline.reservation.model;

import com.airline.reservation.entity.FlightBooking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFlightDetailsWithPassengerResponse {
    private long flightId;
    private String flightName;
    private String source;
    private int noOfSeatsBooked;
    private int remainingSeats;
    private List<FlightBooking> passengerDetails;

}
