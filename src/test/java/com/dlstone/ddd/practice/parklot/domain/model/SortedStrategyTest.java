package com.dlstone.ddd.practice.parklot.domain.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SortedStrategyTest {

    private Strategy sortedStrategy = new SortedStrategy();

    @Test
    public void should_return_parking_lot_order_by_id() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        Map<Ticket, Car> ticketCarMap = new HashMap<>();
        ticketCarMap.put(new Ticket(new CarId("粤B000TA"), new ParkingLotId("1")), new Car(new CarId("粤B000TA")));
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLot1.setTicketCarMap(ticketCarMap);
        ParkingLot parkingLot2 = new ParkingLot(new ParkingLotId("2"), 3);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingLot parkingLot = sortedStrategy.selectParkingLot(parkingLots);
        assertEquals(parkingLot2, parkingLot);
    }

}
