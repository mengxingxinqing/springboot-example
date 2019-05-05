package com.tong.httputil;

import java.util.Map;

@DataSource(name = "name://test001")
public interface MyUrl {
    @GetLine("/user/get")
    public User getUser(@Param("id") int uid,@Header Map<String,String> header);
}
