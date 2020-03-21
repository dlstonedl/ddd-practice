package com.dlstone.ddd.practice.parklot.parking.domain;

import java.util.List;

public interface Strategy {
    ParkingLot selectParkingLot(List<ParkingLot> parkingLots);
}
