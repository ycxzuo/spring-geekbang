package com.yczuoxin.demo.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface ResourceUtil {

    static String printResource(Resource resource) {
        return printResource(resource, StandardCharsets.UTF_8);
    }

    static String printResource(Resource resource, Charset charset) {
        EncodedResource encodedResource = new EncodedResource(resource, charset);
        try (Reader reader = encodedResource.getReader()) {
            return IOUtils.toString(reader);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
