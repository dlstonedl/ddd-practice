package com.dlstone.ddd.practice.parklot.domain.model.config;

import com.dlstone.ddd.practice.parklot.domain.model.parking.ParkingLotId;
import lombok.Data;

import java.util.List;

@Data
public class ParkingBoyConfig {
    private final ParkingBoyId id;
    private List<ParkingLotId> parkingLotIds;
    private ParkingStrategyName parkingStrategyName;
}
