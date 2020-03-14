package com.dlstone.ddd.practice.parklot.domain.model.parking;

import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingBoyConfig;
import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingBoyConfigRepository;
import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingStrategyName;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingBoyFactoryTest {

    private ParkingBoyFactory parkingBoyFactory;
    private ParkingLotRepository parkingLotRepository;
    private ParkingBoyConfigRepository parkingBoyConfigRepository;

    @Before
    public void setup() {
        parkingBoyConfigRepository = mock(ParkingBoyConfigRepository.class);
        parkingLotRepository = mock(ParkingLotRepository.class);
        parkingBoyFactory = new ParkingBoyFactory(parkingBoyConfigRepository, parkingLotRepository);
    }

    @Test
    public void should_return_parking_boy() {
        ParkingBoyConfig parkingBoyConfig = new ParkingBoyConfig(new ParkingBoyId("boy1"));
        parkingBoyConfig.setParkingLotIds(Arrays.asList(new ParkingLotId("lot1")));
        parkingBoyConfig.setParkingStrategyName(ParkingStrategyName.SORTED);
        when(parkingBoyConfigRepository.getParkingBoyConfig(any())).thenReturn(parkingBoyConfig);

        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("lot1"), 1);
        when(parkingLotRepository.getParkingLots(anyList())).thenReturn(Arrays.asList(parkingLot));

        ParkingBoy parkingBoy = parkingBoyFactory.createParkingBoy(new ParkingBoyId("boy1"));
        assertEquals(1, parkingBoy.getParkingLots().size());
        assertEquals("SortedStrategy", parkingBoy.getStrategy().getClass().getSimpleName());
    }

}
