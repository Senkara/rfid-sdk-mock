package com.senkara.rfid.sdk;

import com.senkara.rfid.protocol.DynamicQAlgorithm;


import java.util.List;
import java.util.function.Consumer;

import com.senkara.rfid.model.TagRead;
import com.senkara.rfid.protocol.InventoryRound;
import com.senkara.rfid.protocol.RoundResult;

public class Reader {


    private DynamicQAlgorithm dynamicQAlgorithm=new DynamicQAlgorithm();

    private int qValue = 2;

    private static final int DEFAULT_POWER = 30;
    private int antennaPower = DEFAULT_POWER;

    private static final int DEFAULT_ANTENNA = 1;
    private static final int MIN_ANTENNA = 1;
    private static final int MAX_ANTENNA = 4;

    private int activeAntenna = DEFAULT_ANTENNA;

    private boolean connected = false;
    private volatile boolean inventoryRunning = false;

    private Consumer<TagRead> tagReadListener;

    private TagGenerator tagGenerator=new TagGenerator();

    public void connect() {
        if (connected) {
            System.out.println("Reader zaten bağlı.");
            return;
        }

        connected = true;
        System.out.println("Reader bağlandı.");
    }

    public void setTagReadListener(Consumer<TagRead> listener) {
        this.tagReadListener = listener;
    }

    public void startInventory() {
        if (!connected) {
            System.out.println("Önce reader'a bağlan.");
            return;
        }

        if (inventoryRunning) {
            System.out.println("Inventory zaten çalışıyor.");
            return;
        }

        inventoryRunning = true;
        System.out.println("Inventory başladı.");

        Thread inventoryThread = new Thread(() -> {
            int roundNumber = 1;

            while (inventoryRunning) {

                System.out.println("===== ROUND " + roundNumber + " =====");

                InventoryRound round = new InventoryRound(qValue);

                List<TagRead>generatedTags=tagGenerator.generateTagsForAntenna(activeAntenna);
                for(TagRead tag:generatedTags){
                    if(!inventoryRunning){
                        break;
                    }round.assignTag(tag);

                }

                if (!inventoryRunning) {
                    break;
                }

                round.printRoundResult();

                RoundResult roundResult=round.getResult();

                List<TagRead> successfulTags = round.getSuccesfulTags();

                int newQ=dynamicQAlgorithm.calculateNewQ(qValue,roundResult);

                if(newQ!=qValue){
                    System.out.println("Q Value değişti: "+qValue + " -> "+newQ);
                    qValue=newQ;
                }

                for (TagRead tag : successfulTags) {

                    if (!inventoryRunning) {
                        break;
                    }

                    if (tagReadListener != null) {
                        tagReadListener.accept(tag);
                    }
                }

                roundNumber++;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Inventory thread kesildi.");
                    break;
                }
            }
        });

        inventoryThread.start();
    }

    public void stopInventory() {
        if (!inventoryRunning) {
            System.out.println("Inventory zaten çalışmıyor.");
            return;
        }

        inventoryRunning = false;
        System.out.println("Inventory durduruldu.");
    }

    public void disconnect() {
        if (!connected) {
            System.out.println("Reader zaten bağlı değil.");
            return;
        }

        if (inventoryRunning) {
            stopInventory();
        }

        connected = false;
        System.out.println("Reader bağlantısı kesildi.");
    }

    public void setAntennaPower(int power) {
        if (!connected) {
            System.out.println("Önce reader'a bağlan.");
            return;
        }

        if (power < 5 || power > 30) {
            System.out.println("Power değeri 5 ile 30 arasında olmalı!");
            return;
        }

        antennaPower = power;

        System.out.println("Anten gücü " + power + " dBm olarak ayarlandı.");
    }

    public void setActiveAntenna(int antennaPort) {
        if (!connected) {
            System.out.println("Önce reader'a bağlan.");
            return;
        }

        if (antennaPort < MIN_ANTENNA || antennaPort > MAX_ANTENNA) {
            System.out.println("Anten portu 1 ile 4 arasında olmalı.");
            return;
        }

        activeAntenna = antennaPort;
        System.out.println("Aktif anten " + antennaPort + " olarak ayarlandı.");
    }

    public void setQvalue(int qValue) {
        if (!connected) {
            System.out.println("Önce reader' a bağlan.");
            return;
        }

        if (qValue < 0 || qValue > 15) {
            System.out.println("Q değeri 0 ile 15 arasında olmalı.");
            return;
        }

        this.qValue = qValue;

        System.out.println("Q değeri " + qValue + " olarak ayarlandı.");
    }
}