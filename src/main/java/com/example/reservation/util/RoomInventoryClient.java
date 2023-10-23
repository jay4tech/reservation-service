package com.example.reservation.util;

import com.example.reservation.model.RoomInventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hotel-management-service-service", url = "http://localhost:8083")
public interface RoomInventoryClient {
  
    @PostMapping("/rooms/availability")
    public RoomInventory getAvailability(@RequestBody RoomInventory roomInventory);
  
}