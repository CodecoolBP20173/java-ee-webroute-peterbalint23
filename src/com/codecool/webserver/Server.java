package com.codecool.webserver;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.Arrays;

import com.codecool.annotations.WebRoute;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {
    static HttpServer server;

    public static void main(String[] args) throws Exception {

        server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());

        server.setExecutor(null); // creates a default executor
        server.start();
    }


    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            String requestedPath = t.getRequestURI().getPath();
            Class aClass = Routes.class;
            Method[] methods = aClass.getDeclaredMethods();

            for (Method method : methods
                    ) {
                if(method.getAnnotation(WebRoute.class) != null){
                    WebRoute annotation = method.getAnnotation(WebRoute.class);
                    if (annotation.path().equals(requestedPath)){
                        try {
                            method.invoke(new Routes(),t);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }

    }
}
