package com.dlstone.ddd.practice.parklot.parking.domain.finder;

import com.dlstone.ddd.practice.parklot.config.domain.ParkingManager;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingManagerRepository;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingManagerId;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingManagerFactory {

    private ParkingManagerRepository parkingManagerRepository;
    private ParkingBoyFactory parkingBoyFactory;

    public ParkingManagerFactory(ParkingManagerRepository parkingManagerRepository, ParkingBoyFactory parkingBoyFactory) {
        this.parkingManagerRepository = parkingManagerRepository;
        this.parkingBoyFactory = parkingBoyFactory;
    }

    public com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingManager createParkingManager() {
        ParkingManager parkingManager = parkingManagerRepository.getParkingManager();
        return translateParkingManagerConfigToParkingManager(parkingManager);
    }

    private com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingManager translateParkingManagerConfigToParkingManager(ParkingManager parkingManager) {
        List<ParkingBoy> parkingBoys = parkingManager.getParkingBoyIds()
            .stream()
            .map(parkingBoyFactory::createParkingBoy)
            .collect(Collectors.toList());
        return new com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingManager(parkingBoys);
    }
}
