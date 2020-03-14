package com.dlstone.ddd.practice.parklot.domain.model.parking;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@EqualsAndHashCode
public class Car {
    private final CarId id;
}
