package com.dlstone.ddd.practice.parklot.parking.domain;

import com.dlstone.ddd.practice.parklot.config.domain.ParkingManagerConfig;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingManagerConfigRepository;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingManagerId;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingManagerFactory {

    private ParkingManagerConfigRepository parkingManagerConfigRepository;
    private ParkingBoyFactory parkingBoyFactory;

    public ParkingManagerFactory(ParkingManagerConfigRepository parkingManagerConfigRepository, ParkingBoyFactory parkingBoyFactory) {
        this.parkingManagerConfigRepository = parkingManagerConfigRepository;
        this.parkingBoyFactory = parkingBoyFactory;
    }

    public ParkingManager createParkingManager(ParkingManagerId parkingManagerId) {
        ParkingManagerConfig parkingManagerConfig = parkingManagerConfigRepository.getParkingManagerConfig(parkingManagerId);
        return translateParkingManagerConfigToParkingManager(parkingManagerConfig);
    }

    private ParkingManager translateParkingManagerConfigToParkingManager(ParkingManagerConfig parkingManagerConfig) {
        List<ParkingBoy> parkingBoys = parkingManagerConfig.getParkingBoyIds()
            .stream()
            .map(parkingBoyFactory::createParkingBoy)
            .collect(Collectors.toList());
        return new ParkingManager(parkingBoys);
    }
}
