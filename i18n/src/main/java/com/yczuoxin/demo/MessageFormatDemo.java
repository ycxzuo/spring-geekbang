package com.yczuoxin.demo;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MessageFormatDemo {
    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";

        String messagePattern = "At {1,time,full} on {1,date,full}, there was {2} on planet {0,number,integer}.";

        MessageFormat format = new MessageFormat(messagePattern, Locale.ENGLISH);
        String result = format.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setFormat(1, dateFormat);
        result = format.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        messagePattern = "数字测试 {0, number, #}";
        format.setLocale(Locale.CHINA);
        // 重置需要 pattern 的信息
        format.applyPattern(messagePattern);
        String result1 = format.format(new Object[]{1000000000});
        System.out.println(result1);

//        LocalDate ld = LocalDate.now();
//        Arrays.asList(FormatStyle.FULL, FormatStyle.LONG, FormatStyle.MEDIUM, FormatStyle.SHORT)
//                .forEach(formatStyle -> {
//                    System.out.println(String.format("--- FormatStyle.%s ---",
//                            formatStyle.toString()));
//                    DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(formatStyle);
//                    Arrays.asList(new String[]{"zh", "CN"},
//                            new String[]{"zh", "HK"},
//                            new String[]{"zh", "TW"},
//                            new String[]{"en", "US"},
//                            new String[]{"en", "UK"},
//                            new String[]{"ja", "JP"})
//                            .forEach(strs -> {
//                                Locale locale = new Locale(strs[0], strs[1]);
//                                System.out.println(String.format("%s %s -> %s",
//                                        strs[0],
//                                        strs[1],
//                                        ld.format(dtf.withLocale(locale))));
//                            });
//                });

    }
}
