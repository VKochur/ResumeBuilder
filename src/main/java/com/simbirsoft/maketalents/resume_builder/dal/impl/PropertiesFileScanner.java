package com.simbirsoft.maketalents.resume_builder.dal.impl;

import com.simbirsoft.maketalents.resume_builder.dal.ResumeProvider;
import com.simbirsoft.maketalents.resume_builder.util.Util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Provides info about resume from properties file.
 *
 * Encoding file must be UTF-8 without BOM
 *
 * Set of allowed tags is set of TagTypes
 * Separator between key and value is '='
 * In the case of various option for specific key is used '|' as separator
 */
public class PropertiesFileScanner implements ResumeProvider {

    private static final char TAG_SEPARATOR = '=';
    private static final String CONTEXT_SEPARATOR_REGEX = "\\|";
    private static final String DEFAULT_VALUE_CONTEXT = "";
    private final String pathFile;

    private Map<TagTypes, List<String>> infoSource;

    public PropertiesFileScanner(String pathFile) {
        this.pathFile = pathFile;
        infoSource = new HashMap<>();
        processingFile(pathFile);
    }

    private void processingFile(String pathFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile), StandardCharsets.UTF_8.name()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                processingLine(line);
            }
        } catch (FileNotFoundException e) {
            Util.processingEx(e);
        } catch (InvalidPropertiesFormatException e) {
            Util.processingEx(e);
        } catch (IOException e) {
            Util.processingEx(e);
        }
    }

    private void processingLine(String line) throws InvalidPropertiesFormatException {
        //temp[0] = TAG , temp[1] = CONTEXT
        String[] temp = line.split(String.valueOf(TAG_SEPARATOR), 2);
        if (isCorrectTag(temp[0])) {
            TagTypes tag = TagTypes.valueOf(temp[0]);
            List<String> context = new LinkedList<>();
            context.addAll(Arrays.asList(temp[1].split(String.valueOf(CONTEXT_SEPARATOR_REGEX))));
            infoSource.put(tag, context);
        } else {
            throw new InvalidPropertiesFormatException("Line '" + line + "' has not correct format");
        }
    }

    private boolean isCorrectTag(String tag) {
        TagTypes tags[] = TagTypes.values();
        for (int i = 0; i < tags.length; i++) {
            if (tag.equals(tags[i].toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        if (infoSource.containsKey(TagTypes.FIO)) {
            return presentList(infoSource.get(TagTypes.FIO));
        } else {
            return DEFAULT_VALUE_CONTEXT;
        }
    }

    private String presentList(List<String> list) {
        return list.toString().substring(1, list.toString().length() - 1);
    }

    @Override
    public String getDateOfBorn() {
        if (infoSource.containsKey(TagTypes.DOB)) {
            return presentList(infoSource.get(TagTypes.DOB));
        } else {
            return DEFAULT_VALUE_CONTEXT;
        }
    }

    @Override
    public List<String> getPhoneNumbers() {
        if (infoSource.containsKey(TagTypes.PHONE)) {
            return infoSource.get(TagTypes.PHONE);
        } else {
            return Arrays.asList(DEFAULT_VALUE_CONTEXT);
        }
    }

    @Override
    public List<String> getEmails() {
        if (infoSource.containsKey(TagTypes.EMAIL)) {
            return infoSource.get(TagTypes.EMAIL);
        } else {
            return Arrays.asList(DEFAULT_VALUE_CONTEXT);
        }
    }

    @Override
    public String getSkype() {
        if (infoSource.containsKey(TagTypes.SKYPE)) {
            return presentList(infoSource.get(TagTypes.SKYPE));
        } else {
            return DEFAULT_VALUE_CONTEXT;
        }
    }

    @Override
    public String getUrlAvatar() {
        if (infoSource.containsKey(TagTypes.URL_AVATAR)) {
            return presentList(infoSource.get(TagTypes.URL_AVATAR));
        } else {
            return DEFAULT_VALUE_CONTEXT;
        }
    }

    @Override
    public List<String> getTargets() {
        if (infoSource.containsKey(TagTypes.TARGET)) {
            return infoSource.get(TagTypes.TARGET);
        } else {
            return Arrays.asList(DEFAULT_VALUE_CONTEXT);
        }
    }

    @Override
    public List<String> getExperience() {
        if (infoSource.containsKey(TagTypes.EXPERIENCE)) {
            return infoSource.get(TagTypes.EXPERIENCE);
        } else {
            return Arrays.asList(DEFAULT_VALUE_CONTEXT);
        }
    }

    @Override
    public List<String> getBasicEducation() {
        if (infoSource.containsKey(TagTypes.BS_EDUCATION)) {
            return infoSource.get(TagTypes.BS_EDUCATION);
        } else {
            return Arrays.asList(DEFAULT_VALUE_CONTEXT);
        }
    }

    @Override
    public List<String> getAdditionalEducation() {
        if (infoSource.containsKey(TagTypes.AD_EDUCATION)) {
            return infoSource.get(TagTypes.AD_EDUCATION);
        } else {
            return Arrays.asList(DEFAULT_VALUE_CONTEXT);
        }
    }

    @Override
    public String getOtherInfo() {
        if (infoSource.containsKey(TagTypes.OTHER_INFO)) {
            return presentList(infoSource.get(TagTypes.OTHER_INFO));
        } else {
            return DEFAULT_VALUE_CONTEXT;
        }
    }

    @Override
    public String getCareerTarget() {
        if (infoSource.containsKey(TagTypes.CAREER_TARGET)) {
            return presentList(infoSource.get(TagTypes.CAREER_TARGET));
        } else {
            return DEFAULT_VALUE_CONTEXT;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.format("%s = %s\n", "file", pathFile));
        stringBuilder.append("FIO=").append("CareerTarget=").append(getCareerTarget()).
                append(getName()).append("\n").
                append("DOB=").append(getDateOfBorn()).append("\n").
                append("email=").append(getEmails()).append("\n").
                append("phone=").append(getPhoneNumbers()).append("\n").
                append("skype=").append(getSkype()).append("\n").
                append("avatar=").append(getUrlAvatar()).append("\n").
                append("target=").append(getTargets()).append("\n").
                append("experience=").append(getExperience()).append("\n").
                append("bs_educt=").append(getBasicEducation()).append("\n").
                append("ad_educ=").append(getAdditionalEducation()).append("\n").
                append("other=").append(getOtherInfo()).append("\n");
        return stringBuilder.toString();
    }
}
