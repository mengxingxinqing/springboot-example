package com.tong.httputil;

import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@java.lang.annotation.Target(METHOD)
@Retention(RUNTIME)
public @interface GetLine {
    String value() default "";
}
