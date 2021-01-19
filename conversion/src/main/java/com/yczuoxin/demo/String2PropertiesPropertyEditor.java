package com.yczuoxin.demo;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

public class String2PropertiesPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // 重写 setAsText
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        // 使用 setValue 把值临时存在 PropertyEditor 之中，使用的时候用 getValue 拿出来
        setValue(properties);
    }

    /**
     * 将值还原的方式
     *
     * @return
     */
    @Override
    public String getAsText() {
        StringBuilder builder = new StringBuilder();
        Properties properties = (Properties) getValue();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }
}
