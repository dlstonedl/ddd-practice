package com.dlstone.ddd.practice.parklot.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class ParkingBoy {
    @EqualsAndHashCode.Include
    private final ParkingBoyId id;
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public Ticket park(Car car) {
        return parkingLots.stream()
            .filter(parkingLot -> parkingLot.availableLots() > 0)
            .findFirst()
            .map(parkingLot -> parkingLot.park(car))
            .orElse(null);
    }
}
