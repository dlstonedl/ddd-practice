package com.dlstone.ddd.practice;

import com.dlstone.ddd.practice.common.CarId;
import com.dlstone.ddd.practice.parklot.domain.model.Car;

public class Client {

    public static void main(String[] args) {
        Car car = new Car(new CarId("粤B000TA"));


    }

}
