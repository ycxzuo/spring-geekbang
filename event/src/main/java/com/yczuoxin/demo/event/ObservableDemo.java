package com.yczuoxin.demo.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

public class ObservableDemo {

    public static void main(String[] args) {
        EventObservable observable = new EventObservable();
        observable.addObserver(new EventObserver());
        observable.notifyObservers(new EventObject("hello, World"));
    }

    /**
     * 必须将 changed 变量变为 true 才能发布消息，改变 changed 变量需要调用 setChanged()
     * 因为 setChanged() 方法是 protected 必须继承然后使用这个方法
     */
    static class EventObservable extends Observable {
        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(arg);
        }
    }

    static class EventObserver implements Observer, EventListener {

        @Override
        public void update(Observable o, Object arg) {
            System.out.println("接收到事件：" + arg);
        }
    }

}
