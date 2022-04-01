package controller;

import models.Country;

import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CountryCountroller {

    public final HashMap<String, Country> countries;

    public CountryCountroller() throws Exception {
        this.countries = new HashMap<>();

        String httpsURL = "https://raw.githubusercontent.com/google/dspl/master/samples/google/canonical/countries.csv";
        URL url = new URL(httpsURL);
        HttpURLConnection connection = (HttpsURLConnection) url.openConnection();

        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String inputLine;

        while ((inputLine = br.readLine()) != null) {
            if (inputLine.contains("longitude")) {
                continue;
            }
            addToMap(inputLine.replace("\"", ""));
        }
    }


    private void addToMap(String data) {
        String[] split = data.replace(',', ' ').split(" ");
        countries.put(split[3], new Country(split[3], split[0], split[1], split[2]));
    }

    /**
     * Prints all stored countries
     */
    public void printCountries(String name) throws IOException, URISyntaxException {
        printHeader();
        if (name == null) {
            for (String countryName : countries.keySet()) {
                Country country = countries.get(countryName);
                country.printCountry();
            }
        } else {
            Country country = countries.get(name);
            if (country != null) {
                country.printCountry();
                Desktop.getDesktop().browse(new URI("https://google.co.uk/maps/@" + country.getLongitude() + "," + country.getLatitude() + ",7z"));
            }else{
                System.out.println("This country does not exist in our system yet!");
            }

        }

        System.out.println("-----------------------------------------------------");
    }

    private void printHeader() {
        System.out.println("-----------------------------------------------------");
        System.out.printf("%5s %15s %15s %15s", "CODE", "LATITUDE", "LONGITUDE", "COUNTRY NAME");
        System.out.println();
        System.out.println("-----------------------------------------------------");
    }

}
