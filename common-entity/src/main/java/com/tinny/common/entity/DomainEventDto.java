package com.tinny.common.entity;

import lombok.*;

/**
 * @author : Tinny
 * @date : Created on 2020/4/20 - 16:04
 * @description :
 * @modified :
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DomainEventDto<T> {
    /**
     * aggregateRootId
     */
    private String aggregateRootId;
    /**
     * event
     */
    private T event;
}
