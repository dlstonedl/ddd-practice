package com.dlstone.ddd.practice.parklot.domain.model.config;

public interface ParkingManagerConfigRepository {
    ParkingManagerConfig getParkingManagerConfig(ParkingManagerId id);

    void addParkingManagerConfig(ParkingManagerConfig parkingManagerConfig);

    void removeParkingManagerConfig(ParkingManagerConfig parkingManagerConfig);

    void updateParkingManagerConfig(ParkingManagerConfig parkingManagerConfig);
}
