package com.senkara.rfid.sdk;

public class TagFilters {

    public static TagFilter epcStartsWith(String prefix) {
        return new EpcPrefixFilter(prefix);
    }

    public static TagFilter tidStartsWith(String prefix) {
        return new TidPrefixFilter(prefix);
    }

    public static TagFilter and(TagFilter first, TagFilter second) {
        return new AndTagFilter(first, second);
    }

    public static TagFilter or(TagFilter first, TagFilter second) {
        return new OrTagFilter(first, second);
    }

    public static TagFilter not(TagFilter filter) {
        return new NotTagFilter(filter);
    }
}
