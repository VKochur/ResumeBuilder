package com.simbirsoft.maketalents.resume_builder.dal.impl;

import com.simbirsoft.maketalents.resume_builder.dal.ResumePrinter;
import com.simbirsoft.maketalents.resume_builder.dal.ResumeScanner;
import com.simbirsoft.maketalents.resume_builder.dal.impl.html.DefaultFormater;
import com.simbirsoft.maketalents.resume_builder.dal.impl.html.HtmlFormater;

import java.io.*;

public class HtmlPrinter implements ResumePrinter {

    private static final String DEFAULT_DIR_HTML_FILE = "";
    private ResumeScanner resumeScanner;
    private String pathDirToHtmlFile;
    private String nameHtmlFile;
    private HtmlFormater htmlFormater;

    public HtmlPrinter() {
        pathDirToHtmlFile = DEFAULT_DIR_HTML_FILE;
    }

    @Override
    public void setScanner(ResumeScanner resumeScanner) {
        this.resumeScanner = resumeScanner;
    }

    @Override
    public void print() throws IOException {
        if (resumeScanner != null) {
            createFileHtml();
        } else {
            throw new NullPointerException("ResumeScanner not defined");
        }
    }

    private void checkPathDirToHtml() throws IOException {
        File directory = new File(pathDirToHtmlFile).getAbsoluteFile();
        if (!directory.exists()) {
            throw new IOException("path not exists: " + directory.getAbsolutePath());
        }
        if (!directory.isDirectory()) {
            throw new IOException("path is not directory: " + directory.getAbsolutePath());
        }
        if (!directory.canWrite()) {
            throw new IOException("not able to write to directory: " + directory.getAbsolutePath());
        }
    }

    private void createFileHtml() throws IOException {
        checkPathDirToHtml();
        String codHtml = createHtmlCod();
        String nameHtmlFile = (this.nameHtmlFile != null) ? this.nameHtmlFile : resumeScanner.getName();
        File file = new File(String.format("%s%s%s%s", new File(pathDirToHtmlFile).getAbsolutePath(), System.getProperty("file.separator"), nameHtmlFile, ".html"));
        try(Writer writer = new BufferedWriter(new FileWriter(file))){
            writer.write(codHtml);
        }

        System.out.println("--------------------");
        System.out.println(codHtml);
    }

    private String createHtmlCod() {
        HtmlFormater htmlFormater = (this.htmlFormater == null) ? new DefaultFormater() : this.htmlFormater;
        htmlFormater.setName(resumeScanner.getName());
        htmlFormater.setDateOfBorn(resumeScanner.getDateOfBorn());
        htmlFormater.setPhoneNumbers(resumeScanner.getPhoneNumbers());
        htmlFormater.setEmails(resumeScanner.getEmails());
        htmlFormater.setSkype(resumeScanner.getSkype());
        htmlFormater.setUrlAvatar(resumeScanner.getUrlAvatar());
        htmlFormater.setTargets(resumeScanner.getTargets());
        htmlFormater.setExperience(resumeScanner.getExperience());
        htmlFormater.setBasicEducation(resumeScanner.getBasicEducation());
        htmlFormater.setAdditionalEducation(resumeScanner.getAdditionalEducation());
        htmlFormater.setOtherInfo(resumeScanner.getOtherInfo());
        return htmlFormater.getHtmlCod();
    }

    public String getPathDirToHtmlFile() {
        return pathDirToHtmlFile;
    }

    public void setPathDirToHtmlFile(String pathDirToHtmlFile) {
        this.pathDirToHtmlFile = pathDirToHtmlFile;
    }

    public String getNameHtmlFile() {
        return nameHtmlFile;
    }

    public void setNameHtmlFile(String nameHtmlFile) {
        this.nameHtmlFile = nameHtmlFile;
    }

    public void setHtmlFormater(HtmlFormater htmlFormater) {
        this.htmlFormater = htmlFormater;
    }
}
