package com.dlstone.ddd.practice.parklot.parking.application;

import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.parking.domain.core.*;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoy;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingBoyFactory;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingManager;
import com.dlstone.ddd.practice.parklot.parking.domain.finder.ParkingManagerFactory;
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
    private ParkingBoyFactory parkingBoyFactory;
    private ParkingManagerFactory parkingManagerFactory;
    private ParkingLotRepository parkingLotRepository;

    @Before
    public void setUp() {
        parkingBoyFactory = mock(ParkingBoyFactory.class);
        parkingManagerFactory = mock(ParkingManagerFactory.class);
        parkingLotRepository = mock(ParkingLotRepository.class);
        parkingApplicationService = new ParkingApplicationService(parkingBoyFactory, parkingManagerFactory, parkingLotRepository);
    }

    @Test
    public void should_return_ticket_when_user_select_one_parking_boy_to_park() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(new ParkingLotId("lot1"), 1);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());
        when(parkingBoyFactory.createParkingBoy(any())).thenReturn(parkingBoy);
        Mockito.doNothing().when(parkingLotRepository).updateParkingLot(any());

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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new SortedStrategy());

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ParkingManager parkingManager = new ParkingManager(parkingBoys);

        when(parkingManagerFactory.createParkingManager(any())).thenReturn(parkingManager);

        List<ParkingLot> availableParkingLots = parkingApplicationService.getAvailableParkingLotsFromParkingManager();
        assertEquals(1, availableParkingLots.size());
    }

}
