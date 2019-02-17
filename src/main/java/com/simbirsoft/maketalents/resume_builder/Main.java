package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.dal.ResumePrinter;
import com.simbirsoft.maketalents.resume_builder.dal.ResumeScanner;
import com.simbirsoft.maketalents.resume_builder.dal.impl.HtmlPrinter;
import com.simbirsoft.maketalents.resume_builder.dal.impl.PropertiesFileScanner;

import java.io.File;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String pathFile = "example.properties";
        buidResume(pathFile);
    }

    private static void buidResume(String pathFile) {
      // System.out.println(  Arrays.toString("sdfsdfsd|sdfsdf||gfd34".split("\\|")) );

      //  System.out.println(new File("1").getAbsoluteFile());

       ResumeScanner resumeScanner = new PropertiesFileScanner(pathFile);
       HtmlPrinter htmlPrinter = new HtmlPrinter();
     //  htmlPrinter.setNameHtmlFile("тест резюме");
     //  htmlPrinter.setPathDirToHtmlFile("c://");
       ResumePrinter resumePrinter = htmlPrinter;
       resumePrinter.setScanner(resumeScanner);
        try {
            resumePrinter.print();
            System.out.println("successfully");
        } catch (Exception e) {
            System.err.println("fail");
            e.printStackTrace();
        }

        System.out.println(resumeScanner);


    }
}
