package com.dlstone.ddd.practice.parklot.parking.domain.finder;

import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyConfig;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyConfigRepository;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingStrategyName;
import com.dlstone.ddd.practice.parklot.parking.domain.policy.MaxIdleStrategy;
import com.dlstone.ddd.practice.parklot.parking.domain.policy.SortedStrategy;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLot;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotId;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotRepository;

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
