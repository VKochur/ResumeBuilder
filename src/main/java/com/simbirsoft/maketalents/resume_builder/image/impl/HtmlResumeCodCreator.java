package com.simbirsoft.maketalents.resume_builder.image.impl;

import com.simbirsoft.maketalents.resume_builder.dal.ResumeProvider;

/**
 * Creates cod html for resume
 */
public interface HtmlResumeCodCreator {

    void setProvider(ResumeProvider provider);

    ResumeProvider getProvider();

    String getHtmlCod();
}
