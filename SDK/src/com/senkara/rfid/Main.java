package com.senkara.rfid;

import com.senkara.rfid.model.TagRead;
import com.senkara.rfid.protocol.Slot;
import com.senkara.rfid.protocol.InventorySession;
import com.senkara.rfid.sdk.Reader;
import com.senkara.rfid.protocol.InventoryRound;

import com.senkara.rfid.sdk.TagFilters;



public class Main {

    public static void main(String[] args) throws InterruptedException {

    Reader reader=new Reader();

    reader.connect();

    reader.setActiveAntenna(1);

    reader.setAntennaPower(20);

    reader.setQvalue(2);

    reader.setInventorySession(InventorySession.S1);

    reader.setTagReadListener(tag->{
        System.out.println("EPC : "+tag.getEpc() );
        System.out.println("TID : " + tag.getTid());
        System.out.println("RSSI: " + tag.getRssi());
        System.out.println("ANT : " + tag.getAntennaPort());
        System.out.println("TIME: " + tag.getReadTime());
        System.out.println("------------------------");
    });
        reader.setTagFilter(TagFilters.epcStartsWith("300833B2DDD9014000000001"));
        reader.startInventory();
        Thread.sleep(10000);
        reader.stopInventory();
        reader.disconnect();

    }
}
