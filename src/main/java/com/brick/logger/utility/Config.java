package com.brick.logger.utility;

import com.brick.logger.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/*
    Description: Helps Read and get Config Details from application.yaml
 */
public class Config {

    private static final String LOGGER = "logger";
    public static final String FILE = "file";

    /*
        Singleton Pattern to Ensure Config File is read only once
     */
    private static Config instance;
    public static Config getInstance(){
        if( instance == null ){
            synchronized (Logger.class){
                if( instance == null ){
                    instance = new Config();
                }
            }
        }

        return instance;
    }

    private Map<String,Object> configMap;
    private Config(){
        Yaml yamlFile = new Yaml();

        String fileName = "application.yaml";

        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
            if (in == null) {
                throw new FileNotFoundException("Cannot Find File : "+fileName);
            }

            configMap = yamlFile.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        Description: Returns Logger Configuration from all configuration properties
     */
    public Map<String,Object> getLoggerConfig(){
        if( configMap == null ){
            return new HashMap<>();
        }
        return (Map<String,Object>) configMap.get(LOGGER);
    }
}
