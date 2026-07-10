package com.senkara.rfid.sdk;

import com.senkara.rfid.model.TagRead;
public class EpcPrefixFilter implements TagFilter{

    private String prefix;

    public EpcPrefixFilter(String prefix){
        this.prefix = prefix;
    }

    public boolean matches(TagRead tag){


        // başladıgı kısmı kontrol ediyoruz ....
        return tag.getEpc().startsWith(prefix);
    }

}
