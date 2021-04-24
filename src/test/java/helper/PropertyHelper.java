package helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class first try to read environemt properties (usefull in Jenkins)
 * If they are not set then it use the config.properties file instead
 */
public class PropertyHelper {
    public static String getProperties(String property) {
        String env = System.getenv("poc_"+property);
        System.getenv();

        if (env !=null) {
            return env;
        } else {
            String propertyValue = "";
            try (InputStream input = new FileInputStream("src/test/config.properties")) {
                Properties prop = new Properties();
                // load a properties file
                prop.load(input);
                propertyValue = prop.getProperty(property);
            } catch (IOException ex) { ex.printStackTrace(); }
            return propertyValue;
        }
    }
}

