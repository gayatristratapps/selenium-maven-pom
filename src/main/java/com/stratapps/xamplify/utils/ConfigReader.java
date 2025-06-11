package com.stratapps.xamplify.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop = new Properties();

    static {
        try (InputStream input = Thread.currentThread()
                                        .getContextClassLoader()
                                        .getResourceAsStream("config/config.properties")) {
            if (input != null) {
                prop.load(input);
            } else {
                throw new RuntimeException("config/config.properties file not found in classpath");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
