package com.dlstone.ddd.practice.parklot.parking.adapter;

import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinder;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinderFactory;

public class DbParkingLotFinderFactory implements ParkingLotFinderFactory {


    @Override
    public ParkingLotFinder newParkingManager() {
        return null;
    }

    @Override
    public ParkingLotFinder newParkingBoy(ParkingBoyId parkingBoyId) {

        return null;
    }
}
