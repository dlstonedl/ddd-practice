package com.dlstone.ddd.practice.parklot.parking.domain.core;

import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoySpecification;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindParkingLotService {

    private ParkingLotFinderFactory parkingLotFinderFactory;

    public ParkingLot findParkingLot(ParkingLotFinderSpecification parkingLotFinderSpecification) {
        return getParkingLotFinder(parkingLotFinderSpecification).findParkingLotToPark();
    }

    public List<ParkingLot> findAvailableParkingLots(ParkingLotFinderSpecification parkingLotFinderSpecification) {
        return getParkingLotFinder(parkingLotFinderSpecification).getAvailableParkingLots();
    }

    private ParkingLotFinder getParkingLotFinder(ParkingLotFinderSpecification parkingLotFinderSpecification) {
        ParkingLotFinder parkingLotFinder;
        if (parkingLotFinderSpecification instanceof ParkingBoySpecification) {
            parkingLotFinder = parkingLotFinderFactory.newParkingBoy(((ParkingBoySpecification) parkingLotFinderSpecification).getParkingBoyId());
        } else {
            parkingLotFinder = parkingLotFinderFactory.newParkingManager();
        }
        return parkingLotFinder;
    }

}
