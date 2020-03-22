package com.dlstone.ddd.practice.parklot.parking.application;

import com.dlstone.ddd.practice.parklot.parking.domain.core.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class ParkingApplicationService {

    private ParkingLotRepository parkingLotRepository;
    private ParkingLotFinderFactory parkingLotFinderFactory;

    public Ticket parkWithParkingBoy(String parkingBoyId, Car car) {
        ParkingLotFinder parkingLotFinder = parkingLotFinderFactory.newParkingBoy(parkingBoyId);
        ParkingLot parkingLot = parkingLotFinder.findParkingLotToPark();
        if (Objects.isNull(parkingLot)) {
            return null;
        }
        Ticket ticket = parkingLot.park(car);
        parkingLotRepository.updateParkingLot(parkingLot);
        return ticket;
    }

    public Ticket parkWithParkingManager(Car car) {
        ParkingLotFinder parkingLotFinder = parkingLotFinderFactory.newParkingManager();
        ParkingLot parkingLot = parkingLotFinder.findParkingLotToPark();
        if (Objects.isNull(parkingLot)) {
            return null;
        }
        Ticket ticket = parkingLot.park(car);
        parkingLotRepository.updateParkingLot(parkingLot);
        return ticket;
    }

    public List<ParkingLot> getAvailableParkingLotsFromParkingManager() {
        ParkingLotFinder parkingLotFinder = parkingLotFinderFactory.newParkingManager();
        return parkingLotFinder.getAvailableParkingLots();
    }
}
