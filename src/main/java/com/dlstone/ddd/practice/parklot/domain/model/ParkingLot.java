package com.dlstone.ddd.practice.parklot.domain.model;

import com.dlstone.ddd.practice.parklot.common.ParkingLotId;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Data
@Slf4j
public class ParkingLot {
    private final ParkingLotId id;
    private final int capacity;
    private Map<Ticket, Car> ticketCarMap = new HashMap<>();

    public Ticket park(Car car) {
        if (!available()) {
            return null;
        }

        Ticket ticket = new Ticket(car.getId(), id);
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car take(Ticket ticket) {
        if (!validateTicket(ticket)) {
            return null;
        }

        return ticketCarMap.get(ticket);
    }

    private boolean validateTicket(Ticket ticket) {
        return ticketCarMap.containsKey(ticket);
    }

    private boolean available() {
        return capacity > ticketCarMap.size();
    }
}
