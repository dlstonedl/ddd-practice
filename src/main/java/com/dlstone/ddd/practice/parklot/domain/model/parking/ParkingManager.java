package com.dlstone.ddd.practice.parklot.domain.model.parking;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ParkingManager {
    List<ParkingBoy> parkingBoys = new ArrayList<>();

    public ParkingManager(List<ParkingBoy> parkingBoys) {
        this.parkingBoys = parkingBoys;
    }

    public ParkingLot selectParkingLot() {
        return parkingBoys
            .stream()
            .filter(ParkingBoy::available)
            .findFirst()
            .map(parkingBoy -> parkingBoy.selectParkingLot())
            .orElse(null);
    }

    public List<ParkingLot> getAvailableParkingLots() {
        return this.parkingBoys
            .stream()
            .flatMap(parkingBoy -> parkingBoy.getAvailableParkingLots().stream())
            .collect(Collectors.toList());
    }
}
