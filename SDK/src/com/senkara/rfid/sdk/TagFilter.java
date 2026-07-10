package com.senkara.rfid.sdk;

import com.senkara.rfid.model.TagRead;
public interface TagFilter {
    boolean matches(TagRead tag);
}
