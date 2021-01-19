package com.yczuoxin.demo;

public class PropertyEditorDemo {
    public static void main(String[] args) {
        String text = "name = yczuo";
        // 创建 PropertyEditor
        String2PropertiesPropertyEditor editor = new String2PropertiesPropertyEditor();
        // 调用重写的 setAsText 临时存储
        editor.setAsText(text);
        // 调用 getValue 获取到存储的值
        System.out.println(editor.getValue());
        // 还原
        System.out.println(editor.getAsText());
    }
}
