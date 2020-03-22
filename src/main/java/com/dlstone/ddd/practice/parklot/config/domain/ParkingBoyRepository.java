package com.dlstone.ddd.practice.parklot.config.domain;

import java.util.List;

public interface ParkingBoyRepository {
    ParkingBoy getParkingBoy(ParkingBoyId id);

    List<ParkingBoy> getParkingBoyConfigs(List<ParkingBoyId> ids);

    void addParkingBoyConfig(ParkingBoy parkingBoy);

    void removeParkingBoyConfig(ParkingBoy parkingBoy);

    void updateParkingBoyConfig(ParkingBoy parkingBoy);
}
