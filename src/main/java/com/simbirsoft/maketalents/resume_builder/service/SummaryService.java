package com.simbirsoft.maketalents.resume_builder.service;

import com.simbirsoft.maketalents.resume_builder.image.impl.TemplateReplacer;
import com.simbirsoft.maketalents.resume_builder.dal.impl.PropertiesFileScanner;
import com.simbirsoft.maketalents.resume_builder.dal.impl.TagTypes;
import com.simbirsoft.maketalents.resume_builder.image.impl.HtmlResumeCodeCreator;
import com.simbirsoft.maketalents.resume_builder.image.impl.HtmlResumePrinter;
import com.simbirsoft.maketalents.resume_builder.util.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.logging.Level;

public class SummaryService {

    private String pathDirPropertiesFile;
    private String propertiesFileName;
    private String pathDirHtmlFile;
    private String htmlFileName;

    public SummaryService(String pathDirPropertiesFile, String propertiesFileName, String pathDirHtmlFile, String htmlFileName) {
        this.pathDirPropertiesFile = pathDirPropertiesFile;
        this.propertiesFileName = propertiesFileName;
        this.pathDirHtmlFile = pathDirHtmlFile;
        this.htmlFileName = htmlFileName;
    }

    /**
     *
     */
    public void buildResume() {
        Util.getLogger().info("Name properties file: " + this.propertiesFileName);
        Util.getLogger().info("Dir properties file: " + this.pathDirPropertiesFile);
        Util.getLogger().info("Name html file: " + this.htmlFileName);
        Util.getLogger().info("Dir html file: " + this.pathDirHtmlFile);

        try {
            HtmlResumeCodeCreator htmlResumeCodeCreator = new TemplateReplacer("html/template.html");
            htmlResumeCodeCreator.setProvider(new PropertiesFileScanner(pathDirPropertiesFile + "\\" + propertiesFileName));
            HtmlResumePrinter htmlResumePrinter = new HtmlResumePrinter();
            htmlResumePrinter.setHtmlResumeCodeCreator(htmlResumeCodeCreator);
            htmlResumePrinter.setPathDirToFile(pathDirHtmlFile);
            htmlResumePrinter.setNameFile(htmlFileName);
            htmlResumePrinter.print();
            Util.getLogger().info("success");

        } catch (InvalidPropertiesFormatException e) {
            Util.getLogger().log(Level.SEVERE, "Illegal properties file. Encode file must be UTF-8 without BOM. Legal tags: " + Arrays.toString(TagTypes.values()) + "\n " + e.getMessage(), e);
        } catch (IOException e) {
            Util.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
