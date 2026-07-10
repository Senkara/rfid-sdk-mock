package com.senkara.rfid.protocol;

 import java.util.HashMap;
 import java.util.Map;
public class TagState {

    private Map<InventorySession, TagSessionFlag>sessionFlags=new HashMap<>();
private Map<InventorySession, Long >lastReadTimes=new HashMap<>();

    public TagState(){
        sessionFlags.put(InventorySession.S0, TagSessionFlag.A);
        sessionFlags.put(InventorySession.S1,TagSessionFlag.A);
        sessionFlags.put(InventorySession.S2,TagSessionFlag.A);
        sessionFlags.put(InventorySession.S3,TagSessionFlag.A);
    }
    public TagSessionFlag getFlag(InventorySession session){
        return sessionFlags.get(session);
    }public void setFlag(InventorySession session, TagSessionFlag flag){
        sessionFlags.put(session, flag);
    }public boolean canReply(InventorySession session){
        if(getFlag(session)== TagSessionFlag.A){
            return true;
        }Long lastReadTime= lastReadTimes.get(session);

        if(lastReadTime==null){
            return true;
        }long elapsedTime=System.currentTimeMillis() - lastReadTime;
        if(elapsedTime>=getSessionTimeoutMillis(session)){
            reset(session);
            return true;
        }return false;

    }public void markRead(InventorySession session){
        setFlag(session,TagSessionFlag.B);
        lastReadTimes.put(session,System.currentTimeMillis());
    }public void reset(InventorySession session){
        setFlag(session, TagSessionFlag.A);
        lastReadTimes.remove(session);
    }public void resetAll(){
        for(InventorySession session:InventorySession.values()){
            reset(session);
        }
    }
    private long getSessionTimeoutMillis(InventorySession session) {
        switch (session) {
            case S1:
                return 2000;
            case S2:
                return 10000;
            case S3:
                return 10000;
            default:
                return 0;
        }
    }
}


