package com.tong.httputil;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;

public class Decode {
    public static Object decode(String resp, Type returnType){
        if(String.class == returnType) return resp;
        if(Void.class == returnType) return null;
        if(StringUtils.isEmpty(resp)) return null;
        return new Gson().fromJson(resp, returnType);
    }
}
