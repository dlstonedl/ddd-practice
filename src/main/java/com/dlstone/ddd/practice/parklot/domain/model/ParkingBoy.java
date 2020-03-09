package com.dlstone.ddd.practice.parklot.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Slf4j
public class ParkingBoy {
    @EqualsAndHashCode.Include
    private final ParkingBoyId id;
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private Strategy strategy = new SortedStrategy();

    ParkingLot selectParkingLot() {
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
}
