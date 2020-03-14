package com.dlstone.ddd.practice.parklot.domain.model.parking;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParkingManager {
    List<ParkingBoy> parkingBoys = new ArrayList<>();

    ParkingLot selectParkingLot() {
        return parkingBoys
            .stream()
            .filter(ParkingBoy::available)
            .findFirst()
            .map(parkingBoy -> parkingBoy.selectParkingLot())
            .orElse(null);
    }

}
