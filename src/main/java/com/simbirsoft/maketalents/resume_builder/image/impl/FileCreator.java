package com.simbirsoft.maketalents.resume_builder.image.impl;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Creates text file
 */
public interface FileCreator {

    String getPathDirToFile();

    void setPathDirToFile(String pathDirToFile);

    String getNameFile();

    void setNameFile(String nameFile);

    /**
     * Creates text file
     *
     * @param content
     * @param typeFile sample: "txt", "html", "java" etc
     * @throws IOException in case if not exists specific directory, or no access
     */
    default void createFile(String content, String typeFile) throws IOException {
        File file = new File(String.format("%s%s%s%c%s", new File(getPathDirToFile()).getAbsolutePath(), System.getProperty("file.separator"), getNameFile(),'.',typeFile));
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8.name()))) {
                writer.write(content);
                writer.flush();
        }
    }

}
