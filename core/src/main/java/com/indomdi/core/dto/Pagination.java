package com.indomdi.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.data.domain.Sort;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {

    @Range(min = 1, max = Short.MAX_VALUE, message = "{pagination.page}")
    private int page = 1;

    @Range(min = 1, max = Short.MAX_VALUE, message = "{pagination.per.page}")
    private int perPage = Short.MAX_VALUE;

    private String column;

    private Sort.Direction direction;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = Sort.Direction.valueOf(direction);
    }

    public Pagination withDirection(String direction) {
        this.direction = Sort.Direction.valueOf(direction);
        return this;
    }

    /**
     * Register column and sort direction based on string sort value.
     * <p/>
     *
     * @param sort - sort parameter e.g. <code>sort=messageType,DESC</code>
     * @return pagination instance.
     */
    public Pagination withSort(String sort) throws Exception {
        if (StringUtils.isNotBlank(sort)) {
            final String[] split = sort.split(",");
            final int requiredSortLength = 2;

            if (requiredSortLength != split.length) {
                throw new Exception("Invalid sort parameter:" + sort);
            }
            column = split[0];
            withDirection(split[1]);
        }

        return this;
    }
}
