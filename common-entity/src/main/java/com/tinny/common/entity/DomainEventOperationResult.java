package com.tinny.common.entity;

import lombok.Builder;
import lombok.Getter;

/**
 * @author : Tinny
 * @date : Created on 2020/4/20 - 16:06
 * @description :
 * @modified :
 */
@Getter
@Builder
public class DomainEventOperationResult<T, E extends DomainEvent> {
    /**
     * result
     */
    private T result;
    /**
     * event
     */
    private E event;
}
