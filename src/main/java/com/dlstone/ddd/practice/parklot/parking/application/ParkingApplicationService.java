package com.dlstone.ddd.practice.parklot.parking.application;

import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.parking.domain.core.*;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingManager;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class ParkingApplicationService {

    private ParkingLotRepository parkingLotRepository;
    private ParkingLotFinderFactory parkingLotFinderFactory;

    public Ticket parkWithParkingBoy(ParkingBoyId parkingBoyId, Car car) {
        ParkingBoy parkingBoy = (ParkingBoy) parkingLotFinderFactory.newParkingBoy(parkingBoyId);
        ParkingLot parkingLot = parkingBoy.selectParkingLot();
        if (Objects.isNull(parkingLot)) {
            return null;
        }
        Ticket ticket = parkingLot.park(car);
        parkingLotRepository.updateParkingLot(parkingLot);
        return ticket;
    }

    public Ticket parkWithParkingManager(Car car) {
        ParkingManager parkingManager = (ParkingManager) parkingLotFinderFactory.newParkingManager();
        ParkingLot parkingLot = parkingManager.selectParkingLot();
        if (Objects.isNull(parkingLot)) {
            return null;
        }
        Ticket ticket = parkingLot.park(car);
        parkingLotRepository.updateParkingLot(parkingLot);
        return ticket;
    }

    public List<ParkingLot> getAvailableParkingLotsFromParkingManager() {
        ParkingManager parkingManager = (ParkingManager) parkingLotFinderFactory.newParkingManager();
        return parkingManager.getAvailableParkingLots();
    }
}
