package com.dlstone.ddd.practice.parklot.domain.model.parking;

import lombok.Value;

@Value
public class Ticket {
    private final CarId carId;
    private final ParkingLotId parkingLotId;
}
