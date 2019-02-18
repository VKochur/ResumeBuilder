package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.dal.ResumePrinter;
import com.simbirsoft.maketalents.resume_builder.dal.ResumeScanner;
import com.simbirsoft.maketalents.resume_builder.dal.impl.HtmlPrinter;
import com.simbirsoft.maketalents.resume_builder.dal.impl.PropertiesFileScanner;

public class Main {

    public static void main(String[] args) {
        String pathFile = "example.properties";
        buidResume(pathFile);
    }

    private static void buidResume(String pathFile) {
        ResumeScanner resumeScanner = new PropertiesFileScanner(pathFile);
        HtmlPrinter htmlPrinter = new HtmlPrinter();
        ResumePrinter resumePrinter = htmlPrinter;
        resumePrinter.setScanner(resumeScanner);
        try {
            resumePrinter.print();
            System.out.println("successfully");
        } catch (Exception e) {
            System.err.println("fail");
            e.printStackTrace();
        }
    }
}
