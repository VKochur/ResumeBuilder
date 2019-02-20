package com.simbirsoft.maketalents.resume_builder.image.impl;

import com.simbirsoft.maketalents.resume_builder.dal.ResumeProvider;

public abstract class CodReplacerHtmlCreator implements HtmlResumeCodCreator, CodReplacer {

    private ResumeProvider resumeProvider;

    @Override
    public void setProvider(ResumeProvider resumeProvider) {
        this.resumeProvider = resumeProvider;
    }

    @Override
    public ResumeProvider getProvider() {
        return resumeProvider;
    }

    @Override
    public String getHtmlCod() {
        return getPostCod();
    }
}
