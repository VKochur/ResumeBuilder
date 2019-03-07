package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.service.SummaryService;
import com.simbirsoft.maketalents.resume_builder.util.Util;

import java.io.File;

public class Main {

    private static final String DEFAULT_NAME_PROPERTY_FILE = "resume.properties";
    private static final String DEFAULT_NAME_HTML_FILE = "resume";

    public static void main(String[] args) {

        buildResume(args);
    }

    /**
     * @param pathFiles
     */
    private static void buildResume(String... pathFiles) {
        SummaryService summaryService;
        if (pathFiles.length > 2) {
            summaryService = new SummaryService(new File(pathFiles[0]).getParent(), new File(pathFiles[0]).getName(), pathFiles[1], pathFiles[2]);
        } else {
            summaryService = new SummaryService(Util.getPathExecutableDir(), DEFAULT_NAME_PROPERTY_FILE, Util.getPathExecutableDir(), DEFAULT_NAME_HTML_FILE);
        }
        summaryService.buildResume();
    }
}
