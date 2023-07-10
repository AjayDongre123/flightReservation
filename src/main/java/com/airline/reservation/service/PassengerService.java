package com.airline.reservation.service;
import com.airline.reservation.entity.Passenger;
import com.airline.reservation.model.MessageResponse;
import com.airline.reservation.model.PassengerListResponse;
import com.airline.reservation.model.PassengerResponseObject;
import com.airline.reservation.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public ResponseEntity addPassenger(String firstName, String lastName,int age,String gender,String phone, String email) {

        if (passengerRepository.existsByEmail(email)) {
            return new ResponseEntity(new MessageResponse(false, "this email is already exist!!"), HttpStatus.NOT_FOUND);
        }
        Passenger passenger = new Passenger();
        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);
        passenger.setAge(age);
        passenger.setGender(gender);
        passenger.setPhone(phone);
        passenger.setEmail(email);
        passengerRepository.save(passenger);
        return new ResponseEntity(new MessageResponse(true, "Successfully add Passenger"), HttpStatus.OK);

    }

    public ResponseEntity updatePassenger(long passengerId, String firstName,String lastName,String phone,String email) {
        if (passengerRepository.existsById(passengerId)) {
            Passenger passenger = passengerRepository.findById(passengerId).get();
            if(firstName!=null) {
                passenger.setFirstName(firstName);
            }
            passenger.setLastName(lastName);
            passenger.setPhone(phone);
            passenger.setEmail(email);
            passengerRepository.save(passenger);
            return new ResponseEntity(new MessageResponse(true, "successfully passenger name updated"),
                    HttpStatus.OK);

        } else {
            return new ResponseEntity(new MessageResponse(false, "passengerId not exist!!can't updated"),
                    HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deletePassenger(long passengerId) {
        if (passengerRepository.existsById(passengerId)) {
            passengerRepository.deleteById(passengerId);
            return new ResponseEntity<>(new MessageResponse(true, "successfully deleted Passenger"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(new MessageResponse(false, "passengerId not Exist"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getPassengerById(long passengerId) {
        if(passengerId>0) {
            if (passengerRepository.existsById(passengerId)) {
                Passenger passenger = passengerRepository.findById(passengerId).get();
                return new ResponseEntity(new PassengerResponseObject(true, "successfully get data", passenger), HttpStatus.OK);
            }
            return new ResponseEntity(new MessageResponse(false, "not found Data"), HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(new MessageResponse(false,"Please provide valid passengerId...!"),HttpStatus.NOT_ACCEPTABLE);
        }

    }

    public ResponseEntity getAllPassenger() {
        List<Passenger> listPassengers = passengerRepository.findAll();
        if (!(listPassengers.isEmpty())){
            return new ResponseEntity(new PassengerListResponse(true, "successfully get all", listPassengers), HttpStatus.OK);
        }else{
            return new ResponseEntity(new MessageResponse(false, "not found Data"), HttpStatus.NOT_FOUND);
        }
    }
}