package com.senkara.rfid.protocol;

public class DynamicQAlgorithm {

    private static final int MIN_Q = 0;
    private static final int MAX_Q = 15;

    private int lastDirection = 0;
    private int sameDecisionCount = 0;

    public int calculateNewQ(int currentQ, RoundResult result) {

        int direction = 0;

        if (result.getCollisionSlotCount() > result.getEmptySlotCount()) {
            direction = 1;
        } else if (result.getEmptySlotCount() > result.getCollisionSlotCount()) {
            direction = -1;
        }

        if (direction == 0) {
            lastDirection = 0;
            sameDecisionCount = 0;
            return currentQ;
        }

        if (direction == lastDirection) {
            sameDecisionCount++;
        } else {
            lastDirection = direction;
            sameDecisionCount = 1;
        }

        if (sameDecisionCount < 2) {
            return currentQ;
        }

        int newQ = currentQ + direction;

        if (newQ < MIN_Q) {
            newQ = MIN_Q;
        }

        if (newQ > MAX_Q) {
            newQ = MAX_Q;
        }

        sameDecisionCount = 0;

        return newQ;
    }
}