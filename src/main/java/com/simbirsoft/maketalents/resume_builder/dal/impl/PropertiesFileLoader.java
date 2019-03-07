package com.simbirsoft.maketalents.resume_builder.dal.impl;

import com.simbirsoft.maketalents.resume_builder.dal.ResumeProvider;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Provides info about resume from property file.
 *
 * Encoding file must be UTF-8 without BOM
 *
 * Set of allowed tags is set of TagTypes
 * Separator between key and value is '='
 * In the case of various options for specific key is used '|' as separator
 * In case not found specific tag, the associated context is ""
 *
 * Example properties file:
 ' FIO=Name Second_Name
 * DOB=13.08.1983
 * EMAIL=name@rambler.ru|name@gmail.com
 * PHONE=???7???
 * SKYPE=login
 * TARGET=target1|target2|target3
 * CAREER_TARGET=career target
 */
public class PropertiesFileLoader implements ResumeProvider {

    private static final String CONTEXT_SEPARATOR_REGEX = "\\|";
    private static final String DEFAULT_VALUE_CONTEXT = "";

    Properties properties;

    /**
     *
     * @param pathFile path to file properties
     * @throws IOException in case inaccessibility file properties
     */
    public PropertiesFileLoader(String pathFile) throws IOException {
        properties = new Properties();
        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(pathFile), StandardCharsets.UTF_8.name())){
            properties.load(inputStreamReader);
        }
    }

    @Override
    public String getName() {
        return properties.getProperty(TagTypes.FIO.name(), DEFAULT_VALUE_CONTEXT);
    }

    @Override
    public String getDateOfBorn() {
        return properties.getProperty(TagTypes.DOB.name(), DEFAULT_VALUE_CONTEXT);
    }

    @Override
    public List<String> getPhoneNumbers() {
        return defineListByLine(properties.getProperty(TagTypes.PHONES.name(), DEFAULT_VALUE_CONTEXT));
    }

    @Override
    public List<String> getEmails() {
        return defineListByLine(properties.getProperty(TagTypes.EMAILS.name(), DEFAULT_VALUE_CONTEXT));
    }

    @Override
    public String getSkype() {
        return properties.getProperty(TagTypes.SKYPE.name(), DEFAULT_VALUE_CONTEXT);
    }

    @Override
    public String getUrlAvatar() {
        return properties.getProperty(TagTypes.URL_AVATAR.name(), DEFAULT_VALUE_CONTEXT);
    }

    @Override
    public List<String> getTargets() {
        return defineListByLine(properties.getProperty(TagTypes.TARGETS.name(), DEFAULT_VALUE_CONTEXT));
    }

    @Override
    public List<String> getExperience() {
        return defineListByLine(properties.getProperty(TagTypes.EXPERIENCES.name(), DEFAULT_VALUE_CONTEXT));
    }

    @Override
    public List<String> getBasicEducation() {
        return defineListByLine(properties.getProperty(TagTypes.BS_EDUCATIONS.name(), DEFAULT_VALUE_CONTEXT));
    }

    @Override
    public List<String> getAdditionalEducation() {
        return defineListByLine(properties.getProperty(TagTypes.AD_EDUCATIONS.name(), DEFAULT_VALUE_CONTEXT));
    }

    @Override
    public String getOtherInfo() {
        return properties.getProperty(TagTypes.OTHER_INFO.name(), DEFAULT_VALUE_CONTEXT);
    }

    @Override
    public String getCareerTarget() {
        return properties.getProperty(TagTypes.CAREER_TARGET.name(), DEFAULT_VALUE_CONTEXT);
    }

    private List<String> defineListByLine(String str){
        return Arrays.asList(str.split(CONTEXT_SEPARATOR_REGEX));
    }
}
