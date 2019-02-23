package com.simbirsoft.maketalents.resume_builder.image.impl;

import java.util.Map;
import java.util.Set;

public interface CodeReplacer {

    String getPreCode();

    Map<String,String> getSubstitution();

    default String getPostCode() {
        String postCode = new String(getPreCode());
        Set<Map.Entry<String, String>> entrySet = getSubstitution().entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String regEx =  new StringBuilder("\\$\\{").append(entry.getKey()).append("\\}").toString();
            postCode = postCode.replaceAll(regEx, entry.getValue());
        }
        return postCode;
    }
}
