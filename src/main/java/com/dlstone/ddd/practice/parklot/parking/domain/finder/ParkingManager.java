package com.dlstone.ddd.practice.parklot.parking.domain.finder;

import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLot;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinder;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class ParkingManager implements ParkingLotFinder {
    private final List<ParkingBoy> parkingBoys;

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

    @Override
    public ParkingLot findParkingLotToPark() {
        return null;
    }
}
