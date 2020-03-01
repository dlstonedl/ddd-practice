package com.dlstone.ddd.practice.parklot;

import com.dlstone.ddd.practice.parklot.common.exception.ParkingLotException;
import com.dlstone.ddd.practice.parklot.domain.model.*;
import com.dlstone.ddd.practice.parklot.infrastructure.ParkingBoyRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Client {

    private static ParkingBoyRepository parkingBoyRepository = new ParkingBoyRepositoryImpl();

    public static void main(String[] args) throws ParkingLotException {
        Car car = new Car(new CarId("粤B000TA"));

        Ticket ticket = park(car);
        log.info("park ticket: {}", ticket);

        Car takeCar = take(ticket);
        log.info("take car: {}", takeCar);

        manageParkingLots();
    }

    private static void manageParkingLots() {
        ParkingBoy parkingBoy = parkingBoyRepository.getParkingBoy();

        List<ParkingLot> parkingLots = parkingBoy.getParkingLots();
        List<ParkingLot> sortParkingLots = parkingLots
            .stream()
            .sorted(Comparator.comparing(parkingLot -> parkingLot.getId().getValue()))
            .collect(Collectors.toList());
        parkingBoy.setParkingLots(sortParkingLots);

        parkingBoyRepository.saveParkingBoy(parkingBoy);
    }

    private static Ticket park(Car car) throws ParkingLotException {
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
