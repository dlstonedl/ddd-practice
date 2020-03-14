package com.dlstone.ddd.practice.parklot.domain.model.parking;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Value
public class ParkingBoy {
    private final List<ParkingLot> parkingLots;
    private final Strategy strategy;

    public ParkingLot selectParkingLot() {
        List<ParkingLot> availableParkingLots = this.parkingLots
            .stream()
            .filter(parkingLot -> parkingLot.availableLots() > 0)
            .collect(Collectors.toList());
        return strategy.selectParkingLot(availableParkingLots);
    }

    boolean available() {
        return this.parkingLots
            .stream()
            .filter(parkingLot -> parkingLot.availableLots() > 0)
            .findAny()
            .isPresent();
    }

    public List<ParkingLot> getAvailableParkingLots() {
        return this.parkingLots
            .stream()
            .filter(parkingLot -> parkingLot.availableLots() > 0)
            .collect(Collectors.toList());
    }
}
