package com.googlecode.test.toolkit.common;

public interface Handle<BEFORE, AFTER> {
    AFTER process(BEFORE before);
}