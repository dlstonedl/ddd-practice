package com.dlstone.ddd.practice.parklot.parking.domain.core;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindParkingLotService {

    public ParkingLot findParkingLot(ParkingLotFinderSpecification parkingLotFinderSpecification) {
        return parkingLotFinderSpecification.newParkingLotFinder().findParkingLotToPark();
    }

    public List<ParkingLot> findAvailableParkingLots(ParkingLotFinderSpecification parkingLotFinderSpecification) {
        return parkingLotFinderSpecification.newParkingLotFinder().getAvailableParkingLots();
    }
}
