package com.tinny.common.entity;

/**
 * @author : Tinny
 * @date : Created on 2020/4/20 - 16:07
 * @description :
 * @modified :
 */
public interface DomainEventPublisher<A, E extends DomainEvent> {
    /**
     * publish
     *
     * @param aggregateRoot aggregateRoot
     * @param events        events
     */
    void publish(A aggregateRoot, E events);

    /**
     * getDomainEventTopic
     *
     * @return String
     */
    String getDomainEventTopic();
}
