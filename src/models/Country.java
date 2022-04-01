package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Country {

    private String name, code, longitude, latitude;

    public void printCountry(){
        System.out.printf("%4s %16s %16s %13s", code, longitude, longitude, name);
        System.out.println();
    }
}
