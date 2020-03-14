package com.dlstone.ddd.practice.parklot.domain.model.parking;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class Ticket {
    private final CarId carId;
    private final ParkingLotId parkingLotId;
}
