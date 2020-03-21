package com.dlstone.ddd.practice.parklot.parking.application;

import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingManagerId;
import com.dlstone.ddd.practice.parklot.domain.model.parking.*;

import java.util.List;
import java.util.Objects;

public class ParkingApplicationService {

    private ParkingBoyFactory parkingBoyFactory;
    private ParkingManagerFactory parkingManagerFactory;
    private ParkingLotRepository parkingLotRepository;

    public ParkingApplicationService(ParkingBoyFactory parkingBoyFactory, ParkingManagerFactory parkingManagerFactory, ParkingLotRepository parkingLotRepository) {
        this.parkingBoyFactory = parkingBoyFactory;
        this.parkingManagerFactory = parkingManagerFactory;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Ticket parkWithParkingBoy(ParkingBoyId parkingBoyId, Car car) {
        ParkingBoy parkingBoy = parkingBoyFactory.createParkingBoy(parkingBoyId);
        ParkingLot parkingLot = parkingBoy.selectParkingLot();
        if (Objects.isNull(parkingLot)) {
            return null;
        }
        Ticket ticket = parkingLot.park(car);
        parkingLotRepository.updateParkingLot(parkingLot);
        return ticket;
    }

    public Ticket parkWithParkingManager(Car car) {
        ParkingManager parkingManager = parkingManagerFactory.createParkingManager(new ParkingManagerId("manager1"));
        ParkingLot parkingLot = parkingManager.selectParkingLot();
        if (Objects.isNull(parkingLot)) {
            return null;
        }
        Ticket ticket = parkingLot.park(car);
        parkingLotRepository.updateParkingLot(parkingLot);
        return ticket;
    }

    public List<ParkingLot> getAvailableParkingLotsFromParkingManager() {
        ParkingManager parkingManager = parkingManagerFactory.createParkingManager(new ParkingManagerId("manager1"));
        return parkingManager.getAvailableParkingLots();
    }
}
