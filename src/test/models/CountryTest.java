package models;

import exceptions.CountryURIInvalidException;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    /**
     * Tests that creating a Country works as intended, and that all instance variables
     * and instantiated correctly.
     */
    @Test
    void createCountryTest(){
        String name = "Vietnam", code = "VN", latitude = "14.058324", longitude = "108.277199";
        Country country = new Country(name, code, latitude, longitude);

        assertNotNull(country);
        assertEquals("VN", country.getCode());
        assertEquals("14.058324", country.getLatitude());
    }

    /**
     * Tests that the creation of the URI returns a valid URI, consisting of "google.co.uk", and the Country position
     *
     * @throws CountryURIInvalidException if the URI could not be created
     */
    @Test
    void createCountryURITest() throws CountryURIInvalidException {
        String name = "Tuvalu", code = "TV", latitude = "-7.109535", longitude = "177.64933";
        Country country = new Country(name, code, latitude, longitude);

        URI uri = country.getURI();
        assertNotNull(uri);
        assertEquals("google.co.uk", uri.getAuthority());
        assertEquals("/maps/@-7.109535,177.64933,7z", uri.getPath());
    }
}