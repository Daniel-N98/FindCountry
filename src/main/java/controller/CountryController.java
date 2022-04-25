package controller;


import exceptions.CountryNotFoundException;
import models.Country;

import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.TreeMap;

public class CountryController {

    public final HashMap<String, Country> countries;

    /**
     * Constructor for the CountryController class
     */
    public CountryController(){
        this.countries = new HashMap<>();
        try {
            connect(); // Attempt to connect and pull the csv data
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void connect() throws Exception {
        // Pull the data from the csv file
        String httpsURL = "https://raw.githubusercontent.com/google/dspl/master/samples/google/canonical/countries.csv";
        URL url = new URL(httpsURL);
        HttpURLConnection connection = (HttpsURLConnection) url.openConnection(); // Open the connection

        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String inputLine;

        while ((inputLine = br.readLine()) != null) {
            if (inputLine.contains("longitude")) { // Simple check to skip the first row of the Reader.
                continue;
            }
            addToMap(inputLine);//.replace("\"", ""));
        }
    }

    /**
     * Add a new Country entry to the map.
     * String[0 = Country code, 1 = Latitude, 2 = Longitude, 3 = Country name]
     * @param data next line from the reader
     */
    private void addToMap(String data) {
        // Split the String so we can get the individual elements
        String[] split = data.split(",");
        countries.put(split[3], new Country(split[3].replace("\"", ""), split[0], split[1], split[2]));
    }

    /**
     * Prints all stored countries
     */
    public void printCountries(String name) {
        printHeader();
        if (name == null) { // All countries should be printed when no name is specified

            // Create a TreeMap from the HashMap to sort it alphabetically
            TreeMap<String, Country> sortedCountries = new TreeMap<>(countries);
            sortedCountries.values().forEach(Country::printCountry); // Prints out each country
        } else {
            searchCountry(name); // Searches for a specific country and opens it via Google Maps if found
        }
        System.out.println("-".repeat(85)); // Print an 85 character long line
    }

    /**
     * Searches for and opens a specific Country in Google Maps in a new browser tab
     * @param name Country name | Country to search for
     */
    private void searchCountry(String name){
        try {
            Country country = getCountryByNameOrCode(name); // Searches the HashMap for a Country with this name or code
            country.printCountry(); // Prints the country found
            System.out.println("\nOpening '" + country.getName() + "," + country.getCode() + "' via Google maps\n");

            /* Opens a new browser tab at the specific web page (Google Maps).
             * When searching on Google Maps, the URL link is [https://google.co.uk/maps/@Latitude,Longitude,(number)z]
             * So we can easily open Google Maps at a specific location as long as we have the Latitude and Longitude
             * Below, we add the Latitude, Longitude, and "7z" which represents the zoom amount.
             */
            Desktop.getDesktop().browse(new URI(("https://google.co.uk/maps/@" + country.getLatitude() + "," + country.getLongitude() + ",7z")));
        }catch (CountryNotFoundException | URISyntaxException | IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Searches for a country in the Hashmap by the Country name or Country Code
     *
     * @param countryNameOrCode name or code
     * @return Country found with the specific name, or code
     * @throws CountryNotFoundException if no country exists with that name or code
     */
    private Country getCountryByNameOrCode(String countryNameOrCode) throws CountryNotFoundException {
        for (Country country : countries.values()){
            // Checks if the Country name, or code is equal to the parameter (Not case-sensitive)
            if (country.getName().equalsIgnoreCase(countryNameOrCode) || country.getCode().equalsIgnoreCase(countryNameOrCode)){
                return country;
            }
        }
        // Country could not be found
        throw new CountryNotFoundException("Country '" + countryNameOrCode + "' cannot be found");
    }

    /**
     * Prints out a nicely formatted header for displaying results
     */
    private void printHeader() {
        System.out.println("-".repeat(85));
        System.out.printf("%5s %15s %15s %45s", "CODE", "LATITUDE", "LONGITUDE", "COUNTRY NAME");
        System.out.println();
        System.out.println("-".repeat(85));
    }

}
