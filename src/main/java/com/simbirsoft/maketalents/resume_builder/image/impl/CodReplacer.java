package com.simbirsoft.maketalents.resume_builder.image.impl;

import java.util.Map;
import java.util.Set;

public interface CodReplacer{

    String getPreCod();

    Map<String,String> getSubstitution();

    default String getPostCod() {
        String postCod = new String(getPreCod());
        Set<Map.Entry<String, String>> entrySet = getSubstitution().entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String regEx =  new StringBuilder("\\$\\{").append(entry.getKey()).append("\\}").toString();
            postCod = postCod.replaceAll(regEx, entry.getValue());
        }
        return postCod;
    }
}
