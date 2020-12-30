package com.yczuoxin.demo;

import com.yczuoxin.demo.utils.ResourceUtil;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 自定义匹配资源查找
 *
 * @author zuoxin
 */
public class CustomResourcePatternMatcherDemo {
    public static void main(String[] args) throws IOException {
        String path = "/" + System.getProperty("user.dir") + "/resource/src/main/java/com/yczuoxin/demo/";
        String matchPath = path + "*.java";
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        resolver.setPathMatcher(new JavaFilePathMatcher());
        Resource[] resources = resolver.getResources(matchPath);
        Stream.of(resources).map(ResourceUtil::printResource).forEach(System.out::println);
    }

    static class JavaFilePathMatcher implements PathMatcher {

        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }
}
