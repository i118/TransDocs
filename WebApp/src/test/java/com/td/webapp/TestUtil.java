package com.td.webapp;


import org.springframework.http.MediaType;

import java.nio.charset.Charset;

/**
 * Created by konstantinchipunov on 18.08.14.
 */
public class TestUtil {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );
}
