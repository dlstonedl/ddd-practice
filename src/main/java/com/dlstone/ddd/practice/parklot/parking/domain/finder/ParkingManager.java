package com.dlstone.ddd.practice.parklot.parking.domain.finder;

import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLot;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinder;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class ParkingManager implements ParkingLotFinder {
    private final List<ParkingBoy> parkingBoys;

    @Override
    public ParkingLot findParkingLotToPark() {
        return parkingBoys
            .stream()
            .filter(ParkingBoy::available)
            .findFirst()
            .map(parkingBoy -> parkingBoy.findParkingLotToPark())
            .orElse(null);
    }

    @Override
    public List<ParkingLot> getAvailableParkingLots() {
        return this.parkingBoys
            .stream()
            .flatMap(parkingBoy -> parkingBoy.getAvailableParkingLots().stream())
            .collect(Collectors.toList());
    }
}
