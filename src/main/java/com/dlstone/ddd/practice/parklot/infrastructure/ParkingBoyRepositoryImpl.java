package com.dlstone.ddd.practice.parklot.infrastructure;

import com.dlstone.ddd.practice.parklot.domain.model.*;

public class ParkingBoyRepositoryImpl implements ParkingBoyRepository {

    @Override
    public ParkingBoy getParkingBoy() {
        return ParkingBoyStore.INSTANCE.getParkingBoy();
    }

    @Override
    public void saveParkingBoy(ParkingBoy parkingBoy) {
        ParkingBoyStore.INSTANCE.setParkingBoy(parkingBoy);
    }
}
