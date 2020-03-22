package com.dlstone.ddd.practice.parklot.parking.domain.core;

public interface ParkingLotFinderFactory {
    ParkingLotFinder newParkingManager();
    ParkingLotFinder newParkingBoy(String parkingBoyId);
}
