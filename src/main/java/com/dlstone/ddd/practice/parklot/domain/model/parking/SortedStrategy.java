package com.dlstone.ddd.practice.parklot.domain.model.parking;

import java.util.Comparator;
import java.util.List;

public class SortedStrategy implements Strategy {

    @Override
    public ParkingLot selectParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots
            .stream()
            .sorted(Comparator.comparing(parkingLot -> parkingLot.getId().getValue()))
            .findFirst()
            .orElse(null);
    }
}
