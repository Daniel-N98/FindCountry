package controller;

import utils.InputReader;

import java.io.IOException;
import java.net.URISyntaxException;

public class Controller {

    private final InputReader reader;
    private boolean finished;
    private final CountryController countryController;

    public Controller(CountryController countryController) {
        // Starts the program by first printing a menu to the user via console
        this.reader = new InputReader();
        this.finished = false;
        this.countryController = countryController;

        while (!finished) {
            printMenu();
        }
    }

    private void printMenu() {
        print("-----------------------------------");
        print("");
        print("  1 - View all countries");
        print("  2 - Search for a country");
        print("  3 - Quit program");
        print("");
        print("-----------------------------------");

        try {
            int userOption = reader.getNextInt("Enter an option from the menu");
            switchUserOption(userOption);
        } catch (NumberFormatException e) {
            System.out.println("You must enter a digit value.");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void print(String toPrint) {
        System.out.println(toPrint);
    }

    private void switchUserOption(int option) throws IOException, URISyntaxException {
        switch (option) {
            case 1 -> countryController.printCountries(null);
            case 2 -> countryController.printCountries(reader.getNextText("> Enter the country name"));
            case 3 -> finished = true;
        }
    }
}
