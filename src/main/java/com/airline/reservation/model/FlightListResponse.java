package com.airline.reservation.model;

import com.airline.reservation.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightListResponse {
    private boolean result;
    private  String message;
    List<Flight>flightList;
}
