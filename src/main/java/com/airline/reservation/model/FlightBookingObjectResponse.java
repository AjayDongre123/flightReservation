package com.airline.reservation.model;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.entity.FlightBooking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookingObjectResponse {
    private boolean result;
    private String message;
    private FlightBooking Data;
}

