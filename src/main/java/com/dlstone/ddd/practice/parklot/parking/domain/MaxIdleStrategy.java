package com.dlstone.ddd.practice.parklot.parking.domain;

import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLot;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.Strategy;

import java.util.Comparator;
import java.util.List;

public class MaxIdleStrategy implements Strategy {

    @Override
    public ParkingLot selectParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots
            .stream()
            .sorted(Comparator.comparingInt(ParkingLot::availableLots).reversed())
            .findFirst()
            .orElse(null);
    }
}
