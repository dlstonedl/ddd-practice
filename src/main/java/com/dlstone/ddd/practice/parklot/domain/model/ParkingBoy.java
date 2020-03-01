package com.dlstone.ddd.practice.parklot.domain.model;

import com.dlstone.ddd.practice.parklot.common.exception.ParkingLotException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Slf4j
public class ParkingBoy {
    @EqualsAndHashCode.Include
    private final ParkingBoyId id;
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public Ticket park(Car car) throws ParkingLotException {
        Ticket ticket = null;
        for (ParkingLot parkingLot : parkingLots) {
            ticket = park(parkingLot, car);
            if (Objects.nonNull(ticket)) {
                break;
            }
        }

        if (Objects.isNull(ticket)) {
            log.error("no lot available");
            throw new ParkingLotException("no lot available");
        }

        return ticket;
    }

    public ParkingLot getParkingLot(Ticket ticket) throws ParkingLotException {
        return parkingLots
            .stream()
            .filter(parkingLot -> Objects.equals(parkingLot.getId(), ticket.getParkingLotId()))
            .findFirst()
            .orElseThrow(() -> new ParkingLotException("lot not found") );
    }

    private Ticket park(ParkingLot parkingLot, Car car) {
        try {
            return parkingLot.park(car);
        } catch (ParkingLotException e) {
            return null;
        }
    }
}
