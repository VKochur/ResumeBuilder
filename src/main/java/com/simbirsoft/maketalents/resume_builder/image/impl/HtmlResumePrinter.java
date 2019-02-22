package com.simbirsoft.maketalents.resume_builder.image.impl;

import com.simbirsoft.maketalents.resume_builder.image.ResumePrinter;

import java.io.*;

/**
 * Creates file html.
 */
public class HtmlResumePrinter implements ResumePrinter, FileCreator {

    private static final String DEFAULT_DIR_FILE = "";
    private static final String DEFAULT_NAME_FILE = "";
    private String pathDirToFile;
    private String nameFile;
    private HtmlResumeCodeCreator htmlResumeCodCreater;

    public HtmlResumePrinter() {
        pathDirToFile = DEFAULT_DIR_FILE;
        nameFile = DEFAULT_NAME_FILE;
    }

    public void setHtmlResumeCodCreater(HtmlResumeCodeCreator htmlResumeCodCreater) {
        this.htmlResumeCodCreater = htmlResumeCodCreater;
    }

    /**
     * Method creates file html
     *
     * @throws IOException
     */
    @Override
    public void print() throws IOException {
        createFile(htmlResumeCodCreater.getHtmlCode(), "html");
    }

    @Override
    public String getPathDirToFile() {
        return pathDirToFile;
    }

    @Override
    public void setPathDirToFile(String pathDirToFile) {
        this.pathDirToFile = pathDirToFile;
    }

    @Override
    public String getNameFile() {
        return nameFile;
    }

    @Override
    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}
