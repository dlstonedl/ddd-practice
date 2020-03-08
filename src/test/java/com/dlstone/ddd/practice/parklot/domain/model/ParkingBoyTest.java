package com.dlstone.ddd.practice.parklot.domain.model;

import com.dlstone.ddd.practice.parklot.common.exception.ParkingLotException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ParkingBoyTest {

    @Test
    public void should_return_ticket_when_have_lot_available() throws ParkingLotException {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingBoyId("boy1"));
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(new ParkingLotId("lot1"), 2));
        parkingBoy.setParkingLots(parkingLots);

        Car car = new Car(new CarId("粤B000TA"));
        Ticket ticket = new Ticket(new CarId("粤B000TA"), new ParkingLotId("lot1"));
        Ticket parkTicket = parkingBoy.park(car);
        assertEquals(ticket, parkTicket);
    }

    @Test
    public void should_return_null_when_no_lot_available() throws ParkingLotException {
        Map<Ticket, Car> ticketCarMap = new HashMap<>();
        ticketCarMap.put(new Ticket(new CarId("粤B000TA"), new ParkingLotId("lot1")), new Car(new CarId("粤B000TA")));
        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("lot1"), 1);
        parkingLot.setTicketCarMap(ticketCarMap);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(new ParkingBoyId("boy1"));
        parkingBoy.setParkingLots(parkingLots);

        Car car = new Car(new CarId("粤B000TA"));
        Ticket ticket = parkingBoy.park(car);
        assertNull(ticket);
    }
}
