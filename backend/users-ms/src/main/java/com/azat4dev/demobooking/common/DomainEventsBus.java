package com.azat4dev.demobooking.common;

public interface DomainEventsBus {

    void publish(DomainEventNew<?> event);
}
