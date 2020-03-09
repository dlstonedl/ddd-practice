package com.dlstone.ddd.practice.parklot.domain.model;

import com.dlstone.ddd.practice.parklot.common.exception.ParkingLotException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Data
@Slf4j
public class ParkingLot {
    @EqualsAndHashCode.Include
    private final ParkingLotId id;
    private final int capacity;
    private Map<Ticket, Car> ticketCarMap = new HashMap<>();

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
