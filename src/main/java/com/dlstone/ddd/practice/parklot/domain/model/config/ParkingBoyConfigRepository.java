package com.dlstone.ddd.practice.parklot.domain.model.config;

import java.util.List;

public interface ParkingBoyConfigRepository {
    ParkingBoyConfig getParkingBoyConfig(ParkingBoyId id);

    List<ParkingBoyConfig> getParkingBoyConfigs(List<ParkingBoyId> ids);

    void addParkingBoyConfig(ParkingBoyConfig parkingBoyConfig);

    void removeParkingBoyConfig(ParkingBoyConfig parkingBoyConfig);

    void updateParkingBoyConfig(ParkingBoyConfig parkingBoyConfig);
}
