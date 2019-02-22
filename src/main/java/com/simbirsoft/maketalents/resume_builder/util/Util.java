package com.simbirsoft.maketalents.resume_builder.util;

import com.simbirsoft.maketalents.resume_builder.Main;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.*;

public class Util {

    private static java.util.logging.Logger logger;

    static {
        logger = java.util.logging.Logger.getLogger(Util.class.getName());

        try {
            FileHandler fh = new FileHandler(Util.getPathCurrentDir() + "\\ResumeBuilder.log");
            fh.setFormatter(new Formatter() {
                private static final String format = "[%s][%s] %s %s %n";

                @Override
                public synchronized String format(LogRecord lr) {
                    String stackTrace ="";
                    if (lr.getThrown() != null) {
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        lr.getThrown().printStackTrace(pw);
                        stackTrace = "\n" + sw.toString();
                    }

                    return String.format(format,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage(), stackTrace
                    );
                }
            });
            logger.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    //получить текущий каталог где лежит
    public static String getPathCurrentDir() {
        File file = null;
        try {
            file = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file.getParent();
    }
}
