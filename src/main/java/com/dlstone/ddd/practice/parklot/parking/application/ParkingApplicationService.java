package com.dlstone.ddd.practice.parklot.parking.application;

import com.dlstone.ddd.practice.parklot.parking.domain.core.*;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoySpecification;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingManagerSpecification;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class ParkingApplicationService {

    private ParkingLotRepository parkingLotRepository;
    private FindParkingLotService findParkingLotService;

    public Ticket parkWithParkingBoy(String parkingBoyId, Car car) {
        ParkingLot parkingLot = findParkingLotService.findParkingLot(new ParkingBoySpecification(parkingBoyId));
        if (Objects.isNull(parkingLot)) {
            return null;
        }
        Ticket ticket = parkingLot.park(car);
        parkingLotRepository.updateParkingLot(parkingLot);
        return ticket;
    }

    public Ticket parkWithParkingManager(Car car) {
        ParkingLot parkingLot = findParkingLotService.findParkingLot(new ParkingManagerSpecification());
        if (Objects.isNull(parkingLot)) {
            return null;
        }
        Ticket ticket = parkingLot.park(car);
        parkingLotRepository.updateParkingLot(parkingLot);
        return ticket;
    }

    public List<ParkingLot> getAvailableParkingLotsFromParkingManager() {
        return findParkingLotService.findAvailableParkingLots(new ParkingManagerSpecification());
    }
}
