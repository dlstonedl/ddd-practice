package com.dlstone.ddd.practice.parklot.config.application;

import com.dlstone.ddd.practice.parklot.config.domain.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParkingConfigApplicationService {
    private ParkingManagerRepository parkingManagerRepository;
    private ParkingBoyRepository parkingBoyRepository;

    public ParkingManager getParkingManager() {
        return parkingManagerRepository.getParkingManager();
    }

    public ParkingBoy getParkingBoy(ParkingBoyId parkingBoyId){
        return parkingBoyRepository.getParkingBoy(parkingBoyId);
    }
}
