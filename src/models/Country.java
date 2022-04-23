package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Country {

    private String name, code, latitude, longitude;

    /**
     * Prints out a nicely formatted Country
     */
    public void printCountry(){
        System.out.printf("%4s %16s %16s %45s", code, latitude, longitude, name);
        System.out.println();
    }
}
