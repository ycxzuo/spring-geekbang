package com.yczuoxin.demo;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * 文件资源管理示例
 *
 * @author zuoxin
 */
public class EncodedFileSystemResourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        String path = "/" + System.getProperty("user.dir") + "\\resource\\src\\main\\java\\com\\yczuoxin\\demo\\EncodedFileSystemResourceLoaderDemo.java";
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        // FileSystemResource -> WritableResource -> Resource
        Resource resource = resourceLoader.getResource(path);
        // 使用 spring 的 StreamUtils 工具类转换字节输入流为 String
        //System.out.println(StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8));
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }
}
