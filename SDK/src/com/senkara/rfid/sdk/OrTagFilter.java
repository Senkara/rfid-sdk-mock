package com.senkara.rfid.sdk;

import com.senkara.rfid.model.TagRead;

public class OrTagFilter implements TagFilter {

    private TagFilter first;
    private TagFilter second;

    public OrTagFilter(TagFilter first, TagFilter second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean matches(TagRead tag) {
        return first.matches(tag) || second.matches(tag);
    }
}
