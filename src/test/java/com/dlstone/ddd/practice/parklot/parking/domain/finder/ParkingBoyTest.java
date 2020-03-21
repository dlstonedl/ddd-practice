package com.dlstone.ddd.practice.parklot.parking.domain.finder;

import com.dlstone.ddd.practice.parklot.parking.domain.MaxIdleStrategy;
import com.dlstone.ddd.practice.parklot.parking.domain.SortedStrategy;
import com.dlstone.ddd.practice.parklot.parking.domain.core.*;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ParkingBoyTest {

    @Test
    public void should_return_parking_lot_when_is_junior_parking_boy() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        Map<Ticket, Car> ticketCarMap = new HashMap<>();
        ticketCarMap.put(new Ticket(new CarId("粤B000TA"), new ParkingLotId("1")), new Car(new CarId("粤B000TA")));
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLot1.setTicketCarMap(ticketCarMap);
        ParkingLot parkingLot2 = new ParkingLot(new ParkingLotId("2"), 3);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy JuniorParkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());
        ParkingLot parkingLot = JuniorParkingBoy.selectParkingLot();
        assertEquals(parkingLot2, parkingLot);
    }

    @Test
    public void should_return_parking_lot_when_is_senior_parking_boy() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        ParkingLot parkingLot2 = new ParkingLot(new ParkingLotId("2"), 3);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy seniorParkingBoy = new ParkingBoy(parkingLots, new MaxIdleStrategy());
        ParkingLot parkingLot = seniorParkingBoy.selectParkingLot();
        assertEquals(parkingLot2, parkingLot);
    }

    @Test
    public void should_return_true_when_parking_boy_have_available_parking_lot() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());
        boolean available = parkingBoy.available();
        assertTrue(available);
    }

    @Test
    public void should_return_false_when_parking_boy_not_have_available_parking_lot() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        Map<Ticket, Car> ticketCarMap = new HashMap<>();
        ticketCarMap.put(new Ticket(new CarId("粤B000TA"), new ParkingLotId("1")), new Car(new CarId("粤B000TA")));
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLot1.setTicketCarMap(ticketCarMap);
        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());
        boolean available = parkingBoy.available();
        assertFalse(available);
    }

    @Test
    public void should_return_parking_lots_when_get_available_parking_lots() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());

        List<ParkingLot> availableParkingLots = parkingBoy.getAvailableParkingLots();
        assertEquals(1, availableParkingLots.size());
    }
}
