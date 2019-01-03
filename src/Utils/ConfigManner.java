package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManner {
    Properties properties = new Properties();
    InputStream is = null;
    private static ConfigManner configManner = null;

    private ConfigManner(){
        //加载数据库配置文件
        is = ConfigManner.class.getClassLoader().getResourceAsStream("database.properties");
        try {
            properties.load(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ConfigManner getInstance(){
        if (configManner == null){
            configManner = new ConfigManner();
        }
        return configManner;
    }
    public String getValue(String key){
        return properties.getProperty(key);
    }

}
