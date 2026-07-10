package com.senkara.rfid.sdk;

import com.senkara.rfid.model.TagRead;

public class NotTagFilter implements TagFilter {

    private TagFilter filter;

    public NotTagFilter(TagFilter filter) {
        this.filter = filter;
    }

    @Override
    public boolean matches(TagRead tag) {
        return !filter.matches(tag);
    }
}
