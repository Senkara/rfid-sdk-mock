package com.senkara.rfid.model;

public class TagRecord {

    private String epc;
    private String tid;
    private int antennaPort;

    public TagRecord(String epc, String tid, int antennaPort) {
        this.epc = epc;
        this.tid = tid;
        this.antennaPort = antennaPort;
    }

    public String getEpc() {
        return epc;
    }

    public String getTid() {
        return tid;
    }

    public int getAntennaPort() {
        return antennaPort;
    }
}
