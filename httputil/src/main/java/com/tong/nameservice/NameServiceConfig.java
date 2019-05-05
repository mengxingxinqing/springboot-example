package com.tong.nameservice;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class NameServiceConfig {
    public String[] getHosts() {
        return hosts;
    }

    public void setHosts(String[] hosts) {
        this.hosts = hosts;
    }

    private String[] hosts;
    public NameServiceConfig(){
        InputStream inStream = NameServiceConfig.class.getClassLoader().getResourceAsStream("application.properties");
        Properties prop = new Properties();
        try {
            prop.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String values = prop.getProperty("nameservice.hosts");
        hosts = values.trim().split(",");
    }

}
