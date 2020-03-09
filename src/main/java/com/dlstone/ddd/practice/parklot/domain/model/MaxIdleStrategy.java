package com.dlstone.ddd.practice.parklot.domain.model;

import java.util.Comparator;
import java.util.List;

public class MaxIdleStrategy implements Strategy {

    @Override
    public ParkingLot selectParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots
            .stream()
            .filter(parkingLot -> parkingLot.availableLots() > 0)
            .sorted(Comparator.comparingInt(ParkingLot::availableLots).reversed())
            .findFirst()
            .orElse(null);
    }
}
