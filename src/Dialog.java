import java.util.Scanner;

public class Dialog {
    static void askUser() {
        System.out.println("Input a value and press 'enter' ");
        Scanner sc = new Scanner(System.in);
        try {
            int bill = Integer.parseInt(sc.nextLine());
            System.out.println("You inputted: " + bill);
        } catch (Exception e) {
            System.err.println(e + ": You might have inputted wrong value.");
        }


    }
}
