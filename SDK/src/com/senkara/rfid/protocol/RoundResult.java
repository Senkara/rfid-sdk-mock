package com.senkara.rfid.protocol;

public class RoundResult {
    private int emptySlotCount;
    private int successfulSlotCount;;
    private int collisionSlotCount;

    public int getEmptySlotCount() {
        return emptySlotCount;
    }public int getSuccessfulSlotCount() {
        return successfulSlotCount;
    }public int getCollisionSlotCount() {
        return collisionSlotCount;
    }

    public void setCollisionSlotCount(int collisionSlotCount) {
        this.collisionSlotCount = collisionSlotCount;
    }public RoundResult(int emptySlotCount, int successfulSlotCount, int collisionSlotCount) {
        this.emptySlotCount = emptySlotCount;
        this.successfulSlotCount = successfulSlotCount;
        this.collisionSlotCount = collisionSlotCount;
    }

    public void setEmptySlotCount(int emptySlotCount) {
        this.emptySlotCount = emptySlotCount;
    }

    public void setSuccessfulSlotCount(int successfulSlotCount) {
        this.successfulSlotCount = successfulSlotCount;
    }
}

