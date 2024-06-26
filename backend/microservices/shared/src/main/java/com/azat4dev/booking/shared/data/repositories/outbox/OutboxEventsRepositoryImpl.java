package com.azat4dev.booking.shared.data.repositories.outbox;

import com.azat4dev.booking.shared.data.serializers.DomainEventSerializer;
import com.azat4dev.booking.shared.data.dao.outbox.OutboxEventData;
import com.azat4dev.booking.shared.data.dao.outbox.OutboxEventsDao;
import com.azat4dev.booking.shared.domain.events.DomainEvent;
import com.azat4dev.booking.shared.domain.events.DomainEventPayload;
import com.azat4dev.booking.shared.domain.events.DomainEventsFactory;
import com.azat4dev.booking.shared.domain.events.EventId;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OutboxEventsRepositoryImpl implements OutboxEventsRepository {

    private final DomainEventSerializer domainEventSerializer;
    private final OutboxEventsDao outboxEventsDao;
    private final DomainEventsFactory domainEventsFactory;

    @Override
    public void publish(DomainEventPayload event) {

        final var eventRecord = OutboxEventData.makeFromDomain(
            domainEventsFactory.issue(event),
            this.domainEventSerializer
        );
        this.outboxEventsDao.put(eventRecord);
    }

    @Override
    public void markAsPublished(List<EventId> eventIds) {
        outboxEventsDao.markAsPublished(eventIds.stream().map(EventId::getValue).toList());
    }

    @Override
    public List<? extends DomainEvent<?>> getNotPublishedEvents(int limit) {

        return this.outboxEventsDao.findNotPublishedEvents(limit)
            .stream()
            .map(rc -> this.domainEventSerializer.deserialize(rc.payload()))
            .toList();
    }
}
