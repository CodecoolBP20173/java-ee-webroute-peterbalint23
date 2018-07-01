package com.codecool.webserver;

import com.codecool.annotations.WebRoute;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class Routes {

    private void responseWriter(HttpExchange t, String response) throws IOException {
        t.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    @WebRoute(path = "/")
    void mainPage(HttpExchange t) throws IOException {
        String response = "<h1 style=\"color:red;\" >This is the main page</h1>";
        responseWriter(t, response);


    }

    @WebRoute(path = "/test")
    void onTest(HttpExchange t) throws IOException {
        String response = "<h1 style=\"color:yellow;\">This is the first test</h1>";
        responseWriter(t, response);


    }

    @WebRoute(path = "/second-test")
    void onSecondTest(HttpExchange t) throws IOException {
        String response = "<h1 style=\"color:blue;\">This is the second test</h1>";
        responseWriter(t, response);
    }


}
