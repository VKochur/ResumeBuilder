package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.service.SummaryService;

import java.io.File;

public class MainForIde {

    public static void main(String[] args) {
        SummaryService summaryService = new SummaryService(
                new File("").getAbsolutePath() + "\\src\\main\\resources",
                "person.properties",
                new File("").getAbsolutePath() + "\\src\\main\\webapp",
                "summary");
        summaryService.buildResume();
    }
}
