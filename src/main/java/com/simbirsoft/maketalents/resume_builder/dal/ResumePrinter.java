package com.simbirsoft.maketalents.resume_builder.dal;

public interface ResumePrinter {

    void setScanner(ResumeScanner resumeScanner);

    void print() throws Exception;
}
