package main.java.simbirsoft.maketalents.resume_builder.dal;

import java.util.List;

/**
 *
 */
public interface ResumeScanner {

    public String getName();

    public String getDateOfBorn();

    public List<String> getPhone();

    public List<String> getEmail();

    public String getSkype();

    public String getUrlAvatar();

    public List<String> getTarget();

    public List<String> getExperience();

    public List<String> getBasicEducation();

    public List<String> getAdditionalEducation();

    public String getOtherInfo();

}
