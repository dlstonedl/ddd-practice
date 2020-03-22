package com.dlstone.ddd.practice.parklot.parking.domain.finder;

import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoy;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyRepository;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingStrategyName;
import com.dlstone.ddd.practice.parklot.parking.domain.policy.MaxIdleStrategy;
import com.dlstone.ddd.practice.parklot.parking.domain.policy.SortedStrategy;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLot;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotId;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotRepository;

import java.util.List;

public class ParkingBoyFactory {

    private ParkingBoyRepository parkingBoyRepository;
    private ParkingLotRepository parkingLotRepository;

    public ParkingBoyFactory(ParkingBoyRepository parkingBoyRepository, ParkingLotRepository parkingLotRepository) {
        this.parkingBoyRepository = parkingBoyRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy createParkingBoy(ParkingBoyId parkingBoyId) {
        ParkingBoy parkingBoy = parkingBoyRepository.getParkingBoy(parkingBoyId);
        return translateParkingBoyConfigToParkingBoy(parkingBoy);
    }

    private com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy translateParkingBoyConfigToParkingBoy(ParkingBoy parkingBoy) {
        List<ParkingLot> parkingLots = createParkingLots(parkingBoy.getParkingLotIds());
        Strategy strategy = createParkingStrategy(parkingBoy.getParkingStrategyName());
        return new com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy(parkingLots, strategy);
    }

    private Strategy createParkingStrategy(ParkingStrategyName parkingStrategyName) {
        return parkingStrategyName == ParkingStrategyName.SORTED ? new SortedStrategy() : new MaxIdleStrategy();
    }

    private List<ParkingLot> createParkingLots(List<ParkingLotId> parkingLotIds) {
        return parkingLotRepository.getParkingLots(parkingLotIds);
    }
}
