package com.simbirsoft.maketalents.resume_builder.dal;

import java.io.IOException;

public interface ResumePrinter {
    void setScanner(ResumeScanner resumeScanner);

    void print() throws Exception;
}
