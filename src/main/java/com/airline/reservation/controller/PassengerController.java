package com.airline.reservation.controller;

import com.airline.reservation.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @PostMapping("/addPassenger")
    public ResponseEntity addPassenger(@RequestParam(name = "firstName") String firstName,
                                       @RequestParam(name = "lastName") String lastName,
                                       @RequestParam(name = "age")int age,
                                       @RequestParam(name = "gender")String gender,
                                       @RequestParam(name = "phone")String phone,
                                       @RequestParam(name = "email") String email) {
        return passengerService.addPassenger(firstName,lastName,age,gender,phone,email);

    }

    @PutMapping("/updatePassenger")
    public ResponseEntity updatePassenger(@RequestParam(name = "passengerId") long passengerId,
                                          @RequestParam(name = "firstName") String firstName,
                                          @RequestParam(name = "lastName") String lastName,
                                          @RequestParam(name = "phone")String phone,
                                          @RequestParam(name = "email") String email){
        return passengerService.updatePassenger(passengerId, firstName,lastName,phone,email);
    }

    @DeleteMapping("/deletePassenger")
    public ResponseEntity updatePassenger(@RequestParam(name = "passengerId") long passengerId) {
        return passengerService.deletePassenger(passengerId);
    }

    @GetMapping("/getPassengerById")
    public ResponseEntity getPassenger(@RequestParam(name = "passengerId") long passengerId) {
        return passengerService.getPassengerById(passengerId);
    }
    @GetMapping("/getAllPassenger")
    public ResponseEntity getAllPassenger()
    {
        return passengerService.getAllPassenger();
    }
}