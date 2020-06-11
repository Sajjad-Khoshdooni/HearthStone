package Config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Config extends Properties {
    private static Config gui;

    private Config(){
        loadProperties();
    }

    public static Config getInstance(){
        if (gui == null) gui = new Config();
        return gui;
    }

    private void loadProperties(){
        try {
            load(new FileReader("src//main//resources//Config//ConstantConfig.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getData(String name){
        return Integer.valueOf(getProperty(name.toUpperCase()));
    }
}