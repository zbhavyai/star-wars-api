package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.fusesource.jansi.AnsiConsole;

/**
 * The Star-Wars-API app
 *
 * @author Bhavyai Gupta
 * @version 1.0.0
 * @since July 7, 2021
 */
public class App {
    /**
     * Initializes the App
     */
    public App() {
        AnsiConsole.systemInstall();
    }

    /**
     * Clears the console
     */
    public static void clearConsole() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.indexOf("win") > -1) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        else {
            System.out.print("\033\143");
        }
    }

    /**
     * Star-Wars-API menu
     */
    public void appMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String baseurl = "https://swapi.dev/api/";
        String category = "";

        // priming input ch
        int choice = 9;

        while (choice != 0) {
            App.clearConsole();

            System.out.printf("%n%nWelcome to Star Wars API - built on \'https://swapi.dev/api/\'");
            System.out.printf("%n------------------------------------------------------------");

            System.out.printf("%n%nChoose a category from below -%n");
            System.out.printf("%n[1] Print all films");
            System.out.printf("%n[2] Print all people");
            System.out.printf("%n[3] Print all planets");
            System.out.printf("%n[4] Print all species");
            System.out.printf("%n[5] Print all starships");
            System.out.printf("%n[6] Print all vehicles");
            System.out.printf("%n[0] Exit");

            try {
                System.out.printf("%n%n%n[QUES] Please enter your choice: ");
                choice = Integer.parseInt(br.readLine());

                switch (choice) {
                    // films
                    case 1: {
                        App.clearConsole();
                        category = "films/";

                        SWapi.results(baseurl + category);

                        System.out.printf("%n%n%nPress enter to return to the menu ");
                        br.readLine();
                    }
                        break;

                    // people
                    case 2: {
                        App.clearConsole();
                        category = "people/";

                        SWapi.results(baseurl + category);

                        System.out.printf("%n%n%nPress enter to return to the menu ");
                        br.readLine();
                    }
                        break;

                    // planets
                    case 3: {
                        App.clearConsole();
                        category = "planets/";

                        SWapi.results(baseurl + category);

                        System.out.printf("%n%n%nPress enter to return to the menu ");
                        br.readLine();
                    }
                        break;

                    // species
                    case 4: {
                        App.clearConsole();
                        category = "species/";

                        SWapi.results(baseurl + category);

                        System.out.printf("%n%n%nPress enter to return to the menu ");
                        br.readLine();
                    }
                        break;

                    // starships
                    case 5: {
                        App.clearConsole();
                        category = "starships/";

                        SWapi.results(baseurl + category);

                        System.out.printf("%n%n%nPress enter to return to the menu ");
                        br.readLine();
                    }
                        break;

                    // vehicles
                    case 6: {
                        App.clearConsole();
                        category = "vehicles/";

                        SWapi.results(baseurl + category);

                        System.out.printf("%n%n%nPress enter to return to the menu ");
                        br.readLine();
                    }
                        break;

                    // exit by changing choice
                    case 0: {
                        choice = 0;
                        System.out.printf("%n%s%n", "Bye!");
                    }
                        break;

                    default: {
                        throw new NumberFormatException();
                    }
                }
            }

            catch (NumberFormatException e) {
                System.err.printf("%n%n[FAIL] Please enter a valid choice");
                System.err.printf("%n%n%nPress enter to return to the menu ");

                try {
                    br.readLine();
                } catch (IOException bre) {
                    System.err.printf(bre.getMessage());
                }

            }

            catch (java.util.NoSuchElementException e) {
                System.err.printf("%n%n[FAIL] Input stream has been closed. Bye.");
                Runtime.getRuntime().exit(0);
            }

            catch (IOException e) {
                System.err.printf(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.appMenu();
    }
}
