package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.image.impl.FileReplacerHtml;
import com.simbirsoft.maketalents.resume_builder.image.impl.HtmlResumePrinter;
import com.simbirsoft.maketalents.resume_builder.dal.impl.PropertiesFileScanner;
import com.simbirsoft.maketalents.resume_builder.image.impl.HtmlResumeCodCreator;
import com.simbirsoft.maketalents.resume_builder.util.Util;

import java.io.File;
import java.io.IOException;


public class Main {

    private static final String DEFAULT_NAME_PROPERTY_FILE = "resume.properties";
    private static final String DEFAULT_NAME_HTML_FILE = "resume";

    public static void main(String[] args) {
        System.out.println("Current directory: " + new File("").getAbsolutePath());
        System.out.println("Path of Main class: " + Util.getPathCurrentDir());

        buidResume(args);
    }


    /**
     * @param pathFiles
     */
    private static void buidResume(String... pathFiles) {
        //путь к шаблону html
        String pathToTemplate = new StringBuilder(Util.getPathCurrentDir()).append("\\html\\template.html").toString();

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

        System.out.println("Path to template: " + pathToTemplate);

        if (10>0){
            return;
        }

        HtmlResumeCodCreator htmlResumeCodCreator = new FileReplacerHtml(pathToTemplate);
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
