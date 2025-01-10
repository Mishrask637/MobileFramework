package baseclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    private static final String configFilePath = System.getProperty("user.dir")+"/src/main/java/properties/config.properties";

    public static Properties readProperties(){
        FileInputStream fis = null;
        Properties properties = null;

        try{
            fis = new FileInputStream(configFilePath);
            properties = new Properties();
            properties.load(fis);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
