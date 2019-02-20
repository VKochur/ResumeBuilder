package com.simbirsoft.maketalents.resume_builder.image.impl;

import com.simbirsoft.maketalents.resume_builder.util.Util;

import java.io.*;
import java.util.*;

public class FileReplacerHtml extends CodReplacerHtmlCreator {

    private static final String DEFAULT_TEMPLATE = "<!doctype html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <!-- Required meta tags -->\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
            "\n" +
            "    <!-- Bootstrap CSS -->\n" +
            "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n" +
            "\n" +
            "    <title>Резюме ${name}</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div class=\"container\">\n" +
            "    <h1 class=\"text-center\">Резюме</h1>\n" +
            "    <h2 class=\"text-center\">на должность ${careerTarget}</h2>\n" +
            "    <br>\n" +
            "    <div class=\"row\">\n" +
            "        <div class=\"col-8\">\n" +
            "            <table class=\"table\">\n" +
            "                <tr><td class=\"text-right\">ФИО:</td><td>${name}</td></tr>\n" +
            "                <tr><td class=\"text-right\">Дата рождения:</td><td>${dateOfBorn}</td></tr>\n" +
            "                <tr><td class=\"text-right\">Телефон:</td><td>${phone}</td></tr>\n" +
            "                <tr><td class=\"text-right\">e-mail:</td><td>${email}</td></tr>\n" +
            "                <tr><td class=\"text-right\">Skype:</td><td>${skype}</td></tr>\n" +
            "            </table>\n" +
            "        </div>\n" +
            "        <div class=\"col-4\">\n" +
            "            <img src=\"${avatarUrl}\" alt=\"avatar\" class=\"img-fluid\">\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <br>\n" +
            "    <div class=\"col-12\">\n" +
            "        <h2>Цель:</h2>\n" +
            "        <p>${target}</p>\n" +
            "    </div>\n" +
            "    <div class=\"col-12\">\n" +
            "        <h2>Опыт работы:</h2>\n" +
            "        <p>${experience}</p>\n" +
            "    </div>\n" +
            "    <div class=\"col-12\">\n" +
            "        <h2>Образование:</h2>\n" +
            "        <p>${baseEducation}</p>\n" +
            "    </div>\n" +
            "    <div class=\"col-12\">\n" +
            "        <h2>Дополнительное образование и курсы:</h2>\n" +
            "        <p>${addedEducation}</p>\n" +
            "    </div>\n" +
            "    <div class=\"col-12\">\n" +
            "        <h2>Дополнительная информация:</h2>\n" +
            "        <p>${otherInfo}</p>\n" +
            "    </div>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";;

    private String preCod;

    public FileReplacerHtml() {
        preCod = DEFAULT_TEMPLATE;
    }

    public FileReplacerHtml(String pathToTemplateFile){
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(pathToTemplateFile)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
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
