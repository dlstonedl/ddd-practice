package com.dlstone.ddd.practice.parklot.parking.domain.policy;

import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLot;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotId;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.Strategy;
import com.dlstone.ddd.practice.parklot.parking.domain.policy.SortedStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortedStrategyTest {

    private Strategy sortedStrategy = new SortedStrategy();

    @Test
    public void should_return_parking_lot_order_by_id() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        ParkingLot parkingLot2 = new ParkingLot(new ParkingLotId("2"), 3);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingLot parkingLot = sortedStrategy.selectParkingLot(parkingLots);
        assertEquals(parkingLot1, parkingLot);
    }

}
