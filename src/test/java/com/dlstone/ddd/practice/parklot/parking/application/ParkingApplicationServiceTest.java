package com.dlstone.ddd.practice.parklot.parking.application;

import com.dlstone.ddd.practice.parklot.parking.domain.core.*;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingManager;
import com.dlstone.ddd.practice.parklot.parking.domain.policy.SortedStrategy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingApplicationServiceTest {

    private ParkingApplicationService parkingApplicationService;
    private ParkingLotRepository parkingLotRepository;
    private FindParkingLotService findParkingLotService;

    @Before
    public void setUp() {
        findParkingLotService = mock(FindParkingLotService.class);
        parkingLotRepository = mock(ParkingLotRepository.class);
        parkingApplicationService = new ParkingApplicationService(parkingLotRepository, findParkingLotService);
    }

    @Test
    public void should_return_ticket_when_user_select_one_parking_boy_to_park() {
        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("lot1"), 1);
        when(findParkingLotService.findParkingLot(any())).thenReturn(parkingLot);
        Mockito.doNothing().when(parkingLotRepository).updateParkingLot(any());

        Car car = new Car(new CarId("粤B000TA"));
        Ticket ticket = parkingApplicationService.parkWithParkingBoy("boy1", car);
        assertEquals("粤B000TA", ticket.getCarId().getValue());
        assertEquals("lot1", ticket.getParkingLotId().getValue());
    }

    @Test
    public void should_return_ticket_when_user_select_parking_manager_to_park() {
        ParkingLot parkingLot = new ParkingLot(new ParkingLotId("lot1"), 1);
        when(findParkingLotService.findParkingLot(any())).thenReturn(parkingLot);
        Mockito.doNothing().when(parkingLotRepository).updateParkingLot(any());

        Car car = new Car(new CarId("粤B000TA"));
        Ticket ticket = parkingApplicationService.parkWithParkingManager(car);
        assertEquals("粤B000TA", ticket.getCarId().getValue());
        assertEquals("lot1", ticket.getParkingLotId().getValue());
    }

    @Test
    public void should_return_parking_lots_when_get_available_parking_lots_from_parking_manager() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("lot1"), 1);
        parkingLots.add(parkingLot1);

        when(findParkingLotService.findAvailableParkingLots(any())).thenReturn(parkingLots);

        List<ParkingLot> availableParkingLots = parkingApplicationService.getAvailableParkingLotsFromParkingManager();
        assertEquals(1, availableParkingLots.size());
    }

}
