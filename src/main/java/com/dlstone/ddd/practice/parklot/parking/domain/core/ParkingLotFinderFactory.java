package com.dlstone.ddd.practice.parklot.parking.domain.core;

import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyId;

public interface ParkingLotFinderFactory {
    ParkingLotFinder newParkingManager();
    ParkingLotFinder newParkingBoy(ParkingBoyId parkingBoyId);
}
