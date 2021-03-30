package com.indomdi.com.core.persistent.common;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class QueryResult<T> {

    private Long count;
    private List<T> items;

    public QueryResult(List<T> items) {
        this.items = items;
    }

    public QueryResult(List<T> items, Long count) {
        this.count = count;
        this.items = items;
    }

    public Long getCount() {
        return count;
    }

    @SuppressWarnings("rawtypes")
    public QueryResult setCount(Long count) {
        this.count = count;
        return this;
    }

    public List<T> getItems() {
        return items;
    }

    @SuppressWarnings("rawtypes")
    public QueryResult setItems(List<T> items) {
        this.items = items;
        return this;
    }
}
