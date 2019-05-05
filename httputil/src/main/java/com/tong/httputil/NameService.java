package com.tong.httputil;

import com.google.gson.Gson;
import com.tong.nameservice.NameServiceConfig;
import com.tong.nameservice.NameServiceStruct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NameService {
    public static NameServiceConfig config;

    static {
        config = new NameServiceConfig();
    }

    public static String getUrlByName(String str){
        if(str.startsWith("name://")){
            Map<String, String> params = new HashMap<>();
            params.put("uri",str);
            String resp = HttpUtil.post(config.getUrl(),params,null);
            NameServiceStruct nameServiceStruct = new Gson().fromJson(resp, NameServiceStruct.class);
            if(nameServiceStruct.getErrno() != 0){
                return null;
            }
            List<String> hosts = nameServiceStruct.getHosts();
            return hosts.get(config.balance(hosts.size()));
        }
        return str;
    }



}
