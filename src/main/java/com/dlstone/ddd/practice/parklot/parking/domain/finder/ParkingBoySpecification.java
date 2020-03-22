package com.dlstone.ddd.practice.parklot.parking.domain.finder;

import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotFinderSpecification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParkingBoySpecification implements ParkingLotFinderSpecification {
    private String parkingBoyId;
}
