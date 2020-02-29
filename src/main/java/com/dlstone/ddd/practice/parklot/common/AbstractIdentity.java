package com.dlstone.ddd.practice.parklot.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractIdentity implements Identity {
    private final String value;
}