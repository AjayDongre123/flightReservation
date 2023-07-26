package com.airline.reservation.model;

import com.airline.reservation.entity.FlightBooking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MessageResponseOfFlightBookingList {
        private boolean result;
        private String message;
        List<FlightBooking> bookingFlightDetails;
    }


