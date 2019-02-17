package com.simbirsoft.maketalents.resume_builder.dal.impl;

import com.simbirsoft.maketalents.resume_builder.dal.ResumeScanner;
import com.simbirsoft.maketalents.resume_builder.util.Logger;

import java.io.*;
import java.util.*;

public class PropertiesFileScanner implements ResumeScanner {

    private static final char TAG_SEPARATOR = '=';
    private static final String CONTEXT_SEPARATOR_REGEX = "\\|";
    private final String pathFile;

    private Map<TagTypes, List<String>> infoSource;

    public PropertiesFileScanner(String pathFile) {
        this.pathFile = pathFile;
        infoSource = new HashMap<>();
        processingFile(pathFile);
    }

    //TODO: переписать чтобы кириллица читалась
    private void processingFile(String pathFile) {
       // try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile))) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                processingLine(line);
            }
        } catch (FileNotFoundException e) {
            Logger.processingEx(e);
        } catch (InvalidPropertiesFormatException e) {
            Logger.processingEx(e);
        } catch (IOException e) {
            Logger.processingEx(e);
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

            try {
                String temp = new String(tag.getBytes("UTF-8"));

                if (tag.equals(tags[i].toString())) {
                    return true;
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        }
        return false;
    }

    @Override
    public String getName() {
        return trimList(infoSource.get(TagTypes.FIO).toString());
    }

    private String trimList(String s) {
        return s.substring(1, s.length()-1);
    }

    @Override
    public String getDateOfBorn() {
        return trimList(infoSource.get(TagTypes.DOB).toString());
    }

    @Override
    public List<String> getPhoneNumbers() {
        return infoSource.get(TagTypes.PHONE);
    }

    @Override
    public List<String> getEmails() {
        return infoSource.get(TagTypes.EMAIL);
    }

    @Override
    public String getSkype() {
        return trimList(infoSource.get(TagTypes.SKYPE).toString());
    }

    @Override
    public String getUrlAvatar() {
        return trimList(infoSource.get(TagTypes.URL_AVATAR).toString());
    }

    @Override
    public List<String> getTargets() {
        return infoSource.get(TagTypes.TARGET);
    }

    @Override
    public List<String> getExperience() {
        return infoSource.get(TagTypes.EXPERIENCE);
    }

    @Override
    public List<String> getBasicEducation() {
        return infoSource.get(TagTypes.BS_EDUCATION);
    }

    @Override
    public List<String> getAdditionalEducation() {
        return infoSource.get(TagTypes.AD_EDUCATION);
    }

    @Override
    public String getOtherInfo() {
        return trimList(infoSource.get(TagTypes.OTHER_INFO).toString());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.format("%s = %s\n", "file", pathFile));
        stringBuilder.append("FIO=").append(getName()).append("\n").
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
