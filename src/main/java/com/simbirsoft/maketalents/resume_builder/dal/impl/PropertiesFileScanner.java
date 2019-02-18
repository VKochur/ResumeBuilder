package com.simbirsoft.maketalents.resume_builder.dal.impl;

import com.simbirsoft.maketalents.resume_builder.dal.ResumeScanner;
import com.simbirsoft.maketalents.resume_builder.util.Util;

import java.io.*;
import java.util.*;

public class PropertiesFileScanner implements ResumeScanner {

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
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile)))) {
            String line;

            //first line can starts with BOM in UTF-8, or not
            line = bufferedReader.readLine();
            try {
                processingLine(line);
            } catch (InvalidPropertiesFormatException e) {
                //probably file starts with BOM
                line = new String(Arrays.copyOfRange(line.getBytes(),3, line.getBytes().length));
                processingLine(line);
            }

            //other line must be correct in terms of Tags
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
            return trimList(infoSource.get(TagTypes.FIO).toString());
        } else {
            return DEFAULT_VALUE_CONTEXT;
        }
    }

    private String trimList(String s) {
        return s.substring(1, s.length() - 1);
    }

    @Override
    public String getDateOfBorn() {
        if (infoSource.containsKey(TagTypes.DOB)) {
            return trimList(infoSource.get(TagTypes.DOB).toString());
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
            return trimList(infoSource.get(TagTypes.SKYPE).toString());
        } else {
            return DEFAULT_VALUE_CONTEXT;
        }
    }

    @Override
    public String getUrlAvatar() {
        if (infoSource.containsKey(TagTypes.URL_AVATAR)) {
            return trimList(infoSource.get(TagTypes.URL_AVATAR).toString());
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
            return trimList(infoSource.get(TagTypes.OTHER_INFO).toString());
        } else {
            return DEFAULT_VALUE_CONTEXT;
        }
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
