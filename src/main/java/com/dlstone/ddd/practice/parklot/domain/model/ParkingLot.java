package com.dlstone.ddd.practice.parklot.domain.model;

import com.dlstone.ddd.practice.parklot.common.ParkingLotException;
import com.dlstone.ddd.practice.parklot.common.ParkingLotId;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Slf4j
public class ParkingLot {
    private final ParkingLotId id;
    private final int capacity;
    private List<Car> cars = new ArrayList<>();

    public Ticket park(Car car) {
        if (!available()) {
            log.error("parkingLot not available, capacity={}, car={}", capacity, car);
            throw new ParkingLotException("parkingLot not available");
        }
        cars.add(car);
        return new Ticket(car.getId());
    }

    public Car take(Ticket ticket) {
        return cars
            .stream()
            .filter(car -> Objects.equals(car.getId(), ticket.getCarId()))
            .findFirst()
            .orElse(null);
    }

    private boolean available() {
        return capacity > cars.size();
    }
}
