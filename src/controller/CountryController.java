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

    public CountryController() throws Exception {
        this.countries = new HashMap<>();

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


    private void addToMap(String data) {
        String[] split = data.split(",");
        countries.put(split[3], new Country(split[3].replace("\"", ""), split[0], split[1], split[2]));
    }

    /**
     * Prints all stored countries
     */
    public void printCountries(String name) {
        printHeader();
        if (name == null) {
            TreeMap<String, Country> sortedCountries = new TreeMap<>(countries);

            sortedCountries.values().forEach(Country::printCountry);
        } else {
            searchCountry(name);
        }
        System.out.println("-".repeat(85));
    }

    private void searchCountry(String name){
        try {
            Country country = getCountryByNameOrCode(name);
            country.printCountry();
            Desktop.getDesktop().browse(new URI(("https://google.co.uk/maps/@" + country.getLatitude() + "," + country.getLongitude() + ",7z")));
        }catch (CountryNotFoundException | URISyntaxException | IOException e){
            e.printStackTrace();
        }
    }

    private Country getCountryByNameOrCode(String countryNameOrCode) throws CountryNotFoundException {
        for (Country country : countries.values()){
            if (country.getName().equalsIgnoreCase(countryNameOrCode) || country.getCode().equalsIgnoreCase(countryNameOrCode)){
                return country;
            }
        }
        throw new CountryNotFoundException("Country '" + countryNameOrCode + "' cannot be found");
    }

    private void printHeader() {
        System.out.println("-".repeat(85));
        System.out.printf("%5s %15s %15s %45s", "CODE", "LATITUDE", "LONGITUDE", "COUNTRY NAME");
        System.out.println();
        System.out.println("-".repeat(85));
    }

}
