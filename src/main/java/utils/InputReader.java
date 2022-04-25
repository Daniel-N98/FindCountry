package utils;

import java.util.Scanner;

public class InputReader {
    private final Scanner reader;

    public InputReader(){
        this.reader = new Scanner(System.in);
    }

    /**
     * Prints out a message to the console
     * Returns the next Integer input
     *
     * @param printMessage to be printed to console
     * @return int next integer
     * @throws NumberFormatException if a non-int value is provided
     */
    public int getNextInt(String printMessage) throws NumberFormatException{
        System.out.println(printMessage);
        try {
            // Parses the next String as an Integer
            return Integer.parseInt(reader.next());
        }catch (NumberFormatException e){
            throw new NumberFormatException();
        }
    }

    /**
     * Prints out a message to the console
     * Returns the next String input
     *
     * @param printMessage to be printed to console
     * @return string next input
     */
    public String getNextText(String printMessage){
        System.out.println(printMessage);
        return reader.next();
    }
}
