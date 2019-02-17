package com.simbirsoft.maketalents.resume_builder.util;

public class Logger {

    //TODO: переписать вывод об ошибках в файл. Создавать файл заново при перезапуске программы
    public static void processingEx(Exception e){
        System.out.println(e);
    }
}
