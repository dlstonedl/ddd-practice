package com.dlstone.ddd.practice.parklot.config.domain;

public interface ParkingManagerRepository {
    ParkingManager getParkingManager(ParkingManagerId id);

    void addParkingManagerConfig(ParkingManager parkingManager);

    void removeParkingManagerConfig(ParkingManager parkingManager);

    void updateParkingManagerConfig(ParkingManager parkingManager);
}
