package com.senkara.rfid.sdk;

import com.senkara.rfid.model.TagRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvTagParser {

    public List<TagRecord> parse(String fileName) {
        List<TagRecord> tagRecords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                TagRecord tagRecord = parseLine(line);

                if (tagRecord != null) {
                    tagRecords.add(tagRecord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tagRecords;
    }

    private TagRecord parseLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split(",");

        if (parts.length < 3) {
            return null;
        }

        String epc = parts[0].trim();
        String tid = parts[1].trim();
        String antennaText = parts[2].trim();

        if (epc.isEmpty() || tid.isEmpty() || antennaText.isEmpty()) {
            return null;
        }

        try {
            int antennaPort = Integer.parseInt(antennaText);
            return new TagRecord(epc, tid, antennaPort);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
