package com.dlstone.ddd.practice.parklot.parking.application;

import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingManagerId;
import com.dlstone.ddd.practice.parklot.parking.domain.core.Car;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLot;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotRepository;
import com.dlstone.ddd.practice.parklot.parking.domain.core.Ticket;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoyFactory;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingManager;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingManagerFactory;

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
        ParkingManager parkingManager = parkingManagerFactory.createParkingManager();
        ParkingLot parkingLot = parkingManager.selectParkingLot();
        if (Objects.isNull(parkingLot)) {
            return null;
        }
        Ticket ticket = parkingLot.park(car);
        parkingLotRepository.updateParkingLot(parkingLot);
        return ticket;
    }

    public List<ParkingLot> getAvailableParkingLotsFromParkingManager() {
        ParkingManager parkingManager = parkingManagerFactory.createParkingManager();
        return parkingManager.getAvailableParkingLots();
    }
}
