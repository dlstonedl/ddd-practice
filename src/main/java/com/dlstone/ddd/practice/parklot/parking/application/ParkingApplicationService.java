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
    private ParkingLotFinderFactory parkingLotFinderFactory;

    public Ticket parkWithParkingBoy(String parkingBoyId, Car car) {
        ParkingBoySpecification parkingLotFinderSpecification = new ParkingBoySpecification(parkingLotFinderFactory, parkingBoyId);
        ParkingLot parkingLot = findParkingLotService.findParkingLot(parkingLotFinderSpecification);
        if (Objects.isNull(parkingLot)) {
            return null;
        }
        Ticket ticket = parkingLot.park(car);
        parkingLotRepository.save(parkingLot);
        return ticket;
    }

    public Ticket parkWithParkingManager(Car car) {
        ParkingManagerSpecification parkingLotFinderSpecification = new ParkingManagerSpecification(parkingLotFinderFactory);
        ParkingLot parkingLot = findParkingLotService.findParkingLot(parkingLotFinderSpecification);
        if (Objects.isNull(parkingLot)) {
            return null;
        }
        Ticket ticket = parkingLot.park(car);
        parkingLotRepository.save(parkingLot);
        return ticket;
    }

    public List<ParkingLot> getAvailableParkingLotsFromParkingManager() {
        ParkingManagerSpecification parkingLotFinderSpecification = new ParkingManagerSpecification(parkingLotFinderFactory);
        return findParkingLotService.findAvailableParkingLots(parkingLotFinderSpecification);
    }
}
