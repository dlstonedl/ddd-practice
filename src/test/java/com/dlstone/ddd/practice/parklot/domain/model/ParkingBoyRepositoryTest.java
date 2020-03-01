package com.dlstone.ddd.practice.parklot.domain.model;

import com.dlstone.ddd.practice.parklot.infrastructure.ParkingBoyRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingBoyRepositoryTest {
    private ParkingBoyRepository parkingBoyRepository;

    @Before
    public void setUp() {
        parkingBoyRepository = new ParkingBoyRepositoryImpl();
    }

    @Test
    public void should_return_parking_boy() {
        ParkingBoy parkingBoy = parkingBoyRepository.getParkingBoy();
        assertNotNull(parkingBoy);
    }

    @Test
    public void should_save_parking_boy() {
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingBoyId("test"));
        parkingBoyRepository.saveParkingBoy(parkingBoy);
        ParkingBoy boy = parkingBoyRepository.getParkingBoy();
        assertEquals(parkingBoy, boy);
    }
}
