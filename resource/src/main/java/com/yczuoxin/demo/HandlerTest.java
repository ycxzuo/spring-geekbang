package com.yczuoxin.demo;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HandlerTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("x:///META-INF/default.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));

        // 需要启动 JVM 参数 -Djava.protocol.handler.pkgs=com.yczuoxin.demo
        URL url2 = new URL("springx:///META-INF/product.properties");
        InputStream inputStream2 = url2.openStream();
        System.out.println(StreamUtils.copyToString(inputStream2, StandardCharsets.UTF_8));
    }
}
