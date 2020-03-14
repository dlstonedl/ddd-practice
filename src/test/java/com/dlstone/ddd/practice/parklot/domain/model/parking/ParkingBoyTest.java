package com.dlstone.ddd.practice.parklot.domain.model.parking;

import com.dlstone.ddd.practice.parklot.domain.model.parking.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ParkingBoyTest {

    @Test
    public void should_return_parking_lot_when_is_junior_parking_boy() {
        ParkingBoy JuniorParkingBoy = new ParkingBoy(new ParkingBoyId("boy1"));
        List<ParkingLot> parkingLots = new ArrayList<>();
        Map<Ticket, Car> ticketCarMap = new HashMap<>();
        ticketCarMap.put(new Ticket(new CarId("粤B000TA"), new ParkingLotId("1")), new Car(new CarId("粤B000TA")));
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLot1.setTicketCarMap(ticketCarMap);
        ParkingLot parkingLot2 = new ParkingLot(new ParkingLotId("2"), 3);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        JuniorParkingBoy.setParkingLots(parkingLots);

        ParkingLot parkingLot = JuniorParkingBoy.selectParkingLot();
        assertEquals(parkingLot2, parkingLot);
    }

    @Test
    public void should_return_parking_lot_when_is_senior_parking_boy() {
        ParkingBoy seniorParkingBoy = new ParkingBoy(new ParkingBoyId("boy1"));
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        ParkingLot parkingLot2 = new ParkingLot(new ParkingLotId("2"), 3);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        seniorParkingBoy.setParkingLots(parkingLots);
        seniorParkingBoy.setStrategy(new MaxIdleStrategy());

        ParkingLot parkingLot = seniorParkingBoy.selectParkingLot();
        assertEquals(parkingLot2, parkingLot);
    }

    @Test
    public void should_return_true_when_parking_boy_have_available_parking_lot() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingBoyId("boy1"));
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLots.add(parkingLot1);
        parkingBoy.setParkingLots(parkingLots);

        boolean available = parkingBoy.available();
        assertTrue(available);
    }

    @Test
    public void should_return_false_when_parking_boy_not_have_available_parking_lot() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingBoyId("boy1"));
        List<ParkingLot> parkingLots = new ArrayList<>();
        Map<Ticket, Car> ticketCarMap = new HashMap<>();
        ticketCarMap.put(new Ticket(new CarId("粤B000TA"), new ParkingLotId("1")), new Car(new CarId("粤B000TA")));
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLot1.setTicketCarMap(ticketCarMap);
        parkingLots.add(parkingLot1);
        parkingBoy.setParkingLots(parkingLots);

        boolean available = parkingBoy.available();
        assertFalse(available);
    }
}
