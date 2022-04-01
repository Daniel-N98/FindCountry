import controller.Controller;
import controller.CountryCountroller;

public class Main {


    public static void main(String[] args) throws Exception {
        new Controller(new CountryCountroller());
    }
}
