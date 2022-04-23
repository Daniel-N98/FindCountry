package controller;

import utils.InputReader;

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

    /**
     * Prints out the Menu for the application
     */
    private void printMenu() {
        String menu = """
                -----------------------------------
                1 - View all countries
                2 - Search for a country
                3 - Quit program
                -----------------------------------
                """;
        System.out.println(menu);
        try {
            int userOption = reader.getNextInt("Enter an option from the menu"); // Request an integer from the user
            switchUserOption(userOption); // Select the correct option based on user input
        } catch (NumberFormatException e) {
            System.out.println("You must enter a digit value.");
        }
    }

    /**
     * Select the correct method call for the user input parameter
     * @param option user input
     */
    private void switchUserOption(int option) {
        switch (option) {
            case 1 -> countryController.printCountries(null); // Prints all countries
            case 2 -> countryController.printCountries(reader.getNextText("> Enter the country name")); // Prints a specific country
            case 3 -> finished = true;
        }
    }
}
