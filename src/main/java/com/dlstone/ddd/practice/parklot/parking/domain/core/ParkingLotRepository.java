package com.dlstone.ddd.practice.parklot.parking.domain.core;

import java.util.List;

public interface ParkingLotRepository {

    List<ParkingLot> getParkingLots(List<ParkingLotId> id);

    void save(ParkingLot parkingLot);
}
