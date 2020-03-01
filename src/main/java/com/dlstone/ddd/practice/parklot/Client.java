package com.dlstone.ddd.practice.parklot;

import com.dlstone.ddd.practice.parklot.common.exception.ParkingLotException;
import com.dlstone.ddd.practice.parklot.domain.model.*;
import com.dlstone.ddd.practice.parklot.infrastructure.ParkingBoyRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

    private static ParkingBoyRepository parkingBoyRepository = new ParkingBoyRepositoryImpl();

    public static void main(String[] args) throws ParkingLotException {
        Car car = new Car(new CarId("粤B000TA"));

        Ticket ticket = park(car);
        log.info("park ticket: {}", ticket);

        Car takeCar = take(ticket);
        log.info("take car: {}", takeCar);
    }

    private static Ticket park(Car car) {
        ParkingBoy parkingBoy = parkingBoyRepository.getParkingBoy();
        Ticket ticket = parkingBoy.park(car);
        parkingBoyRepository.saveParkingBoy(parkingBoy);
        return ticket;
    }

    private static Car take(Ticket ticket) throws ParkingLotException {
        ParkingBoy parkingBoy = parkingBoyRepository.getParkingBoy();
        ParkingLot parkingLot = parkingBoy.getParkingLot(ticket);
        Car car = parkingLot.take(ticket);
        parkingBoyRepository.saveParkingBoy(parkingBoy);
        return car;
    }
}
