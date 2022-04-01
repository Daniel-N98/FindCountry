package utils;

import controller.Controller;

import java.util.Scanner;

public class InputReader {
    private final Scanner reader;

    public InputReader(){
        this.reader = new Scanner(System.in);
    }

    public int getNextInt(String printMessage) throws NumberFormatException{
        Controller.print(printMessage);
        try {
            return Integer.parseInt(reader.next());
        }catch (NumberFormatException e){
            throw new NumberFormatException();
        }
    }

    public String getNextText(String printMessage){
        Controller.print(printMessage);
        return reader.next();
    }
}
