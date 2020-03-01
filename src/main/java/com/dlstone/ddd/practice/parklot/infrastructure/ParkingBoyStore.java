package com.dlstone.ddd.practice.parklot.infrastructure;

import com.dlstone.ddd.practice.parklot.domain.model.ParkingBoy;
import com.dlstone.ddd.practice.parklot.domain.model.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.domain.model.ParkingLot;
import com.dlstone.ddd.practice.parklot.domain.model.ParkingLotId;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public enum ParkingBoyStore {
    INSTANCE;

    @Getter
    @Setter
    private ParkingBoy parkingBoy;

    ParkingBoyStore() {
        this.parkingBoy = new ParkingBoy(new ParkingBoyId("boy1"));
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(new ParkingLotId("lot1"), 1));
        parkingLots.add(new ParkingLot(new ParkingLotId("lot2"), 2));
        this.parkingBoy.setParkingLots(parkingLots);
    }
}
