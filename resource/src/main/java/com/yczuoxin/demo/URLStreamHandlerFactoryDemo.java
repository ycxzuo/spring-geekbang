package com.yczuoxin.demo;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLStreamHandlerFactory;
import java.nio.charset.StandardCharsets;

/**
 * {@link URLStreamHandlerFactory} 示例
 */
public class URLStreamHandlerFactoryDemo {
    public static void main(String[] args) throws IOException {
        // 这个必须要先设置，不然的话还是会走之前的逻辑，然后报出找不到 springx 协议
        URL.setURLStreamHandlerFactory(new MyURLStreamHandlerFactory());
        URL url2 = new URL("springx:///META-INF/product.properties");
        InputStream inputStream2 = url2.openStream();
        System.out.println(StreamUtils.copyToString(inputStream2, StandardCharsets.UTF_8));
    }
}
