package com.tong.httputil;

import com.tong.nameservice.NameServiceConfig;

public class NameService {
    public static NameServiceConfig config;

    static {
        config = new NameServiceConfig();
    }

    public String getUrlByName(String str){
        if(str.startsWith("name://")){

        }
        return str;
    }
}
