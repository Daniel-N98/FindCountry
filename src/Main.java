import controller.Controller;
import controller.CountryController;

public class Main {


    public static void main(String[] args) throws Exception {
        new Controller(new CountryController());
    }
}
