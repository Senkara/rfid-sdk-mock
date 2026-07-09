package com.senkara.rfid.protocol;

import  com.senkara.rfid.model.TagRead;
import java.util.List;
import java.util.ArrayList;
public class Slot {

     private List<TagRead> tags=new ArrayList<>();
    private int slotNumber;
    public Slot(int slotNumber){
        this.slotNumber=slotNumber;
    }

    public int getSlotNumber(){
        return slotNumber;
    }public void addTag(TagRead tag){
        tags.add(tag);
    }public int getTagCount(){
        return tags.size();

    }public boolean isEmpty(){
        return tags.isEmpty();
    }public boolean hasSingleTag(){
        return tags.size()==1;
    }public boolean hasCollision(){
        return tags.size()>1;
    }public List<TagRead> getTags(){
        return tags;
    }
}
