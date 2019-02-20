package com.simbirsoft.maketalents.resume_builder.dal;

import java.util.List;

/**
 * Interface provides data about resume
 */
public interface ResumeProvider {

    String getName();

    String getDateOfBorn();

    List<String> getPhoneNumbers();

    List<String> getEmails();

    String getSkype();

    String getUrlAvatar();

    List<String> getTargets();

    List<String> getExperience();

    List<String> getBasicEducation();

    List<String> getAdditionalEducation();

    String getOtherInfo();

    String getCareerTarget();

}
