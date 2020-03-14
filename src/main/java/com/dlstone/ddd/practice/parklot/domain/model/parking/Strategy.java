package com.dlstone.ddd.practice.parklot.domain.model.parking;

import java.util.List;

public interface Strategy {
    ParkingLot selectParkingLot(List<ParkingLot> parkingLots);
}
