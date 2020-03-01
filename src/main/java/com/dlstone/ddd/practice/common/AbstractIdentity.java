package com.dlstone.ddd.practice.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class AbstractIdentity implements Identity {
    private final String value;
}
