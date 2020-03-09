package com.dlstone.ddd.practice.parklot.domain.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParkingManagerTest {

    @Test
    public void should_return_parking_lot_when_manager_have_available_parking_boy() {
        ParkingManager parkingManager = new ParkingManager();
        List<ParkingBoy> parkingBoys = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingBoyId("boy1"));
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLots.add(parkingLot1);
        parkingBoy.setParkingLots(parkingLots);
        parkingBoys.add(parkingBoy);
        parkingManager.setParkingBoys(parkingBoys);

        ParkingLot parkingLot = parkingManager.selectParkingLot();
        assertEquals(parkingLot1, parkingLot);
    }

    @Test
    public void should_return_null_when_manager_not_have_available_parking_boy() {
        ParkingManager parkingManager = new ParkingManager();
        List<ParkingBoy> parkingBoys = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingBoyId("boy1"));
        List<ParkingLot> parkingLots = new ArrayList<>();
        Map<Ticket, Car> ticketCarMap = new HashMap<>();
        ticketCarMap.put(new Ticket(new CarId("粤B000TA"), new ParkingLotId("1")), new Car(new CarId("粤B000TA")));
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLot1.setTicketCarMap(ticketCarMap);
        parkingLots.add(parkingLot1);
        parkingBoy.setParkingLots(parkingLots);
        parkingManager.setParkingBoys(parkingBoys);

        ParkingLot parkingLot = parkingManager.selectParkingLot();
        assertNull(parkingLot);
    }


}
