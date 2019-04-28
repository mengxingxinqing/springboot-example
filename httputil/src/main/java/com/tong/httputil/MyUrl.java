package com.tong.httputil;

@DataSource(name = "/aaaa")
public interface MyUrl {
    @GetLine("/bbbb")
    public User getUser(@Param("uid") String uid,@Param("age") int age);
}
