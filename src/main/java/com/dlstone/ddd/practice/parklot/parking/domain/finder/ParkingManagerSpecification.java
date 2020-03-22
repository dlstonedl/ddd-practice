package com.dlstone.ddd.practice.parklot.parking.domain.finder;

import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinder;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinderFactory;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinderSpecification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParkingManagerSpecification implements ParkingLotFinderSpecification {

    private ParkingLotFinderFactory parkingLotFinderFactory;

    @Override
    public ParkingLotFinder newParkingLotFinder() {
        return parkingLotFinderFactory.newParkingManager();
    }
}
