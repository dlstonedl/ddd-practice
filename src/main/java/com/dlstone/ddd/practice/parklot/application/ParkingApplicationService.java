package com.dlstone.ddd.practice.parklot.application;

import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingManagerId;
import com.dlstone.ddd.practice.parklot.domain.model.parking.*;

import java.util.List;

public class ParkingApplicationService {

    private ParkingBoyFactory parkingBoyFactory;
    private ParkingManagerFactory parkingManagerFactory;

    public ParkingApplicationService(ParkingBoyFactory parkingBoyFactory, ParkingManagerFactory parkingManagerFactory) {
        this.parkingBoyFactory = parkingBoyFactory;
        this.parkingManagerFactory = parkingManagerFactory;
    }

    public Ticket parkWithParkingBoy(ParkingBoyId parkingBoyId, Car car) {
        ParkingBoy parkingBoy = parkingBoyFactory.createParkingBoy(parkingBoyId);
        ParkingLot parkingLot = parkingBoy.selectParkingLot();
        return parkingLot.park(car);
    }

    public Ticket parkWithParkingManager(Car car) {
        ParkingManager parkingManager = parkingManagerFactory.createParkingManager(new ParkingManagerId("manager1"));
        ParkingLot parkingLot = parkingManager.selectParkingLot();
        return parkingLot.park(car);
    }

    public List<ParkingLot> getAvailableParkingLotsFromParkingManager() {
        ParkingManager parkingManager = parkingManagerFactory.createParkingManager(new ParkingManagerId("manager1"));
        return parkingManager.getAvailableParkingLots();
    }
}
