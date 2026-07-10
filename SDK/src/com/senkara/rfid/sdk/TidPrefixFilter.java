package com.senkara.rfid.sdk;

import com.senkara.rfid.model.TagRead;

public class TidPrefixFilter implements TagFilter {

    private String prefix;

    public TidPrefixFilter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean matches(TagRead tag) {
        return tag.getTid().startsWith(prefix);
    }
}
