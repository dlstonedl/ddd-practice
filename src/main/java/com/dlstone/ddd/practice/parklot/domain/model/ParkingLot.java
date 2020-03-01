package com.dlstone.ddd.practice.parklot.domain.model;

import com.dlstone.ddd.practice.common.ParkingLotException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Data
@Slf4j
public class ParkingLot {
    private final ParkingLotId id;
    private final int capacity;
    private Map<Ticket, Car> ticketCarMap = new HashMap<>();

    public Ticket park(Car car) throws ParkingLotException {
        if (!available()) {
            log.error("{} lot not available, capacity is {} , car is {}", id, capacity, car);
            throw new ParkingLotException("parking lot not available");
        }

        Ticket ticket = new Ticket(car.getId(), id);
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car take(Ticket ticket) throws ParkingLotException {
        if (!validateTicket(ticket)) {
            log.error("invalid ticket: {} in {} lot", ticket, id);
            throw new ParkingLotException("invalid ticket");
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
