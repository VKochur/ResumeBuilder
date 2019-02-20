package com.simbirsoft.maketalents.resume_builder.image.impl;

import com.simbirsoft.maketalents.resume_builder.dal.ResumeProvider;
import com.simbirsoft.maketalents.resume_builder.image.impl.HtmlResumeCodCreator;

public class CustomResumeCodCreator implements HtmlResumeCodCreator {

    private static final String TITLE = "Резюме";
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
        StringBuilder html =  new StringBuilder("<!DOCTYPE html>");
        html.append("<html>");
        addHead(html);
        addBody(html);
        html.append("</html>");
        return html.toString();
    }

    private void addBody(StringBuilder html) {
        html.append("<body>");
        html.append("Резюме на должность ").append(resumeProvider.getCareerTarget());
        html.append("<br>");
        html.append(resumeProvider.getName());
        html.append("<br>");
        html.append(resumeProvider.getDateOfBorn());
        html.append("<br>");
        html.append(resumeProvider.getPhoneNumbers());
        html.append("<br>");
        html.append(resumeProvider.getEmails());
        html.append("<br>");
        html.append(resumeProvider.getSkype());
        html.append("<br>");
        html.append(resumeProvider.getUrlAvatar());
        html.append("<br>");
        html.append(resumeProvider.getTargets());
        html.append("<br>");
        html.append(resumeProvider.getExperience());
        html.append("<br>");
        html.append(resumeProvider.getBasicEducation());
        html.append("<br>");
        html.append(resumeProvider.getAdditionalEducation());
        html.append("<br>");
        html.append(resumeProvider.getOtherInfo());
        html.append("<br>");

        html.append("</body>");
    }

    private void addHead(StringBuilder html) {
        html.append("<head>");
        html.append("<meta charset=\"utf-8\" />");
        html.append("<title>");
        html.append(TITLE).append(" ").append(resumeProvider.getName());
        html.append("</title>");
        html.append("</head>");
    }
}
