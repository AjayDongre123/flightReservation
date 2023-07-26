package com.airline.reservation.model;

import com.airline.reservation.entity.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseOfPassengerList {
    private boolean result;
    private String message;
    private List<GetFlightDetailsWithPassengerResponse> BookingFlightPassDetails;

}
