package com.tong.httputil;

import java.lang.annotation.Retention;
import java.util.Map;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@java.lang.annotation.Target(PARAMETER)
public @interface Header {

}
