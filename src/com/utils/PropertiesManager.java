package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The {@code PropertiesManager} class is a utility class for managing properties loaded from "application.properties" file.
 * "application.properties" file located in the resources directory.
 * <p>
 * Because the database path settings often change depending on where we deploy the application,
 * it is common practice to store them in a separate file.
 * </p>
 */

public final class PropertiesManager {
    private static final Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        loadProperties();
    }

    /**
     * Utility class means private constructor.
     * */
    private PropertiesManager(){}

    /**
     * Method to load properties to PROPERTIES object.
     * */
    private static void loadProperties(){
        try {
        InputStream inputStream =
                PropertiesManager.class.
                getClassLoader().
                getResourceAsStream("application.properties");

            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the value of the property with the specified key.
     *
     * @param key the key of the property
     * @return the value of the property, or {@code null} if the property is not found
     */
    public static String getProperty(String key){
        return PROPERTIES.getProperty(key);
    }
}