package com.airline.reservation.model;

import com.airline.reservation.entity.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerResponseObject {
    private boolean result;
    private String message;
    private Passenger Data;

}
