package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.dal.impl.TagTypes;
import com.simbirsoft.maketalents.resume_builder.image.impl.HtmlResumePrinter;
import com.simbirsoft.maketalents.resume_builder.dal.impl.PropertiesFileScanner;
import com.simbirsoft.maketalents.resume_builder.image.impl.HtmlResumeCodeCreator;
import com.simbirsoft.maketalents.resume_builder.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.logging.Level;

public class Main {

    private static final String DEFAULT_NAME_PROPERTY_FILE = "resume.properties";
    private static final String DEFAULT_NAME_HTML_FILE = "resume";

    public static void main(String[] args) {
        buidResume(args);
    }

    /**
     * @param pathFiles
     */
    private static void buidResume(String... pathFiles) {
        //by default
        String pathDirPropertiesFile = Util.getPathExecutableDir();
        String pathDirHtmlFile = Util.getPathExecutableDir();
        String propertiesFileName = DEFAULT_NAME_PROPERTY_FILE;
        String htmlFileName = DEFAULT_NAME_HTML_FILE;

        //not default
        if (pathFiles.length > 2) {
            //pathFiles[0] - path to file properties
            pathDirPropertiesFile = new File(pathFiles[0]).getParent();
            propertiesFileName = new File(pathFiles[0]).getName();

            //pathFiles[1] - path to directory html
            pathDirHtmlFile = pathFiles[1];

            //pathFiles[2] html file name
            htmlFileName = pathFiles[2];
        }

        Util.getLogger().info("Name properties file: " + propertiesFileName);
        Util.getLogger().info("Dir properties file: " + pathDirPropertiesFile);
        Util.getLogger().info("Name html file: " + htmlFileName);
        Util.getLogger().info("Dir html file: " + pathDirHtmlFile);

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
