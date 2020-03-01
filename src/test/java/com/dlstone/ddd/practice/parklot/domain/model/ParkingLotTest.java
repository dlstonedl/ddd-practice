package com.dlstone.ddd.practice.parklot.domain.model;

import com.dlstone.ddd.practice.common.ParkingLotException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ParkingLotTest {

    @Test
    public void should_return_ticket_when_park_lot_is_available() throws ParkingLotException {
        Car car = new Car(new CarId("粤B000TA"));
        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("1"), 3);
        Ticket parkTicket = parkingLot.park(car);
        Ticket ticket = new Ticket(new CarId("粤B000TA"), new ParkingLotId("1"));

        assertEquals(ticket, parkTicket);
    }

    @Test(expected = ParkingLotException.class)
    public void should_throw_exception_when_park_lot_not_available() throws ParkingLotException {
        Map<Ticket, Car> ticketCarMap = new HashMap<>();
        ticketCarMap.put(new Ticket(new CarId("粤B000TA"), new ParkingLotId("1")), new Car(new CarId("粤B000TA")));

        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLot.setTicketCarMap(ticketCarMap);

        parkingLot.park(new Car(new CarId("粤B000TB")));
    }

    @Test
    public void should_return_car_when_ticket_is_valid() throws ParkingLotException {
        Ticket ticket = new Ticket(new CarId("粤B000TA"), new ParkingLotId("1"));
        Car car = new Car(new CarId("粤B000TA"));

        Map<Ticket, Car> ticketCarMap = new HashMap<>();
        ticketCarMap.put(ticket, car);
        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLot.setTicketCarMap(ticketCarMap);

        Car takeCar = parkingLot.take(ticket);
        assertEquals(car, takeCar);
    }

    @Test(expected = ParkingLotException.class)
    public void should_throw_exception_when_ticket_is_invalid() throws ParkingLotException {
        Ticket ticket = new Ticket(new CarId("粤B000TA"), new ParkingLotId("1"));
        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("1"), 1);
        parkingLot.take(ticket);
    }

}
