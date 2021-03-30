package com.indomdi.com.core.dto;

import java.util.Arrays;

public class FilterUsers extends FilterComponent {

    public FilterUsers() throws Exception {
    }

    public enum Sort {

        // @formatter:off
        DEFAULT("id,ASC", "id,ASC"),
        FIRST_NAME_ASC("firstName,ASC", "firstName,ASC"),
        FIRST_NAME_DESC("firstName,DESC", "firstName,DESC"),
        LAST_NAME_ASC("lastName,ASC", "lastName,ASC"),
        LAST_NAME_DESC("lastName,DESC", "lastName,DESC"),
        USER_NAME_ASC("userName,ASC", "username,ASC"),
        USER_NAME_DESC("userName,DESC", "username,DESC");
        // @formatter:on

        private final String modelSortParam;
        private final String entitySortParam;

        Sort(String modelSortParam, String entitySortParam) {
            this.modelSortParam = modelSortParam;
            this.entitySortParam = entitySortParam;
        }

        public String getModelSortParam() {
            return modelSortParam;
        }

        public String getEntitySortParam() {
            return entitySortParam;
        }

        public static FilterUsers.Sort fromValue(String value) {
            return Arrays.stream(values()).filter(s -> s.getModelSortParam().equalsIgnoreCase(value)).findFirst().orElse(DEFAULT);
        }

        public static String of(String value) {
            return fromValue(value).getEntitySortParam();
        }
    }

    @Override
    public String translate(String sort) {
        return Sort.of(sort);
    }
}
