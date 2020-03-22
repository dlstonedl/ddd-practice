package com.dlstone.ddd.practice.parklot.parking.domain.finder;

import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoy;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyRepository;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.config.domain.ParkingStrategyName;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLot;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotId;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotRepository;
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
    private ParkingBoyRepository parkingBoyRepository;

    @Before
    public void setup() {
        parkingBoyRepository = mock(ParkingBoyRepository.class);
        parkingLotRepository = mock(ParkingLotRepository.class);
        parkingBoyFactory = new ParkingBoyFactory(parkingBoyRepository, parkingLotRepository);
    }

    @Test
    public void should_return_parking_boy() {
        ParkingBoy parkingBoyConfig = new ParkingBoy(new ParkingBoyId("boy1"));
        parkingBoyConfig.setParkingLotIds(Arrays.asList(new ParkingLotId("lot1")));
        parkingBoyConfig.setParkingStrategyName(ParkingStrategyName.SORTED);
        when(parkingBoyRepository.getParkingBoy(any())).thenReturn(parkingBoyConfig);

        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("lot1"), 1);
        when(parkingLotRepository.getParkingLots(anyList())).thenReturn(Arrays.asList(parkingLot));

        com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy parkingBoy = parkingBoyFactory.createParkingBoy(new ParkingBoyId("boy1"));
        assertEquals(1, parkingBoy.getParkingLots().size());
        assertEquals("SortedStrategy", parkingBoy.getStrategy().getClass().getSimpleName());
    }

}
