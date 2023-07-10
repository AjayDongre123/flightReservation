package com.airline.reservation.service;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.model.FlightListResponse;
import com.airline.reservation.model.FlightObjectResponse;
import com.airline.reservation.model.MessageResponse;
import com.airline.reservation.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public ResponseEntity addFlight(String flightName, String source, String destination, String departureTime,
                                    String arrivalTime, int fare) {
        Flight flight = new Flight();
        List<Flight> byFlightName = flightRepository.findByFlightName(flightName);
        for (Flight flightDetail : byFlightName) {
            String flightOnName = flightDetail.getFlightName();
            if (flightOnName.equals(flightName)) {
                return new ResponseEntity(new MessageResponse(false, "this flightName is already declared!!"), HttpStatus.NOT_FOUND);
            }
        }
        flight.setFlightName(flightName);
        flight.setSource(source);
        flight.setDestination(source+"-"+destination);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flight.setFare(fare);
        flight.setCancelFlight(false);
        flight.setTotalSeats(60);
        flightRepository.save(flight);
        return new ResponseEntity(new MessageResponse(true, "Successfully added flight"), HttpStatus.OK);
    }

    public ResponseEntity updateFlight(long flightId, String flightName, String destination, String departureTime, String arrivalTime,
                                       int fare) {
        if (flightRepository.existsById(flightId)) {
            Flight flight = flightRepository.findById(flightId).get();
            flight.setFlightName(flightName);
            flight.setDestination(destination);
            flight.setDepartureTime(departureTime);
            flight.setArrivalTime(arrivalTime);
            flight.setFare(fare);
            flight.setTotalSeats(60);
            flightRepository.save(flight);
            return new ResponseEntity(new MessageResponse(true, "Successfully Updated flight"), HttpStatus.OK);
        }
        return new ResponseEntity(new MessageResponse(false, "flightId not Exist!!!"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity deleteFlight(long flightId) {
        if (flightRepository.existsById(flightId)) {
            flightRepository.deleteById(flightId);
            return new ResponseEntity(new MessageResponse(true, "Successfully Deleted flight"), HttpStatus.OK);
        }
        return new ResponseEntity(new MessageResponse(false, "flightId not Exist!!!"), HttpStatus.NOT_FOUND);
    }
    public ResponseEntity getByFlightId(long flightId){
        if (flightRepository.existsById(flightId)) {
            Flight flight = flightRepository.findById(flightId).get();
            return new ResponseEntity(new FlightObjectResponse(true, "Successfully get data",flight), HttpStatus.OK);
        }
        return new ResponseEntity(new MessageResponse(false, "flightId not Exist!!!"), HttpStatus.NOT_FOUND);
    }
    public ResponseEntity getAllFlight(){
        List<Flight> allflight = flightRepository.findAll();
        if(!allflight.isEmpty()){
            return new ResponseEntity(new FlightListResponse(true,"Successfully get All Data!!",allflight)
                    ,HttpStatus.OK);
        }
        return new ResponseEntity (new MessageResponse(false,"Not found Data"),HttpStatus.NOT_FOUND);
    }
    }


