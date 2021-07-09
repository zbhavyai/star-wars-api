package com.example;

import java.net.MalformedURLException;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles fetching of resources and printing of data
 */
public class SWapi {
    /**
     * Fetches a JsonObject from a url
     *
     * @param url the API url of a particular resource
     * @return JsonObject associated with that resource
     */
    public static JsonObject fetch(String url) {
        try {
            Http httpObj = new Http(url);
            String jsonResponse = httpObj.fullResponse(false);
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

            return jsonObject;
        }

        catch (MalformedURLException e) {
            System.err.printf("%s%n", e.getMessage());

            return null;
        }
    }

    /**
     * Fetches and prints all the resources of any films/people/planets/etc
     *
     * @param url the API url of all the resources of any category
     */
    public static void results(String url) {
        Printer p = new Printer();
        JsonObject jo = SWapi.fetch(url);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = 1;
        int max = jo.get("count").getAsInt();

        // loop till everything is printed
        while (count <= max) {
            JsonElement next = jo.get("next");
            JsonArray results = jo.get("results").getAsJsonArray();

            // loop through all the results
            for (int i = 0; i < results.size(); i++) {
                App.clearConsole();
                System.out.printf("Displaying results %d/%d%n%n%n", count++, max);

                if (url.indexOf("films") > -1) {
                    p.film(results.get(i).getAsJsonObject(), true);
                }

                else if (url.indexOf("people") > -1) {
                    p.person(results.get(i).getAsJsonObject(), true);
                }

                else if (url.indexOf("planets") > -1) {
                    p.planet(results.get(i).getAsJsonObject(), true);
                }

                else if (url.indexOf("species") > -1) {
                    p.species(results.get(i).getAsJsonObject(), true);
                }

                else if (url.indexOf("starships") > -1) {
                    p.starship(results.get(i).getAsJsonObject(), true);
                }

                else if (url.indexOf("vehicles") > -1) {
                    p.vehicle(results.get(i).getAsJsonObject(), true);
                }

                System.out.printf("%n%n%nPress q to quit or enter to continue ");
                try {
                    String choice = br.readLine();

                    // do nothing, just continue if user doesn't press q
                    if (choice.equals("") || choice == null) {
                        ;
                    }

                    // return if
                    else if ((choice.charAt(0) == 'q') || (choice.charAt(0) == 'Q')) {
                        return;
                    }
                }

                catch (IOException bre) {
                    System.err.printf(bre.getMessage());
                }

            }

            // get the next page
            if (next != null) {
                jo = SWapi.fetch(next.getAsString());
            }
        }
    }
}
