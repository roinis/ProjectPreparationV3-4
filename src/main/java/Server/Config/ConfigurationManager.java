package Server.Config;

import Server.util.json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager configurationManager;
    private static Configuration myCurrConfiguration;

    public ConfigurationManager(){

    }

    public static ConfigurationManager getInstance(){
        if(configurationManager == null)
            configurationManager = new ConfigurationManager();
        return configurationManager;
    }

    public void loadConfigurationFile(String filepath) {
        FileReader filereader = null;
        try {
            filereader = new FileReader(filepath);
        } catch (FileNotFoundException e){
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ((i = filereader.read()) != -1){
                sb.append((char)i);
            }
        } catch (IOException e){
            throw new HttpConfigurationException(e);
        }
        JsonNode conf = null;
        try {
            conf = json.parse(sb.toString());
        } catch (IOException e){
            throw new HttpConfigurationException("Parse",e);
        }
        try {
            myCurrConfiguration = json.fromJson(conf,Configuration.class);
        } catch (IOException e){
            throw new HttpConfigurationException("fromJson",e);
        }
    }

    public Configuration getCurrConfiguration(){
        if(myCurrConfiguration == null){
            throw new HttpConfigurationException("No current configuration set.");
        }
        return myCurrConfiguration;
    }
}
