package com.dlstone.ddd.practice.parklot.parking.adapter;

import com.dlstone.ddd.practice.parklot.config.application.ParkingConfigApplicationService;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoy;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingManager;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingStrategyName;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLot;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinder;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinderFactory;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotRepository;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.Strategy;
import com.dlstone.ddd.practice.parklot.parking.domain.policy.MaxIdleStrategy;
import com.dlstone.ddd.practice.parklot.parking.domain.policy.SortedStrategy;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DbParkingLotFinderFactory implements ParkingLotFinderFactory {

    private ParkingConfigApplicationService parkingConfigApplicationService;
    private ParkingLotRepository parkingLotRepository;

    @Override
    public ParkingLotFinder newParkingManager() {
        ParkingManager parkingManager = parkingConfigApplicationService.getParkingManager();

        List<com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy> parkingBoys = parkingManager.getParkingBoyIds()
            .stream()
            .map(this::newParkingBoy)
            .map(parkingLotFinder -> (com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy) parkingLotFinder)
            .collect(Collectors.toList());

        return new com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingManager(parkingBoys);
    }

    @Override
    public ParkingLotFinder newParkingBoy(ParkingBoyId parkingBoyId) {
        ParkingBoy parkingBoy = parkingConfigApplicationService.getParkingBoy(parkingBoyId);

        List<ParkingLot> parkingLots = parkingLotRepository.getParkingLots(parkingBoy.getParkingLotIds());
        Strategy strategy = parkingBoy.getParkingStrategyName() == ParkingStrategyName.SORTED ? new SortedStrategy() : new MaxIdleStrategy();

        return new com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy(parkingLots, strategy);
    }
}
