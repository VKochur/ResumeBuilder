package main.java.simbirsoft.maketalents.resume_builder.dal.impl;

import main.java.simbirsoft.maketalents.resume_builder.dal.ResumeScanner;
import main.java.simbirsoft.maketalents.resume_builder.util.Logger;

import java.io.*;
import java.util.*;

public class PropertiesFileScanner implements ResumeScanner {

    private static final char TAG_SEPARATOR = '=';
    private static final char CONTEXT_SEPARATOR = '|';

    private Map<TagTypes, List<String>> infoSource;

    public PropertiesFileScanner(String pathFile) {
        infoSource = new HashMap<>();
        processingFile(pathFile);
    }

    //TODO: переписать чтобы кириллица читалась
    private void processingFile(String pathFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                processingLine(line);
            }
        } catch (FileNotFoundException e) {
            Logger.processingEx(e);
        } catch (InvalidPropertiesFormatException e){
            Logger.processingEx(e);
        } catch (IOException e) {
            Logger.processingEx(e);
        }
    }

    private void processingLine(String line) throws InvalidPropertiesFormatException {
        //temp[0] = TAG , temp[1] = CONTEXT
        String[] temp = line.split(String.valueOf(TAG_SEPARATOR), 2);
        if (isCorrectTag(temp[0])){
            TagTypes tag = TagTypes.valueOf(temp[0]);
                List<String> context = new LinkedList<>();
                context.addAll(Arrays.asList(temp[1].split(String.valueOf(CONTEXT_SEPARATOR))));
                infoSource.put(tag, context);
        } else{
            throw new InvalidPropertiesFormatException("Line '" + line + "' has not correct format");
        }
    }

    private boolean isCorrectTag(String tag) {
        TagTypes tags[] = TagTypes.values();
        for (int i = 0; i < tags.length; i++) {
            if (tag.equals(tags[i].toString())){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return infoSource.get(TagTypes.FIO).toString();
    }

    @Override
    public String getDateOfBorn() {
        return infoSource.get(TagTypes.DOB).toString();
    }

    @Override
    public List<String> getPhone() {
        return infoSource.get(TagTypes.PHONE);
    }

    @Override
    public List<String> getEmail() {
        return infoSource.get(TagTypes.EMAIL);
    }

    @Override
    public String getSkype() {
        return infoSource.get(TagTypes.SKYPE).toString();
    }

    @Override
    public String getUrlAvatar() {
        return infoSource.get(TagTypes.URL_AVATAR).toString();
    }

    @Override
    public List<String> getTarget() {
        return infoSource.get(TagTypes.TARGET);
    }

    @Override
    public List<String> getExperience() {
        return infoSource.get(TagTypes.EXPERIENCE);
    }

    @Override
    public List<String> getBasicEducation() {
        return infoSource.get(TagTypes.BD_EDUCATION);
    }

    @Override
    public List<String> getAdditionalEducation() {
        return infoSource.get(TagTypes.AD_EDUCATION);
    }

    @Override
    public String getOtherInfo() {
        return infoSource.get(TagTypes.OTHER_INFO).toString();
    }
}
