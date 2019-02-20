package com.simbirsoft.maketalents.resume_builder.image.impl;

import java.io.*;

public interface FileCreator {

    String getPathDirToFile();

    void setPathDirToFile(String pathDirToFile);

    String getNameFile();

    void setNameFile(String nameFile);

    default void checkPathDir() throws IOException {
        File directory = new File(getPathDirToFile()).getAbsoluteFile();
        if (!directory.exists()) {
            throw new IOException("path not exists: " + directory.getAbsolutePath());
        }
        if (!directory.isDirectory()) {
            throw new IOException("path is not directory: " + directory.getAbsolutePath());
        }
        if (!directory.canWrite()) {
            throw new IOException("not able to write to directory: " + directory.getAbsolutePath());
        }
    }

    default void createFile(String content, String typeFile) throws IOException {
        checkPathDir();
        File file = new File(String.format("%s%s%s%c%s", new File(getPathDirToFile()).getAbsolutePath(), System.getProperty("file.separator"), getNameFile(),'.',typeFile));
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
    }

}
