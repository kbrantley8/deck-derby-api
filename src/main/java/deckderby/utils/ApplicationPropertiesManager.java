package deckderby.utils;

import deckderby.enums.Environment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationPropertiesManager {
    private static Properties properties;

    public static void loadProperties(String env) {
        if (properties == null) {
            properties = new Properties();
            try {
                FileInputStream fileInputStream = new FileInputStream(String.format("src/main/resources/application-%s.properties", env));
                properties.load(fileInputStream);
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
