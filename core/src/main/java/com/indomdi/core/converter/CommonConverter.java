package com.indomdi.core.converter;

import com.indomdi.core.dto.Pagination;
import com.indomdi.core.persistent.common.QueryResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CommonConverter {

    /**
     * Convert elements mapped by provided mapper to QueryResult
     * <p/>
     *
     * @param page   page of elements
     * @param mapper Function which map elements type to response type
     * @param <T>    type of response
     * @param <R>    type of request
     * @return QueryResult
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T, R> QueryResult<T> toQueryResult(Page<R> page, Function<R, T> mapper) {
        final List<R> content = Optional.ofNullable(page.getContent()).orElse(new ArrayList<>());
        final List<T> result = content.stream().map(mapper).collect(Collectors.toList());

        return new QueryResult(result, page.getTotalElements());
    }

    public <T> QueryResult<T> toQueryResult(Page<T> page) {

        return toQueryResult(page, Function.identity());
    }

    /**
     * Convert pg into a PageRequest
     * <p/>
     *
     * @param pg - pagination
     * @return page request
     */
    public PageRequest toPageRequest(Pagination pg) {
        if (Objects.isNull(pg)) return null;

        final int pageNumber = pg.getPage() - 1;

        if (StringUtils.isNotBlank(pg.getColumn()) && Objects.nonNull(pg.getDirection()))
            return PageRequest.of(pageNumber, pg.getPerPage(), JpaSort.unsafe(pg.getDirection(), pg.getColumn()));

        return PageRequest.of(pageNumber, pg.getPerPage());
    }
}
