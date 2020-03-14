package com.dlstone.ddd.practice.parklot.domain.model.config;

import java.util.List;

public interface ParkingBoyConfigRepository {
    List<ParkingBoyConfig> getParkingBoyConfigs(List<ParkingBoyConfigId> ids);

    void addParkingBoyConfig(ParkingBoyConfig parkingBoyConfig);

    void removeParkingBoyConfig(ParkingBoyConfig parkingBoyConfig);

    void updateParkingBoyConfig(ParkingBoyConfig parkingBoyConfig);
}
