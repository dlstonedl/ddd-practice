package com.dlstone.ddd.practice.parklot.domain.model;

import com.dlstone.ddd.practice.parklot.common.CarId;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class Car {
    private final CarId id;
}
