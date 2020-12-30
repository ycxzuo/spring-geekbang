package com.yczuoxin.demo.springx;

import java.net.URL;

/**
 * 启动时需要加上启动参数才能加上 springx 这个协议
 * -Djava.protocol.handler.pkgs=com.yczuoxin.demo
 * @see URL#getURLStreamHandler(String)
 */
public class Handler extends sun.net.www.protocol.x.Handler {

}
