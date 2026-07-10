package com.senkara.rfid.sdk;

import com.senkara.rfid.model.TagRead;
import com.senkara.rfid.model.TagRecord;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TagGenerator {

    private static final String DEFAULT_FILE_NAME = "tags.csv";
    private static final int DEFAULT_POWER = 30;

    private String fileName;
    private Random random = new Random();
    private CsvTagParser csvTagParser = new CsvTagParser();

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
        List<TagRecord> tagRecords = csvTagParser.parse(fileName);

        for (TagRecord tagRecord : tagRecords) {
            if (tagRecord.getAntennaPort() != activeAntenna) {
                continue;
            }

            TagRead tag = createTagRead(tagRecord, antennaPower);
            generatedTags.add(tag);
        }

        return generatedTags;
    }

    private TagRead createTagRead(TagRecord tagRecord, int antennaPower) {
        return new TagRead(
                tagRecord.getEpc(),
                tagRecord.getTid(),
                generateRssi(antennaPower),
                tagRecord.getAntennaPort(),
                Instant.now()
        );
    }

    private int generateRssi(int antennaPower) {
        return -85 + antennaPower + random.nextInt(11);
    }
}
