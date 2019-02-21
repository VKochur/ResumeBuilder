package com.simbirsoft.maketalents.resume_builder.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger implements Closeable {

    private static Logger instance = null;
    private static final String LOG_FILE = "logger.log";

    FileWriter fileWriter;

    private Logger() {
        try {
            fileWriter = new FileWriter(new File(LOG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance(){
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }


    @Override
    public void close() throws IOException {
        fileWriter.close();
    }

    public void addInfo(String info){
        try {
            fileWriter.write(info);
            fileWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
