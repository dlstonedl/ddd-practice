package com.dlstone.ddd.practice.parklot.domain.model.application;

import com.dlstone.ddd.practice.parklot.application.ParkingApplicationService;
import com.dlstone.ddd.practice.parklot.domain.model.config.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.domain.model.parking.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingApplicationServiceTest {

    private ParkingApplicationService parkingApplicationService;
    private ParkingBoyFactory parkingBoyFactory;
    private ParkingManagerFactory parkingManagerFactory;

    @Before
    public void setUp() {
        parkingBoyFactory = mock(ParkingBoyFactory.class);
        parkingManagerFactory = mock(ParkingManagerFactory.class);
        parkingApplicationService = new ParkingApplicationService(parkingBoyFactory, parkingManagerFactory);
    }

    @Test
    public void should_return_ticket_when_user_select_one_parking_boy_to_park() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("lot1"), 1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());
        when(parkingBoyFactory.createParkingBoy(any())).thenReturn(parkingBoy);

        Car car = new Car(new CarId("粤B000TA"));
        Ticket ticket = parkingApplicationService.parkWithParkingBoy(new ParkingBoyId("boy1"), car);
        assertEquals("粤B000TA", ticket.getCarId().getValue());
        assertEquals("lot1", ticket.getParkingLotId().getValue());
    }

    @Test
    public void should_return_ticket_when_user_select_parking_manager_to_park() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("lot1"), 1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ParkingManager parkingManager = new ParkingManager(parkingBoys);

        when(parkingManagerFactory.createParkingManager(any())).thenReturn(parkingManager);
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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ParkingManager parkingManager = new ParkingManager(parkingBoys);

        when(parkingManagerFactory.createParkingManager(any())).thenReturn(parkingManager);

        List<ParkingLot> availableParkingLots = parkingApplicationService.getAvailableParkingLotsFromParkingManager();
        assertEquals(1, availableParkingLots.size());
    }

}
