package com.senkara.rfid.sdk;

import com.senkara.rfid.model.TagRead;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TagGenerator {

    private static final String FILE_NAME = "tags.csv";

    private Random random = new Random();

    public List<TagRead> generateTagsForAntenna(int activeAntenna) {

        List<TagRead> generatedTags = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                String epc = parts[0];
                String tid = parts[1];
                int antennaPort = Integer.parseInt(parts[2]);

                if (antennaPort != activeAntenna) {
                    continue;
                }

                int rssi = -55 + random.nextInt(11);

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
}