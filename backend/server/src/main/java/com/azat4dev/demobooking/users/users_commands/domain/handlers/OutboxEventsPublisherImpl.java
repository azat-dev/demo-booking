package com.azat4dev.demobooking.users.users_commands.domain.handlers;

import com.azat4dev.demobooking.common.DomainEvent;
import com.azat4dev.demobooking.common.DomainEventsBus;
import com.azat4dev.demobooking.common.EventId;
import com.azat4dev.demobooking.users.users_commands.domain.interfaces.repositories.OutboxEventsRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OutboxEventsPublisherImpl implements OutboxEventsPublisher {

    private final OutboxEventsRepository outboxEventsRepository;
    private final DomainEventsBus bus;

    @Override
    public void publishEvents() {

        while (true) {
            final var events = outboxEventsRepository.getNotPublishedEvents(1);
            if (events.isEmpty()) {
                break;
            }

            events.forEach(bus::publish);
            List<EventId> ids = events.stream().map(DomainEvent::getId).toList();
            outboxEventsRepository.markAsPublished(ids);
        }
    }
}
