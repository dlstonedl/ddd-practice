package com.dlstone.ddd.practice.parklot.parking.domain;

import com.dlstone.ddd.practice.parklot.parking.domain.core.*;
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
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ParkingManager parkingManager = new ParkingManager(parkingBoys);


        ParkingLot parkingLot = parkingManager.selectParkingLot();
        assertEquals(parkingLot1, parkingLot);
    }

    @Test
    public void should_return_null_when_manager_not_have_available_parking_boy() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        Map<Ticket, Car> ticketCarMap = new HashMap<>();
        ticketCarMap.put(new Ticket(new CarId("粤B000TA"), new ParkingLotId("1")), new Car(new CarId("粤B000TA")));
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLot1.setTicketCarMap(ticketCarMap);
        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ParkingManager parkingManager = new ParkingManager(parkingBoys);

        ParkingLot parkingLot = parkingManager.selectParkingLot();
        assertNull(parkingLot);
    }

    @Test
    public void should_return_parking_lots_when_get_available_parking_lots() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ParkingManager parkingManager = new ParkingManager(parkingBoys);

        List<ParkingLot> availableParkingLots = parkingManager.getAvailableParkingLots();
        assertEquals(1, availableParkingLots.size());
    }
}
