package com.airline.reservation.controller;

import com.airline.reservation.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    @PostMapping("/addFlight")
  public ResponseEntity addFlight(@RequestParam(name = "flightName") String flightName,
                                  @RequestParam(name = "source") String source,
                                  @RequestParam(name = "destination") String destination,
                                  @RequestParam(name = "departureTime") String departureTime,
                                  @RequestParam(name ="arrivalTime") String arrivalTime,
                                  @RequestParam(name = "fare") int fare){
     return flightService.addFlight(flightName,source,destination,departureTime,arrivalTime,fare);
 }
 @PutMapping("/updateFlight")
 public ResponseEntity updateFlight(@RequestParam(name = "flightId") Long flightId,
                                    @RequestParam(name = "flightName") String flightName,
                                    @RequestParam(name = "destination") String destination,
                                    @RequestParam(name = "departureTime") String departureTime,
                                    @RequestParam(name ="arrivalTime") String arrivalTime,
                                    @RequestParam(name = "fare") int fare){
       return flightService.updateFlight(flightId,flightName,destination,departureTime,arrivalTime,fare);
    }
    @DeleteMapping("/deleteFlight")
    public ResponseEntity deleteFlight(@RequestParam(name = "flightId") Long flightId){
        return flightService.deleteFlight(flightId);
    }
    @GetMapping("/getByFlightId")
    public ResponseEntity getByFlightId(@RequestParam(name = "flightId") Long flightId){
        return flightService.getByFlightId(flightId);
    }
    @GetMapping("/getAllFlight")
    public ResponseEntity getAllFlight()
    {
        return flightService.getAllFlight();
    }
}
