package models;

import exceptions.CountryURIInvalidException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.net.URISyntaxException;

@AllArgsConstructor
@Getter
@Setter
public class Country {

    private String name, code, latitude, longitude;

    /**
     * Prints out a nicely formatted Country
     */
    public void print() {
        System.out.printf("%4s %16s %16s %45s", code, latitude, longitude, name);
        System.out.println();
    }


    /**
     * Attempts to create a new URI with the Country details
     *
     * @return URI created from Country details
     * @throws CountryURIInvalidException if the URI could not be created
     */
    public URI getURI() throws CountryURIInvalidException {
        try {
            return new URI(("https://google.co.uk/maps/@" + this.getLatitude() + "," + this.getLongitude() + ",7z"));
        } catch (URISyntaxException e) {
            throw new CountryURIInvalidException("URI invalid for '" + this.name + ", " + this.code + "'");
        }
    }
}
