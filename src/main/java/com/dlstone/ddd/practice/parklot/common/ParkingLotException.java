package com.dlstone.ddd.practice.parklot.common;

public class ParkingLotException extends RuntimeException {

    public ParkingLotException() {
        super();
    }

    public ParkingLotException(String message) {
        super(message);
    }

    public ParkingLotException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParkingLotException(Throwable cause) {
        super(cause);
    }

    public ParkingLotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
