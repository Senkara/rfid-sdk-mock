package com.senkara.rfid.sdk;

import com.senkara.rfid.model.TagRead;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TagGenerator {

    private static final String DEFAULT_FILE_NAME = "tags.csv";
    private static final int DEFAULT_POWER = 30;

    private String fileName;
    private Random random = new Random();

    public TagGenerator() {
        this(DEFAULT_FILE_NAME);
    }

    public TagGenerator(String fileName) {
        this.fileName = fileName;
    }

    public List<TagRead> generateTagsForAntenna(int activeAntenna) {
        return generateTagsForAntenna(activeAntenna, DEFAULT_POWER);
    }

    public List<TagRead> generateTagsForAntenna(int activeAntenna, int antennaPower) {

        List<TagRead> generatedTags = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                String epc = parts[0];
                String tid = parts[1];
                int antennaPort = Integer.parseInt(parts[2]);

                if (antennaPort != activeAntenna) {
                    continue;
                }

                int rssi = generateRssi(antennaPower);

                TagRead tag = new TagRead(
                        epc,
                        tid,
                        rssi,
                        antennaPort,
                        Instant.now()
                );

                generatedTags.add(tag);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedTags;
    }

    private int generateRssi(int antennaPower) {
        return -85 + antennaPower + random.nextInt(11);
    }
}
