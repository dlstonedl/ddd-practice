package com.dlstone.ddd.practice.parklot.parking.domain.core;

import java.util.List;

public interface ParkingLotFinder {
    ParkingLot findParkingLotToPark();

    List<ParkingLot> getAvailableParkingLots();
}
