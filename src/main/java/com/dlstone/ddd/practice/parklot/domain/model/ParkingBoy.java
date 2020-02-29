package com.dlstone.ddd.practice.parklot.domain.model;

import com.dlstone.ddd.practice.common.ParkingLotException;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    public Ticket park(Car car) {
        return parkingLots
            .stream()
            .filter(parkingLot -> Objects.nonNull(parkingLot.park(car)))
            .findFirst()
            .map(parkingLot -> parkingLot.park(car))
            .orElseThrow(() -> new ParkingLotException("can not found a available lot"));
    }




}
