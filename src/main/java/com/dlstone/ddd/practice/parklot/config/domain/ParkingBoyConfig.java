package com.dlstone.ddd.practice.parklot.config.domain;

import com.dlstone.ddd.practice.parklot.parking.domain.core.ParkingLotId;
import lombok.Data;

import java.util.List;

@Data
public class ParkingBoyConfig {
    private final ParkingBoyId id;
    private List<ParkingLotId> parkingLotIds;
    private ParkingStrategyName parkingStrategyName;
}
