package com.yczuoxin.demo.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PayloadApplicationEventDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(PayloadApplicationEventDemo.class);

        context.refresh();

        //context.publishEvent(new MyPayloadApplicationEvent(context, "hello, world"));
        context.publishEvent(new MyApplicationEvent("hello"));

        context.close();
    }

    static class MyListener implements ApplicationListener<MyApplicationEvent> {

        @Override
        public void onApplicationEvent(MyApplicationEvent event) {
            System.out.println("MyListener: " + event);
        }
    }

    /**
     * 会报错，因为读取不到 MyPayloadApplicationEvent 上面的具体化的泛型
     */
//    static class MyPayloadApplicationEvent extends PayloadApplicationEvent<String> {
//
//        /**
//         * Create a new PayloadApplicationEvent.
//         *
//         * @param source  the object on which the event initially occurred (never {@code null})
//         * @param payload the payload object (never {@code null})
//         */
//        public MyPayloadApplicationEvent(Object source, String payload) {
//            super(source, payload);
//        }
//    }

}
