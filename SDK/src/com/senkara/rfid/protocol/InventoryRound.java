package com.senkara.rfid.protocol;
import com.senkara.rfid.model.TagRead;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
public class InventoryRound {

    private List<Slot> slots=new ArrayList<>();

    private int qValue;

    private Random random=new Random();


    public InventoryRound(int qValue){
        this.qValue = qValue;
        createSlots();
    }

    public int getQValue(){
        return qValue;
    }

    public int getSlotCount(){
        return (int)Math.pow(2,qValue);
    }


    private void createSlots(){
        int slotCount=getSlotCount();
        for(int i=0;i<slotCount;i++){
            slots.add(new Slot(i));
        }
    }public List<Slot>getSlots(){
        return slots;
    }
    public void assignTag(TagRead tag){
        int randomSlotNumber=random.nextInt(getSlotCount());
        Slot selectedSlot=slots.get(randomSlotNumber);
        selectedSlot.addTag(tag);
    }

    public void printRoundResult() {
        for (Slot slot : slots) {
            if (slot.isEmpty()) {
                System.out.println("Slot " + slot.getSlotNumber() + ": Boş");
            } else if (slot.hasSingleTag()) {
                System.out.println("Slot " + slot.getSlotNumber() + ": Başarılı okuma");
            } else if (slot.hasCollision()) {
                System.out.println("Slot " + slot.getSlotNumber() + ": Collision (" + slot.getTagCount() + " tag)");
            }
        }
    }
    public List<TagRead>getSuccesfulTags(){
        List<TagRead>successfulTags=new ArrayList<>();
        for(Slot slot:slots){
            if(slot.hasSingleTag()){
                successfulTags.add(slot.getTags().get(0));
            }
        }
        return successfulTags;
    }public int getEmptySlotCount(){
        int count=0;
        for(Slot slot:slots){
            if(slot.isEmpty())count++;
        }
        return count;
    }public int getSuccessfulSlotCount(){
        int count=0;
        for(Slot slot:slots){
            if(slot.hasSingleTag()){
                count++;
            }
        }return count;
    }public int getCollisionSlotCount(){
        int count=0;
        for(Slot slot:slots){
            if(slot.hasCollision()){
                count++;
            }
        }return count;

    }public RoundResult getResult(){
        return new RoundResult(getEmptySlotCount(),getSuccessfulSlotCount(),getCollisionSlotCount());
    }
}
