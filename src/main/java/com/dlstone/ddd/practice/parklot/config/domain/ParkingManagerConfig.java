package com.dlstone.ddd.practice.parklot.config.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParkingManagerConfig {
    private final ParkingManagerId id;
    private List<ParkingBoyId> parkingBoyIds = new ArrayList<>();
}
