package com.example.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomInventory {
    private Long id;
    private String roomType;
    private Long quantity;
    private Double amount;
}
