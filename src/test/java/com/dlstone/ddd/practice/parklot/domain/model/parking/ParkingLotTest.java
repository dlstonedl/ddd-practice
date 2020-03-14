package com.dlstone.ddd.practice.parklot.domain.model.parking;

import com.dlstone.ddd.practice.parklot.domain.model.parking.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParkingLotTest {

    @Test
    public void should_return_ticket_when_park_lot_is_available() {
        Car car = new Car(new CarId("粤B000TA"));
        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("1"), 3);
        Ticket parkTicket = parkingLot.park(car);
        Ticket ticket = new Ticket(new CarId("粤B000TA"), new ParkingLotId("1"));

        assertEquals(ticket, parkTicket);
        assertEquals(1, parkingLot.getTicketCarMap().size());
    }

    @Test
    public void should_return_null_when_park_lot_not_available() {
        Map<Ticket, Car> ticketCarMap = new HashMap<>();
        ticketCarMap.put(new Ticket(new CarId("粤B000TA"), new ParkingLotId("1")), new Car(new CarId("粤B000TA")));

        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLot.setTicketCarMap(ticketCarMap);

        Ticket ticket = parkingLot.park(new Car(new CarId("粤B000TB")));
        assertNull(ticket);
    }

    @Test
    public void should_return_car_when_ticket_is_valid() {
        Ticket ticket = new Ticket(new CarId("粤B000TA"), new ParkingLotId("1"));
        Car car = new Car(new CarId("粤B000TA"));

        Map<Ticket, Car> ticketCarMap = new HashMap<>();
        ticketCarMap.put(ticket, car);
        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLot.setTicketCarMap(ticketCarMap);

        Car takeCar = parkingLot.take(ticket);
        assertEquals(car, takeCar);
        assertEquals(0, parkingLot.getTicketCarMap().size());
    }

    @Test
    public void should_return_null_when_ticket_is_invalid() {
        Ticket ticket = new Ticket(new CarId("粤B000TA"), new ParkingLotId("1"));
        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("1"), 1);
        Car car = parkingLot.take(ticket);
        assertNull(car);
    }

}
