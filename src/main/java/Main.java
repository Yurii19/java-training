import hibernate.services.AtmService;

public class Main {

    public static void main(String args[]) {
        AtmService atmService = new AtmService(1);
        atmService.ServeClient();
    }
}