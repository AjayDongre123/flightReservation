package com.airline.reservation.service;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.entity.FlightBooking;
import com.airline.reservation.entity.Passenger;
import com.airline.reservation.model.FlightBookingListResponse;
import com.airline.reservation.model.FlightBookingObjectResponse;
import com.airline.reservation.model.FlightListResponse;
import com.airline.reservation.model.MessageResponse;
import com.airline.reservation.repository.FlightBookingRepository;
import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightBookingService {
    @Autowired
    private FlightBookingRepository flightBookingRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private FlightRepository flightRepository;

    public ResponseEntity createFlightBooking(long passengerId, long flightId) {
        FlightBooking flightBooking = new FlightBooking();
        if (passengerRepository.existsById(passengerId)) {
            if (flightRepository.existsById(flightId)) {
                if (!(flightBookingRepository.existsByPassengerIdAndFlightId(passengerId, flightId))) {
                    Passenger passenger = passengerRepository.findById(passengerId).get();
                    flightBooking.setPassengerId(passengerId);
                    flightBooking.setFirstName(passenger.getFirstName());
                    flightBooking.setLastName(passenger.getLastName());
                    Flight flight = flightRepository.findById(flightId).get();

                    flightBooking.setFlightId(flightId);
                    flightBooking.setFlightName(flight.getFlightName());
                    flightBooking.setSource(flight.getSource());
                    flightBooking.setDestination(flight.getDestination());
                    flightBooking.setDepartureTime(flight.getDepartureTime());
                    flightBooking.setArrivalTime(flight.getArrivalTime());
                    flightBooking.setFare(flight.getFare());
                    List<FlightBooking> all = flightBookingRepository.findAll();
                    if (!all.isEmpty()) {
                        int maxBookSeats = flightBookingRepository.getMaxBookSeats();
                        System.out.println("Before if < 60......." + flight.getTotalSeats());
                        if (maxBookSeats < flight.getTotalSeats()) {
                            System.out.println("-----------Inside <60..... " + flightBooking.getBookSeats());
                            flightBooking.setBookSeats(maxBookSeats + 1);
                            flightBookingRepository.save(flightBooking);
                            return new ResponseEntity(new MessageResponse(true, "createFlightBooking Successfully"), HttpStatus.OK);
                        } else {
                            return new ResponseEntity(new MessageResponse(false, "there is no seats for booking"), HttpStatus.NOT_FOUND);
                        }
                    } else {
                        flightBooking.setBookSeats(1);
                        flightBookingRepository.save(flightBooking);
                        return new ResponseEntity(new MessageResponse(true, "createFlightBooking successfully"), HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity(new MessageResponse(false, "Passenger already booked flight...!"), HttpStatus.CONFLICT);
                }
            } else {
                return new ResponseEntity(new MessageResponse(false, "flightId doesn't exist"), HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(new MessageResponse(false, "passenger doesn't exist"), HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity updateFlightBooking(long oldFlightId, long newFlightId) {
        if (flightRepository.existsById(oldFlightId)) {
            if(flightRepository.existsById(newFlightId)) {
                Flight flight = flightRepository.findById(newFlightId).get();
                List<FlightBooking> byFlightId = flightBookingRepository.findByFlightId(oldFlightId);
                String flightName = flight.getFlightName();
                String arrivalTime = flight.getArrivalTime();
                String departureTime = flight.getDepartureTime();
                String destination = flight.getDestination();
                for(FlightBooking flightBooking : byFlightId){
                    flightBooking.setFlightName(flightName);
                    flightBooking.setFlightId(newFlightId);
                    flightBooking.setArrivalTime(arrivalTime);
                    flightBooking.setDepartureTime(departureTime);
                    flightBooking.setDestination(destination);
                    flightBookingRepository.save(flightBooking);
                }
                return new ResponseEntity(new MessageResponse(true, "Successfully Updated flight"), HttpStatus.OK);
            }else{
                return null;
            }
        }else {
            return new ResponseEntity(new MessageResponse(false, "passengerId not exist!!!"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity cancelFlightBooking(long bookingId) {
        if (flightBookingRepository.existsById(bookingId)) {
            flightBookingRepository.deleteById(bookingId);
            return new ResponseEntity(new MessageResponse(true, "Successfully Deleted flightBooking"), HttpStatus.OK);
        }
        return new ResponseEntity(new MessageResponse(false, "bookingId not Exist!!!"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity getByFlightBookingId(long bookingId) {
        if (flightBookingRepository.existsById(bookingId)) {
            FlightBooking flightBooking = flightBookingRepository.findById(bookingId).get();
            return new ResponseEntity(new FlightBookingObjectResponse(true, "Successfully get data", flightBooking), HttpStatus.OK);
        }
        return new ResponseEntity(new MessageResponse(false, "flightBookingId not Exist!!!"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity getAllFlightBooking() {
        List<FlightBooking> allflightBooking = flightBookingRepository.findAll();
        if (!allflightBooking.isEmpty()) {
            return new ResponseEntity(new FlightBookingListResponse(true, "Successfully get All Data!!", allflightBooking)
                    , HttpStatus.OK);
        }
        return new ResponseEntity(new MessageResponse(false, "Not found Data"), HttpStatus.NOT_FOUND);
    }
}



