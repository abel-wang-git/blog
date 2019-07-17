package com.api.core.util;

public class WhereProperty {

    private String field;

    private Object value;

    private WhereLink whereLink;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public WhereLink getWhereLink() {
        return whereLink;
    }

    public void setWhereLink(WhereLink whereLink) {
        this.whereLink = whereLink;
    }
}
