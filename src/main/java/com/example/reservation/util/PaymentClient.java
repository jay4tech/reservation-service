package com.example.reservation.util;

import com.example.reservation.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "http://localhost:8084")
public interface PaymentClient {
  
    @PostMapping("/payments/")
    public Payment receivePayment(@RequestBody Payment payment);
  
}