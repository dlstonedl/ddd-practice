package com.dlstone.ddd.practice.parklot.parking.domain;

import java.util.List;

public interface ParkingLotRepository {

    List<ParkingLot> getParkingLots(List<ParkingLotId> id);

    void addParkingLot(ParkingLot parkingLot);

    void removeParkingLot(ParkingLot parkingLot);

    void updateParkingLot(ParkingLot parkingLot);
}
