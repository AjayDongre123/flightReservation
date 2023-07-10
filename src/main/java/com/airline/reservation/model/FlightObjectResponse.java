package com.airline.reservation.model;

import com.airline.reservation.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightObjectResponse {
    private boolean result;
    private String message;
    private Flight Data;
}
