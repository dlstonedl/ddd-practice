package com.dlstone.ddd.practice.parklot.parking.adapter;

import com.dlstone.ddd.practice.parklot.config.application.ParkingConfigApplicationService;
import com.dlstone.ddd.practice.parklot.config.domain.*;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLot;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinder;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotId;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotRepository;
import com.dlstone.ddd.practice.parklot.parking.domain.policy.SortedStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class DbParkingLotFinderFactoryTest {

    private DbParkingLotFinderFactory dbParkingLotFinderFactory;
    private ParkingConfigApplicationService parkingConfigApplicationService;
    private ParkingLotRepository parkingLotRepository;

    @Before
    public void setUp() {
        parkingConfigApplicationService = mock(ParkingConfigApplicationService.class);
        parkingLotRepository = mock(ParkingLotRepository.class);
        dbParkingLotFinderFactory = new DbParkingLotFinderFactory(parkingConfigApplicationService, parkingLotRepository);
    }

    @Test
    public void should_return_a_parking_manager() {
        ParkingManager parkingManager = new ParkingManager(new ParkingManagerId("manager1"));
        parkingManager.setParkingBoyIds(Arrays.asList(new ParkingBoyId("boy1")));
        when(parkingConfigApplicationService.getParkingManager()).thenReturn(parkingManager);

        dbParkingLotFinderFactory = mock(DbParkingLotFinderFactory.class, withSettings().useConstructor(parkingConfigApplicationService, parkingLotRepository));
        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("lot1"), 1);
        com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy parkingBoy = new com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy(Arrays.asList(parkingLot), new SortedStrategy());
        when(dbParkingLotFinderFactory.newParkingBoy(any())).thenReturn((ParkingLotFinder) parkingBoy);
        when(dbParkingLotFinderFactory.newParkingManager()).thenCallRealMethod();

        ParkingLotFinder parkingLotFinder = this.dbParkingLotFinderFactory.newParkingManager();
        assertNotNull(parkingLotFinder);
    }

    @Test
    public void should_return_a_parking_boy() {
        ParkingBoyId BOY_ID = new ParkingBoyId("boy1");
        ParkingBoy parkingBoy = new ParkingBoy(BOY_ID);
        parkingBoy.setParkingLotIds(Arrays.asList(new ParkingLotId("lot1")));
        parkingBoy.setParkingStrategyName(ParkingStrategyName.SORTED);
        when(parkingConfigApplicationService.getParkingBoy(eq(BOY_ID))).thenReturn(parkingBoy);

        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("lot1"), 1);
        when(parkingLotRepository.getParkingLots(any())).thenReturn(Arrays.asList(parkingLot));

        ParkingLotFinder parkingLotFinder = dbParkingLotFinderFactory.newParkingBoy(BOY_ID);
        assertNotNull(parkingLotFinder);
    }

}
