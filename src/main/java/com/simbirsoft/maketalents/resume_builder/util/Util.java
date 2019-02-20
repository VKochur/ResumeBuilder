package com.simbirsoft.maketalents.resume_builder.util;

import com.simbirsoft.maketalents.resume_builder.Main;

import java.io.*;
import java.net.URISyntaxException;

public class Util {

    //TODO: переписать вывод об ошибках в файл. Создавать файл заново при перезапуске программы
    public static void processingEx(Exception e){
        System.out.println(e);
    }

    //получить текущий каталог где лежит
    public static String getPathCurrentDir(){
        File file = null;
        try {
            file = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file.getParent();
    }
}
