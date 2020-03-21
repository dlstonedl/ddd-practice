package com.dlstone.ddd.practice.parklot.parking.domain.core;

import lombok.Value;

@Value
public class Ticket {
    private final CarId carId;
    private final ParkingLotId parkingLotId;
}
