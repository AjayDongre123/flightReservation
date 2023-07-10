package com.airline.reservation.controller;

import com.airline.reservation.service.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightBooking")
public class FlightBookingController {
    @Autowired
    private FlightBookingService flightBookingService;

    @PostMapping("/createFlightBooking")
    public ResponseEntity createFlightBooking(@RequestParam(name = "passengerId") long passengerId,
                                              @RequestParam(name = "flightId") long flightId) {
        return flightBookingService.createFlightBooking(passengerId, flightId);
    }

    @PutMapping("/updateFlightBooking")
    public ResponseEntity updateFlightBooking(@RequestParam(name = "oldFlightId") long oldFlightId,@RequestParam(name = "newFlightId") long newFlightId) {
        return flightBookingService.updateFlightBooking(oldFlightId, newFlightId);
    }

    @DeleteMapping("/cancelFlightBooking")
    public ResponseEntity cancelFlightBooking(@RequestParam(name = "bookingId") Long bookingId) {
        return flightBookingService.cancelFlightBooking(bookingId);
    }

    @GetMapping("/getByFlightBookingId")
    public ResponseEntity getByFlightBookingId(@RequestParam(name = "bookingId") Long bookingId){
        return flightBookingService.getByFlightBookingId(bookingId);
    }

    @GetMapping("/getAllFlightBooking")
    public ResponseEntity getAllFlightBooking() {
        return flightBookingService.getAllFlightBooking();
    }
}


