package com.masai.controller;

import java.util.List;



import com.masai.DTO.ReservationDTO;
import com.masai.exceptions.UserException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.masai.entities.Reservation;
import com.masai.exceptions.ReservationException;
import com.masai.services.ReservationService;


@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @PostMapping("/reservation/user")  // only bus and source destinantion required
    public ResponseEntity<Reservation> addReservation(@Valid @RequestBody ReservationDTO reservationDTO, @RequestParam(required = false) String key) throws ReservationException, UserException {
        Reservation savedReservation = reservationService.addReservation(reservationDTO, key);
        return new ResponseEntity<Reservation>(savedReservation, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/reservation/user/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable("id") Integer reservationId, @RequestParam(required = false) String key) throws ReservationException, UserException {
        Reservation deletedReservation = reservationService.deleteReservation(reservationId, key);
        return new ResponseEntity<Reservation>(deletedReservation, HttpStatus.OK);
    }


    @GetMapping("/reservation/admin/{id}")
    public ResponseEntity<Reservation> viewReservation(@PathVariable("id") Integer reservationId, @RequestParam(required = false) String key) throws ReservationException {
        Reservation foundReservation = reservationService.viewReservation(reservationId, key);
        return new ResponseEntity<Reservation>(foundReservation, HttpStatus.OK);
    }


    @GetMapping("/reservation/admin")
    public ResponseEntity<List<Reservation>> viewAllReservation(@RequestParam(required = false) String key) throws ReservationException {
        List<Reservation> reservationList = reservationService.viewAllReservation(key);
        return new ResponseEntity<List<Reservation>>(reservationList, HttpStatus.OK);
    }


    @GetMapping("/reservation/user")
    public ResponseEntity<List<Reservation>> viewReservationByUser(@RequestParam(required = false) String key) throws UserException {

        List<Reservation> reservationList = reservationService.viewReservationByUser(key);
        return new ResponseEntity<List<Reservation>>(reservationList, HttpStatus.OK);
    }
}
