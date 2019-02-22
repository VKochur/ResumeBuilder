package com.simbirsoft.maketalents.resume_builder.image.impl;

import com.simbirsoft.maketalents.resume_builder.dal.ResumeProvider;

/**
 * Creates html code by replace template
 *
 * template is a string returned from method getPostCode()
 * All substrings in template like ${keyValue} replaces to value : getSubstitution().getKey(keyValue) = value
 */
public abstract class CodeReplacerHtmlCreator implements HtmlResumeCodeCreator, CodeReplacer {

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
    public String getHtmlCode() {
        return getPostCode();
    }
}
