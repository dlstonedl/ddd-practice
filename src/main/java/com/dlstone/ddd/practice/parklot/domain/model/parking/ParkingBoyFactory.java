package com.dlstone.ddd.practice.parklot.domain.model.parking;

import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingBoyConfig;
import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingBoyConfigRepository;
import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingStrategyName;

import java.util.List;

public class ParkingBoyFactory {

    private ParkingBoyConfigRepository parkingBoyConfigRepository;
    private ParkingLotRepository parkingLotRepository;

    public ParkingBoyFactory(ParkingBoyConfigRepository parkingBoyConfigRepository, ParkingLotRepository parkingLotRepository) {
        this.parkingBoyConfigRepository = parkingBoyConfigRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingBoy createParkingBoy(ParkingBoyId parkingBoyId) {
        ParkingBoyConfig parkingBoyConfig = parkingBoyConfigRepository.getParkingBoyConfig(parkingBoyId);
        return translateParkingBoyConfigToParkingBoy(parkingBoyConfig);
    }

    private ParkingBoy translateParkingBoyConfigToParkingBoy(ParkingBoyConfig parkingBoyConfig) {
        List<ParkingLot> parkingLots = createParkingLots(parkingBoyConfig.getParkingLotIds());
        Strategy strategy = createParkingStrategy(parkingBoyConfig.getParkingStrategyName());
        return new ParkingBoy(parkingLots, strategy);
    }

    private Strategy createParkingStrategy(ParkingStrategyName parkingStrategyName) {
        return parkingStrategyName == ParkingStrategyName.SORTED ? new SortedStrategy() : new MaxIdleStrategy();
    }

    private List<ParkingLot> createParkingLots(List<ParkingLotId> parkingLotIds) {
        return parkingLotRepository.getParkingLots(parkingLotIds);
    }
}
