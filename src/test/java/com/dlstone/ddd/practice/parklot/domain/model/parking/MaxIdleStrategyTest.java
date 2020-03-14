package com.dlstone.ddd.practice.parklot.domain.model.parking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MaxIdleStrategyTest {

    private Strategy maxIdleStrategy = new MaxIdleStrategy();

    @Test
    public void should_return_max_idle_parking_lot() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        ParkingLot parkingLot2 = new ParkingLot(new ParkingLotId("2"), 3);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingLot parkingLot = maxIdleStrategy.selectParkingLot(parkingLots);
        assertEquals(parkingLot2, parkingLot);
    }
}
