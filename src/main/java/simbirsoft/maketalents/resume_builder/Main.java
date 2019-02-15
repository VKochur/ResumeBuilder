package main.java.simbirsoft.maketalents.resume_builder;

import main.java.simbirsoft.maketalents.resume_builder.dal.ResumeScanner;
import main.java.simbirsoft.maketalents.resume_builder.dal.impl.PropertiesFileScanner;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String pathFile = "example.properties";
        buidResume(pathFile);
    }

    private static void buidResume(String pathFile) {
       System.out.println(  Arrays.toString("sdfsdfsd|sdfsdf|gfd34".split("|")) );

        /*
       ResumeScanner resumeScanner = new PropertiesFileScanner(pathFile);
       System.out.println("имя: " + resumeScanner.getName());
       System.out.println("дата рождения: " + resumeScanner.getDateOfBorn());
       System.out.println("почта " + resumeScanner.getEmail());
        */

    }
}
