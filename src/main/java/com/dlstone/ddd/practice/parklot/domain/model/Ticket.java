package com.dlstone.ddd.practice.parklot.domain.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@EqualsAndHashCode
public class Ticket {
    private final CarId carId;
    private final ParkingLotId parkingLotId;
}
