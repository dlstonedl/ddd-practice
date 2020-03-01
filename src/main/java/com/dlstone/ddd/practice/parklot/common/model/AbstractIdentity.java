package com.dlstone.ddd.practice.parklot.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class AbstractIdentity implements Identity {
    private final String value;
}
