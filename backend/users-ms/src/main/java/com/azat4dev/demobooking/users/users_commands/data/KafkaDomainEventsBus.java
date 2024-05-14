package com.azat4dev.demobooking.users.users_commands.data;

import com.azat4dev.demobooking.common.domain.event.*;
import com.azat4dev.demobooking.common.utils.TimeProvider;
import com.azat4dev.demobooking.users.users_commands.data.repositories.DomainEventSerializer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;

import java.io.Closeable;
import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Function;

@RequiredArgsConstructor
public final class KafkaDomainEventsBus implements DomainEventsBus {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final DomainEventSerializer domainEventSerializer;
    private final Function<String, ConcurrentMessageListenerContainer<String, String>> containerFactory;
    private final TimeProvider timeProvider;
    private final EventIdGenerator eventIdGenerator;

    @Override
    public void publish(DomainEventPayload event, LocalDateTime time, EventId eventId) {

        kafkaTemplate.send(
            event.getClass().getSimpleName(),
            domainEventSerializer.serialize(
                new DomainEventNew<DomainEventPayload>(
                    eventId,
                    time,
                    event
                )
            )
        );
    }

    @Override
    public void publish(Command command) {
        this.publish(command, timeProvider.currentTime(), eventIdGenerator.generate());
    }

    @Override
    public void publish(DomainEventPayload event) {
        this.publish(event, timeProvider.currentTime(), eventIdGenerator.generate());
    }

    @Override
    public Closeable listen(Class<DomainEventPayload> eventType, Consumer<DomainEventNew<?>> listener) {

        final var topic = eventType.getSimpleName();
        final var container = containerFactory.apply(topic);

        container.setupMessageListener(new AcknowledgingMessageListener<>() {

            @Override
            public void onMessage(ConsumerRecord<Object, Object> data, Acknowledgment acknowledgment) {
                listener.accept(domainEventSerializer.deserialize((String) data.value()));
                if (acknowledgment != null) {
                    acknowledgment.acknowledge();
                }
            }
        });

        container.start();
        return () -> container.stop();
    }
}