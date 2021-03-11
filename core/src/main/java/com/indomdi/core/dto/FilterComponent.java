package com.indomdi.core.dto;

import javax.validation.Valid;
import java.util.Objects;

public abstract class FilterComponent {

    private String sort;
    @Valid
    private Pagination pagination;

    public FilterComponent() throws Exception {
        getPagination().withSort(translate("DEFAULT"));
    }

    public Pagination getPagination() {
        if (Objects.isNull(pagination)) {
            pagination = new Pagination();
        }
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) throws Exception {
        this.sort = sort;
        getPagination().withSort(translate(sort));
    }

    /**
     * Translate model sort to query sort
     * <p/>
     *
     * @param sort - sort param
     * @return translated sort param
     */
    public abstract String translate(String sort);

}
