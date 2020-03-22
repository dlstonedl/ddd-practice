package com.dlstone.ddd.practice.parklot.parking.domain.core;

import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingManager;
import com.dlstone.ddd.practice.parklot.parking.domain.policy.SortedStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindParkingLotServiceTest {

    private FindParkingLotService findParkingLotService;

    @Before
    public void setUp() {
        findParkingLotService = new FindParkingLotService();
    }

    @Test
    public void should_return_parking_lot_when_given_parking_boy_specification() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("lot1"), 1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());

        ParkingLotFinderSpecification parkingLotFinderSpecification = mock(ParkingLotFinderSpecification.class);
        when(parkingLotFinderSpecification.newParkingLotFinder()).thenReturn(parkingBoy);

        ParkingLot parkingLot = findParkingLotService.findParkingLot(parkingLotFinderSpecification);
        Assert.assertEquals(parkingLot1.getId(), parkingLot.getId());
    }

    @Test
    public void should_return_available_parking_lot_when_given_parking_manager_specifivation() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("lot1"), 1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ParkingManager parkingManager = new ParkingManager(parkingBoys);

        ParkingLotFinderSpecification parkingLotFinderSpecification = mock(ParkingLotFinderSpecification.class);
        when(parkingLotFinderSpecification.newParkingLotFinder()).thenReturn(parkingManager);

        List<ParkingLot> availableParkingLots = findParkingLotService.findAvailableParkingLots(parkingLotFinderSpecification);
        Assert.assertEquals(1, availableParkingLots.size());
        Assert.assertEquals(parkingLot1.getId(), availableParkingLots.get(0).getId());
    }
}
