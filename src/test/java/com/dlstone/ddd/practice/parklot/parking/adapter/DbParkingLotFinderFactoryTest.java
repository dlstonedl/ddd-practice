package com.dlstone.ddd.practice.parklot.parking.adapter;

import com.dlstone.ddd.practice.parklot.config.domain.ParkingBoyId;
import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DbParkingLotFinderFactoryTest {

    private DbParkingLotFinderFactory dbParkingLotFinderFactory;

    @Before
    public void setUp() {
        dbParkingLotFinderFactory = new DbParkingLotFinderFactory();
    }

    @Test
    public void should_return_a_parking_manager() {

    }

    @Test
    public void should_return_a_parking_boy() {
        ParkingLotFinder parkingBoy = dbParkingLotFinderFactory.newParkingBoy(new ParkingBoyId("boy1"));
        assertEquals(null, parkingBoy);
    }

}
