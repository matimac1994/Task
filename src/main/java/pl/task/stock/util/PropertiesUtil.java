package pl.task.stock.util;

import pl.task.stock.MainClass;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    public static String getUrlFromProperties(){
        Properties properties = new Properties();
        try {
            properties.load(MainClass.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty("url");
    }
}
