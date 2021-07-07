package com.example;

import java.net.MalformedURLException;
// import com.google.gson.JsonElement;
// import com.google.gson.JsonArray;

/**
 * Driver class
 */
public class App {
    public static void main(String[] args) {
        // System.out.println("Hello World!");

        try {
            Http swapi = new Http("https://swapi.dev/api/films/1");
            System.out.println(swapi.fullResponse(true));
        }

        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
