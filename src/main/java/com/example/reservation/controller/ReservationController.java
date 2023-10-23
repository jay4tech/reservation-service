package com.example.reservation.controller;

import com.example.reservation.service.IReservationService;
import com.example.reservation.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    @Autowired
    IReservationService reservationService;

    @PostMapping("/")
    public Reservation saveReservation(@RequestBody Reservation reservation) {
       return reservationService.saveReservation(reservation);
    }
}
