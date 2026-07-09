package com.senkara.rfid;

import com.senkara.rfid.model.TagRead;
import com.senkara.rfid.protocol.Slot;
import com.senkara.rfid.sdk.Reader;
import com.senkara.rfid.protocol.InventoryRound;



public class Main {

    public static void main(String[] args) throws InterruptedException {

    Reader reader=new Reader();

    reader.connect();

    reader.setActiveAntenna(1);

    reader.setAntennaPower(20);

    reader.setQvalue(2);

    reader.setTagReadListener(tag->{
        System.out.println("EPC : "+tag.getEpc() );
        System.out.println("TID : " + tag.getTid());
        System.out.println("RSSI: " + tag.getRssi());
        System.out.println("ANT : " + tag.getAntennaPort());
        System.out.println("TIME: " + tag.getReadTime());
        System.out.println("------------------------");
    });
        reader.startInventory();
        Thread.sleep(10000);
        reader.stopInventory();
        reader.disconnect();

    }
}