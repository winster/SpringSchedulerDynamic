package com.winster.spring.scheduledtask.scheduler;

public interface RefreshScheduler {
    default void materializeAfterRefresh() {
    }
}
