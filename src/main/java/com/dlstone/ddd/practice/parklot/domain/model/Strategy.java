package com.dlstone.ddd.practice.parklot.domain.model;

import java.util.List;

public interface Strategy {
    ParkingLot selectParkingLot(List<ParkingLot> parkingLots);
}
