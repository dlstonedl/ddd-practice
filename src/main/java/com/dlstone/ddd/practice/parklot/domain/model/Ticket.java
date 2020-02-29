package com.dlstone.ddd.practice.parklot.domain.model;

import com.dlstone.ddd.practice.parklot.common.CarId;
import com.dlstone.ddd.practice.parklot.common.ParkingLotId;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class Ticket {
    private final CarId carId;
    private final ParkingLotId parkingLotId;
}
