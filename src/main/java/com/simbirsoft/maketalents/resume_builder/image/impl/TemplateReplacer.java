package com.simbirsoft.maketalents.resume_builder.image.impl;

import com.simbirsoft.maketalents.resume_builder.image.impl.CodeReplacerHtmlCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates html cod by replace template
 *
 * template is html code in resources
 * All substrings in template like ${keyValue} replaces to value : getSubstitution().getKey(keyValue) = value
 */
public class TemplateReplacer extends CodeReplacerHtmlCreator {

    private String preCod;

    public TemplateReplacer(String resourcePath) throws IOException {
        InputStream inputTemplate = getClass().getClassLoader().getResourceAsStream(resourcePath);
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputTemplate, StandardCharsets.UTF_8.name()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (NullPointerException e) {
            throw new IOException("not found template resource for html: " + resourcePath, e);
        }
        preCod = fileContent.toString();

    }

    @Override
    public String getPreCode() {
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

    private String getPresent(List<String> lines) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line).append("<br>");
        }
        return stringBuilder.toString();
    }
}
