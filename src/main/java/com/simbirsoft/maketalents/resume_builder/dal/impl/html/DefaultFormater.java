package com.simbirsoft.maketalents.resume_builder.dal.impl.html;

import java.util.List;

public class DefaultFormater implements HtmlFormater {

    private static final String TITLE = "Резюме";
    private String name;
    private String dateOfBorn;
    private List<String> phoneNumbers;
    private List<String> emails;
    private String skype;
    private String urlAvatar;
    private List<String> targets;
    private List<String> experience;
    private List<String> basicEducation;
    private List<String> additionalEducation;
    private String otherInfo;

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
        html.append(name);
        html.append("<br>");
        html.append(dateOfBorn);
        html.append("<br>");
        html.append(phoneNumbers);
        html.append("<br>");
        html.append(emails);
        html.append("<br>");
        html.append(skype);
        html.append("<br>");
        html.append(urlAvatar);
        html.append("<br>");
        html.append(targets);
        html.append("<br>");
        html.append(experience);
        html.append("<br>");
        html.append(basicEducation);
        html.append("<br>");
        html.append(additionalEducation);
        html.append("<br>");
        html.append(otherInfo);
        html.append("<br>");

        html.append("</body>");
    }

    private void addHead(StringBuilder html) {
        html.append("<head>");
        html.append("<meta charset=\"utf-8\" />");
        html.append("<title>");
        html.append(TITLE).append(" ").append(name);
        html.append("</title>");
        html.append("</head>");
    }



    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDateOfBorn(String dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    @Override
    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    @Override
    public void setSkype(String login) {
        this.skype = login;
    }

    @Override
    public void setUrlAvatar(String url) {
        this.urlAvatar = url;
    }

    @Override
    public void setTargets(List<String> targets) {
        this.targets = targets;
    }

    @Override
    public void setExperience(List<String> experience) {
        this.experience = experience;
    }

    @Override
    public void setBasicEducation(List<String> education) {
        this.basicEducation = education;
    }

    @Override
    public void setAdditionalEducation(List<String> education) {
        this.additionalEducation = education;
    }

    @Override
    public void setOtherInfo(String info) {
        this.otherInfo = info;
    }
}
