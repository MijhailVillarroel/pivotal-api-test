package org.fundacionjala.pivotal.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This class is for get the properties defined into properties file
 *
 * @author RosarioGarcia
 */
public class Environment {

    private static final Logger LOGGER = Logger.getLogger(Environment.class.getSimpleName());

    private static final String CONFIG_PROPERTIES = "gradle.properties";

    private static final String PROXY_PORT = "proxyPort";

    private static final String PROXY_HOST = "proxyHost";

    private static final String EMAIL = "email";

    private static final String PASSWORD = "password";

    private static final String URL_API = "urlApi";

    private static final String API_TOKEN = "apiToken";

    private static Environment instance;

    private Properties properties;

    private Environment() {
        loadProperties();
    }

    public static Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
        }
        return instance;
    }

    /**
     * This method is used to load the .properties file
     * which contains the configurations to project
     */
    private void loadProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream(CONFIG_PROPERTIES);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            LOGGER.warn("The properties file couldn't be found", e.getCause());
        } catch (IOException e) {
            LOGGER.warn("A problem of type", e.getCause());
        }
    }

    public String getProperty(String propertyKey) {
        String propertyValue = System.getProperty(propertyKey);
        if (propertyValue == null) {
            propertyValue = properties.getProperty(propertyKey);
        }
        return propertyValue;
    }

    public String getEmail() {
        return getProperty(EMAIL);
    }

    public String getPassword() {
        return getProperty(PASSWORD);
    }

    public String getUrlApi() {
        return getProperty(URL_API);
    }

    public String getApiToken() {
        return getProperty(API_TOKEN);
    }

    public String getProxy() {
        return String.format("http://%s:%s", getProxyHost(), getProxyPort());
    }

    private String getProxyHost() {
        return getProperty(PROXY_HOST);
    }

    private String getProxyPort() {
        return getProperty(PROXY_PORT);
    }

}
