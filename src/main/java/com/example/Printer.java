package com.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

/**
 * Handles printing of JSON data received from swapi.dev/api via SWapi class
 *
 * @author Bhavyai Gupta
 * @version 1.0.0
 * @since July 9, 2021
 */
public class Printer {
    /**
     * Prints film data according to <code>verbosity</code> level
     *
     * @param jsonObject jsonObject of a particular film
     * @param verbosity  <code>true</code> for all details to be printed,
     *                   <code>false</code> for only basic details
     */
    public void film(JsonObject jsonObject, boolean verbosity) {
        int padding = 12;

        if (verbosity == false) {
            System.out.printf("%s", jsonObject.get("title").getAsString());
            return;
        }

        else {
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Film Title"),
                    jsonObject.get("title").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Episode ID"),
                    jsonObject.get("episode_id").getAsString());
            // Opening Crawl commented because its too long
            // System.out.printf("%s: %s%n", String.format("%-" + padding + "s", "Opening
            // Crawl"),
            // jsonObject.get("opening_crawl").getAsString().replaceAll("\\\\r\\\\n",
            // "%n"));
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Release Date"),
                    jsonObject.get("release_date").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Directed by"),
                    jsonObject.get("director").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Produced by"),
                    jsonObject.get("producer").getAsString());

            // print all character names
            // --------------------------------------------------------------------------------
            JsonArray characters = jsonObject.get("characters").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Cast"));

            for (int i = 0; i < characters.size(); i++) {
                this.person(SWapi.fetch(characters.get(i).getAsString()), false);

                if (i != characters.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------

            // print all planet names
            // --------------------------------------------------------------------------------
            JsonArray planets = jsonObject.get("planets").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Planets"));

            for (int i = 0; i < planets.size(); i++) {
                this.planet(SWapi.fetch(planets.get(i).getAsString()), false);

                if (i != planets.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------

            // print all starship names
            // --------------------------------------------------------------------------------
            JsonArray starships = jsonObject.get("starships").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Starships"));

            for (int i = 0; i < starships.size(); i++) {
                this.starship(SWapi.fetch(starships.get(i).getAsString()), false);

                if (i != starships.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------

            // print all vehicle names
            // --------------------------------------------------------------------------------
            JsonArray vehicles = jsonObject.get("vehicles").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Vehicles"));

            for (int i = 0; i < vehicles.size(); i++) {
                this.vehicle(SWapi.fetch(vehicles.get(i).getAsString()), false);

                if (i != vehicles.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------

            // print all species names
            // --------------------------------------------------------------------------------
            JsonArray species = jsonObject.get("species").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Species"));

            for (int i = 0; i < species.size(); i++) {
                this.species(SWapi.fetch(species.get(i).getAsString()), false);

                if (i != species.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------
        }
    }

    /**
     * Prints person data according to <code>verbosity</code> level
     *
     * @param jsonObject jsonObject of a particular person
     * @param verbosity  <code>true</code> for all details to be printed,
     *                   <code>false</code> for only basic details
     */
    public void person(JsonObject jsonObject, boolean verbosity) {
        int padding = 17;

        if (verbosity == false) {
            System.out.printf("%s", jsonObject.get("name").getAsString());
            return;
        }

        else {
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Name"),
                    jsonObject.get("name").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Gender"),
                    jsonObject.get("gender").getAsString());
            System.out.printf("%s: %s cm%n%n", String.format("%-" + padding + "s", "Height"),
                    jsonObject.get("height").getAsString());
            System.out.printf("%s: %s Kg%n%n", String.format("%-" + padding + "s", "Weight"),
                    jsonObject.get("mass").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Birth Year"),
                    jsonObject.get("birth_year").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Hair Color"),
                    jsonObject.get("hair_color").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Skin Color"),
                    jsonObject.get("skin_color").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Eye Color"),
                    jsonObject.get("eye_color").getAsString());

            System.out.printf("%s: ", String.format("%-" + padding + "s", "Homeworld"));
            this.planet(SWapi.fetch(jsonObject.get("homeworld").getAsString()), false);
            System.out.printf("%n%n");

            // print all film names
            // --------------------------------------------------------------------------------
            JsonArray films = jsonObject.get("films").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Films"));

            for (int i = 0; i < films.size(); i++) {
                this.film(SWapi.fetch(films.get(i).getAsString()), false);

                if (i != films.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------

            // print all species names
            // --------------------------------------------------------------------------------
            JsonArray species = jsonObject.get("species").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Species"));

            for (int i = 0; i < species.size(); i++) {
                this.species(SWapi.fetch(species.get(i).getAsString()), false);

                if (i != species.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------

            // print all vehicle names
            // --------------------------------------------------------------------------------
            JsonArray vehicles = jsonObject.get("vehicles").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Vehicles Piloted"));

            for (int i = 0; i < vehicles.size(); i++) {
                this.vehicle(SWapi.fetch(vehicles.get(i).getAsString()), false);

                if (i != vehicles.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------

            // print all starship names
            // --------------------------------------------------------------------------------
            JsonArray starships = jsonObject.get("starships").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Starships Piloted"));

            for (int i = 0; i < starships.size(); i++) {
                this.starship(SWapi.fetch(starships.get(i).getAsString()), false);

                if (i != starships.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------
        }
    }

    /**
     * Prints planet data according to <code>verbosity</code> level
     *
     * @param jsonObject jsonObject of a particular planet
     * @param verbosity  <code>true</code> for all details to be printed,
     *                   <code>false</code> for only basic details
     */
    public void planet(JsonObject jsonObject, boolean verbosity) {
        int padding = 14;

        if (verbosity == false) {
            System.out.printf("%s", jsonObject.get("name").getAsString());
            return;
        }

        else {
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Name"),
                    jsonObject.get("name").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Diameter"),
                    jsonObject.get("diameter").getAsString());
            System.out.printf("%s: %s hrs%n%n", String.format("%-" + padding + "s", "Rotation Period"),
                    jsonObject.get("rotation_period").getAsString());
            System.out.printf("%s: %s days%n%n", String.format("%-" + padding + "s", "Orbital Period"),
                    jsonObject.get("orbital_period").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Climate"),
                    jsonObject.get("climate").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Gravity"),
                    jsonObject.get("gravity").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Terrain"),
                    jsonObject.get("terrain").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Surface Water"),
                    jsonObject.get("surface_water").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Population"),
                    jsonObject.get("population").getAsString());

            // print all resident names
            // --------------------------------------------------------------------------------
            JsonArray residents = jsonObject.get("residents").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Residents"));

            for (int i = 0; i < residents.size(); i++) {
                this.person(SWapi.fetch(residents.get(i).getAsString()), false);

                if (i != residents.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------

            // print all film names
            // --------------------------------------------------------------------------------
            JsonArray films = jsonObject.get("films").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Shown in Films"));

            for (int i = 0; i < films.size(); i++) {
                this.film(SWapi.fetch(films.get(i).getAsString()), false);

                if (i != films.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------
        }
    }

    /**
     * Prints species data according to <code>verbosity</code> level
     *
     * @param jsonObject jsonObject of a particular species
     * @param verbosity  <code>true</code> for all details to be printed,
     *                   <code>false</code> for only basic details
     */
    public void species(JsonObject jsonObject, boolean verbosity) {
        int padding = 14;

        if (verbosity == false) {
            System.out.printf("%s", jsonObject.get("name").getAsString());
            return;
        }

        else {
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Name"),
                    jsonObject.get("name").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Classification"),
                    jsonObject.get("classification").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Designation"),
                    jsonObject.get("designation").getAsString());

            System.out.printf("%s: ", String.format("%-" + padding + "s", "Homeworld"));
            this.planet(SWapi.fetch(jsonObject.get("homeworld").getAsString()), false);
            System.out.printf("%n%n");

            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Language"),
                    jsonObject.get("language").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Avg Lifespan"),
                    jsonObject.get("average_lifespan").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Avg Height"),
                    jsonObject.get("average_height").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Hair Colors"),
                    jsonObject.get("hair_colors").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Skin Colors"),
                    jsonObject.get("skin_colors").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Eye Colors"),
                    jsonObject.get("eye_colors").getAsString());

            // print all person names
            // --------------------------------------------------------------------------------
            JsonArray people = jsonObject.get("people").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "People"));

            for (int i = 0; i < people.size(); i++) {
                this.person(SWapi.fetch(people.get(i).getAsString()), false);

                if (i != people.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------

            // print all film names
            // --------------------------------------------------------------------------------
            JsonArray films = jsonObject.get("films").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Shown in Films"));

            for (int i = 0; i < films.size(); i++) {
                this.film(SWapi.fetch(films.get(i).getAsString()), false);

                if (i != films.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------
        }
    }

    /**
     * Prints starship data according to <code>verbosity</code> level
     *
     * @param jsonObject jsonObject of a particular starship
     * @param verbosity  <code>true</code> for all details to be printed,
     *                   <code>false</code> for only basic details
     */
    public void starship(JsonObject jsonObject, boolean verbosity) {
        int padding = 17;

        if (verbosity == false) {
            System.out.printf("%s", jsonObject.get("name").getAsString());
            return;
        }

        else {
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Name"),
                    jsonObject.get("name").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Model"),
                    jsonObject.get("model").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Class"),
                    jsonObject.get("starship_class").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Hyperdrive Rating"),
                    jsonObject.get("hyperdrive_rating").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "MGLT"),
                    jsonObject.get("MGLT").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Manufacturer"),
                    jsonObject.get("manufacturer").getAsString());
            System.out.printf("%s: %s credits%n%n", String.format("%-" + padding + "s", "Cost"),
                    jsonObject.get("cost_in_credits").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Crew"),
                    jsonObject.get("crew").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Passengers"),
                    jsonObject.get("passengers").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Length"),
                    jsonObject.get("length").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Speed"),
                    jsonObject.get("max_atmosphering_speed").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Cargo Capacity"),
                    jsonObject.get("cargo_capacity").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Consumables"),
                    jsonObject.get("consumables").getAsString());

            // print all pilot names
            // --------------------------------------------------------------------------------
            JsonArray pilots = jsonObject.get("pilots").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Piloted By"));

            for (int i = 0; i < pilots.size(); i++) {
                this.person(SWapi.fetch(pilots.get(i).getAsString()), false);

                if (i != pilots.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------

            // print all film names
            // --------------------------------------------------------------------------------
            JsonArray films = jsonObject.get("films").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Shown in Films"));

            for (int i = 0; i < films.size(); i++) {
                this.film(SWapi.fetch(films.get(i).getAsString()), false);

                if (i != films.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------
        }
    }

    /**
     * Prints vehicle data according to <code>verbosity</code> level
     *
     * @param jsonObject jsonObject of a particular vehicle
     * @param verbosity  <code>true</code> for all details to be printed,
     *                   <code>false</code> for only basic details
     */
    public void vehicle(JsonObject jsonObject, boolean verbosity) {
        int padding = 14;

        if (verbosity == false) {
            System.out.printf("%s", jsonObject.get("name").getAsString());
            return;
        }

        else {
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Name"),
                    jsonObject.get("name").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Model"),
                    jsonObject.get("model").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Class"),
                    jsonObject.get("vehicle_class").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Manufacturer"),
                    jsonObject.get("manufacturer").getAsString());
            System.out.printf("%-13s: %s credits%n%n", String.format("%-" + padding + "s", "Cost"),
                    jsonObject.get("cost_in_credits").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Crew"),
                    jsonObject.get("crew").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Passengers"),
                    jsonObject.get("passengers").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Length"),
                    jsonObject.get("length").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Speed"),
                    jsonObject.get("max_atmosphering_speed").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Cargo Capacity"),
                    jsonObject.get("cargo_capacity").getAsString());
            System.out.printf("%s: %s%n%n", String.format("%-" + padding + "s", "Consumables"),
                    jsonObject.get("consumables").getAsString());

            // print all pilot names
            // --------------------------------------------------------------------------------
            JsonArray pilots = jsonObject.get("pilots").getAsJsonArray();
            System.out.printf("%s: ", String.format("%-" + padding + "s", "Piloted By"));

            for (int i = 0; i < pilots.size(); i++) {
                this.person(SWapi.fetch(pilots.get(i).getAsString()), false);

                if (i != pilots.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------

            // print all film names
            // --------------------------------------------------------------------------------
            JsonArray films = jsonObject.get("films").getAsJsonArray();
            System.out.printf("%-13s: ", String.format("%-" + padding + "s", "Shown in Films"));

            for (int i = 0; i < films.size(); i++) {
                this.film(SWapi.fetch(films.get(i).getAsString()), false);

                if (i != films.size() - 1) {
                    System.out.printf("%s", ", ");
                }
            }

            System.out.printf("%n%n");
            // --------------------------------------------------------------------------------
        }
    }
}
