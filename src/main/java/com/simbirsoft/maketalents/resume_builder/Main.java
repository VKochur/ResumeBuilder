package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.image.impl.HtmlResumePrinter;
import com.simbirsoft.maketalents.resume_builder.dal.impl.PropertiesFileScanner;
import com.simbirsoft.maketalents.resume_builder.image.impl.HtmlResumeCodCreator;
import com.simbirsoft.maketalents.resume_builder.util.Util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.InvalidPropertiesFormatException;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {

    private static final String DEFAULT_NAME_PROPERTY_FILE = "resume.properties";
    private static final String DEFAULT_NAME_HTML_FILE = "resume";

    private static Logger logger = Logger.getLogger(Main.class.getName());
    static {
        try {
            FileHandler fh = new FileHandler("LogApp.txt");
            logger.addHandler(fh);

/*
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Main.class.getClassLoader().getResourceAsStream("logging.properties"), StandardCharsets.UTF_8.name()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (FileNotFoundException e) {
                Util.processingEx(e);
            } catch (InvalidPropertiesFormatException e) {
                Util.processingEx(e);
            } catch (IOException e) {
                Util.processingEx(e);
            }
*/

            //LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        logger.info("Hello logger");
        logger.info("Hello logger2");


        if (10>2) {
            return;
        }
        buidResume(args);

    }

    /**
     * @param pathFiles
     */
    private static void buidResume(String... pathFiles) {
        System.out.println("Current directory: " + new File("").getAbsolutePath());
        System.out.println("Path of Main class: " + Util.getPathCurrentDir());

        //путь к файлу источнику и директории где следует создать html

        //по умолчанию там же где и запускаемый файл
        String pathDirPropertiesFile = Util.getPathCurrentDir();
        String pathDirHtmlFile = Util.getPathCurrentDir();

        String propertiesFileName = DEFAULT_NAME_PROPERTY_FILE;
        String htmlFileName = DEFAULT_NAME_HTML_FILE;


        if (pathFiles.length > 2) {

            //pathFiles[0] путь прямо к файлу
            pathDirPropertiesFile = new File(pathFiles[0]).getParent();
            propertiesFileName = new File(pathFiles[0]).getName();

            //pathFiles[1] путь к директории, где нужно создать html
            pathDirHtmlFile = pathFiles[1];

            //pathFiles[2] имя создаваемого файла
            htmlFileName = pathFiles[2];
        }


        System.out.println("Name properties file: " + propertiesFileName);
        System.out.println("Dir properties file: " + pathDirPropertiesFile);

        System.out.println("Name html file: " + htmlFileName);
        System.out.println("Dir html file: " + pathDirHtmlFile);


        HtmlResumeCodCreator htmlResumeCodCreator = new TemplateReplacer("html/template.html");
        htmlResumeCodCreator.setProvider(new PropertiesFileScanner(pathDirPropertiesFile + "\\" + propertiesFileName));
        HtmlResumePrinter htmlResumePrinter = new HtmlResumePrinter();
        htmlResumePrinter.setHtmlResumeCodCreater(htmlResumeCodCreator);
        htmlResumePrinter.setPathDirToFile(pathDirHtmlFile);
        htmlResumePrinter.setNameFile(htmlFileName);

        try {
            htmlResumePrinter.print();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
