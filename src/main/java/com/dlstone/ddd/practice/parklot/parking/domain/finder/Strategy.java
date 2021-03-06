package com.dlstone.ddd.practice.parklot.parking.domain.finder;

import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLot;

import java.util.List;

public interface Strategy {
    ParkingLot selectParkingLot(List<ParkingLot> parkingLots);
}
