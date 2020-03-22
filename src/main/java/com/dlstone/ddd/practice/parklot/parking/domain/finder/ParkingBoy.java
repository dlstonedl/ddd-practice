package com.dlstone.ddd.practice.parklot.parking.domain.finder;

import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLot;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinder;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class ParkingBoy implements ParkingLotFinder {
    private final List<ParkingLot> parkingLots;
    private final Strategy strategy;

    @Override
    public ParkingLot findParkingLotToPark() {
        List<ParkingLot> availableParkingLots = this.parkingLots
            .stream()
            .filter(parkingLot -> parkingLot.availableLots() > 0)
            .collect(Collectors.toList());
        return strategy.selectParkingLot(availableParkingLots);
    }

    @Override
    public List<ParkingLot> getAvailableParkingLots() {
        return this.parkingLots
            .stream()
            .filter(parkingLot -> parkingLot.availableLots() > 0)
            .collect(Collectors.toList());
    }

    boolean available() {
        return this.parkingLots
            .stream()
            .filter(parkingLot -> parkingLot.availableLots() > 0)
            .findAny()
            .isPresent();
    }
}
