package com.dlstone.ddd.practice.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractIdentity implements Identity {
    private final String value;
}
