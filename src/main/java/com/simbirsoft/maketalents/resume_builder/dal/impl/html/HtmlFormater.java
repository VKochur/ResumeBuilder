package com.simbirsoft.maketalents.resume_builder.dal.impl.html;

import java.util.List;

public interface HtmlFormater {

    String getHtmlCod();

    void setName(String name);

    void setDateOfBorn(String dateOfBorn);

    void setPhoneNumbers(List<String> phoneNumbers);

    void setEmails(List<String> emails);

    void setSkype(String login);

    void setUrlAvatar(String url);

    void setTargets(List<String> targets);

    void setExperience(List<String> experience);

    void setBasicEducation(List<String> education);

    void setAdditionalEducation(List<String> education);

    void setOtherInfo(String info);

}
