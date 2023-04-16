package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesReader {

    private Properties properties;

    private String baseUrl;
    private String inventoryUrl;

    public PropertiesReader(String fileLocation) {
        initProperties(fileLocation);
        loadProperties();
    }

    private void loadProperties() {
        baseUrl = properties.getProperty("baseUrl");
        inventoryUrl = properties.getProperty("inventoryUrl");
    }

    public void initProperties(String fileLocation) {
        properties = new Properties();

        try {
            properties.load(new InputStreamReader(new FileInputStream(fileLocation)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getInventoryUrl() {
        return inventoryUrl;
    }
}
