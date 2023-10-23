package com.example.reservation.service;

import com.example.reservation.entity.Reservation;
import com.example.reservation.model.Payment;
import com.example.reservation.model.RoomInventory;
import com.example.reservation.repository.ReservationRepository;
import com.example.reservation.util.PaymentClient;
import com.example.reservation.util.RabbitHelper;
import com.example.reservation.util.RoomInventoryClient;
import com.example.reservation.util.UtilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RoomInventoryClient roomInventoryClient;

    @Autowired
    PaymentClient paymentClient;

    @Autowired
    RabbitHelper rabbitHelper;

    @Override
    public Reservation saveReservation(Reservation reservation) {
        RoomInventory roomInventory = new RoomInventory();
        roomInventory.setId(reservation.getRoomId());
        RoomInventory availableRoomInventory = roomInventoryClient.getAvailability(roomInventory);
        if (availableRoomInventory != null) {
            Payment payment = new Payment();
            payment.setAmount(availableRoomInventory.getAmount());
            payment.setCustomerId(reservation.getCustomerId());
            Payment responsePayment = paymentClient.receivePayment(payment);
            if (responsePayment != null) {
                rabbitHelper.sendReservationNotification(UtilityMapper.modelToString(reservation));
                return reservationRepository.save(reservation);
            }
        }
        return null;
    }
}
