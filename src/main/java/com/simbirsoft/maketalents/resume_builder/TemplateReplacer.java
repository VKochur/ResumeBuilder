package com.simbirsoft.maketalents.resume_builder;

import com.simbirsoft.maketalents.resume_builder.image.impl.CodReplacerHtmlCreator;
import com.simbirsoft.maketalents.resume_builder.util.Logger;
import com.simbirsoft.maketalents.resume_builder.util.Util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TemplateReplacer extends CodReplacerHtmlCreator {

    private String preCod;

    public TemplateReplacer(String resourcePath) {
        InputStream inputTemplate = getClass().getClassLoader().getResourceAsStream(resourcePath);
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputTemplate, StandardCharsets.UTF_8.name()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            Util.processingEx(e);
        } catch (InvalidPropertiesFormatException e) {
            Util.processingEx(e);
        } catch (IOException e) {
            Util.processingEx(e);
        }

        preCod = fileContent.toString();
    }

    @Override
    public String getPreCod() {
        return preCod;
    }

    @Override
    public Map<String, String> getSubstitution() {
        Map<String, String> substitution = new HashMap<>();
        substitution.put("name", getProvider().getName());
        substitution.put("careerTarget", getProvider().getCareerTarget());
        substitution.put("dateOfBorn", getProvider().getDateOfBorn());
        substitution.put("phone", getPresent(getProvider().getPhoneNumbers()));
        substitution.put("email", getPresent(getProvider().getEmails()));
        substitution.put("skype", getProvider().getSkype());
        substitution.put("avatarUrl", getProvider().getUrlAvatar());
        substitution.put("target", getPresent(getProvider().getTargets()));
        substitution.put("experience", getPresent(getProvider().getExperience()));
        substitution.put("baseEducation", getPresent(getProvider().getBasicEducation()));
        substitution.put("addedEducation", getPresent(getProvider().getAdditionalEducation()));
        substitution.put("otherInfo", getProvider().getOtherInfo());
        return substitution;
    }

    private String getPresent(List<String> emails) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String email : emails) {
            stringBuilder.append(email).append("<br>");
        }
        return stringBuilder.toString();
    }
}
