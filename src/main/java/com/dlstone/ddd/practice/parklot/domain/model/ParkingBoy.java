package com.dlstone.ddd.practice.parklot.domain.model;

import com.dlstone.ddd.practice.parklot.common.exception.ParkingLotException;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class ParkingBoy {
    private final ParkingBoyId id;
    private List<ParkingLot> parkingLots;

    public Ticket park(Car car) {
        return parkingLots
            .stream()
            .filter(parkingLot -> Objects.nonNull(park(parkingLot, car)))
            .findFirst()
            .map(parkingLot -> park(parkingLot, car))
            .orElseThrow(() -> new RuntimeException("can not found a available lot"));
    }

    private Ticket park(ParkingLot parkingLot, Car car) {
        try {
            return parkingLot.park(car);
        } catch (ParkingLotException e) {
            return null;
        }
    }




}
