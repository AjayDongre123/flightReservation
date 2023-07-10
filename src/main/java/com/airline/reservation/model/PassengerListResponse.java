package com.airline.reservation.model;

import com.airline.reservation.entity.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerListResponse {
    private boolean result;
    private String message;
    private List<Passenger>passengerList;
}
