package com.dlstone.ddd.practice.parklot.domain.model.parking;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Data
public class ParkingLot {
    private final ParkingLotId id;
    private int capacity;
    private Map<Ticket, Car> ticketCarMap = new HashMap<>();

    public ParkingLot(ParkingLotId id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (availableLots() <= 0) {
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

        return ticketCarMap.remove(ticket);
    }

    private boolean validateTicket(Ticket ticket) {
        return ticketCarMap.containsKey(ticket);
    }

    int availableLots() {
        return capacity - ticketCarMap.size();
    }
}
