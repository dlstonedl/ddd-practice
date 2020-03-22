package com.dlstone.ddd.practice.parklot.parking.domain.finder;

import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinder;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinderFactory;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinderSpecification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParkingBoySpecification implements ParkingLotFinderSpecification {

    private ParkingLotFinderFactory parkingLotFinderFactory;
    private String parkingBoyId;

    @Override
    public ParkingLotFinder newParkingLotFinder() {
        return parkingLotFinderFactory.newParkingBoy(parkingBoyId);
    }
}
