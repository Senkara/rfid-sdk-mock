package com.senkara.rfid.model;
import java.time.Instant;
public class TagRead {

    private String epc;
    private String tid;
    private int rssi;
    private int antennaPort;
    private Instant readTime;

    public TagRead(String epc, String tid, int rssi,int antennaPort,Instant readTime) {
        this.epc = epc;
        this.tid = tid;
        this.rssi = rssi;
        this.antennaPort = antennaPort;
        this.readTime = readTime;
    }

    public String getEpc() {
        return epc;
    }

    public String getTid() {
        return tid;
    }

    public int getRssi() {
        return rssi;
    }
    public int getAntennaPort() {
        return antennaPort;
    }public Instant getReadTime(){
        return readTime;
    }
}