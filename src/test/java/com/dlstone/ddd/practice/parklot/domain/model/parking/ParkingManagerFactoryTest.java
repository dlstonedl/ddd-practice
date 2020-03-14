package com.dlstone.ddd.practice.parklot.domain.model.parking;

import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingManagerConfig;
import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingManagerConfigRepository;
import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingManagerId;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingManagerFactoryTest {

    private ParkingManagerFactory parkingManagerFactory;
    private ParkingBoyFactory parkingBoyFactory;
    private ParkingManagerConfigRepository managerConfigRepository;

    @Before
    public void setup() {
        managerConfigRepository = mock(ParkingManagerConfigRepository.class);
        parkingBoyFactory = mock(ParkingBoyFactory.class);
        parkingManagerFactory = new ParkingManagerFactory(managerConfigRepository, parkingBoyFactory);
    }

    @Test
    public void should_return_parking_manager() {
        ParkingManagerConfig parkingManagerConfig = new ParkingManagerConfig(new ParkingManagerId("manager1"));
        parkingManagerConfig.setParkingBoyIds(Arrays.asList(new ParkingBoyId("boy1")));
        when(managerConfigRepository.getParkingManagerConfig(any())).thenReturn(parkingManagerConfig);

        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("lot1"), 1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());
        when(parkingBoyFactory.createParkingBoy(any())).thenReturn(parkingBoy);

        ParkingManager parkingManager = parkingManagerFactory.createParkingManager(new ParkingManagerId("manager1"));
        assertEquals(1, parkingManager.getParkingBoys().size());
        assertEquals(1, parkingManager.getParkingBoys().get(0).getParkingLots().size());
    }
}
