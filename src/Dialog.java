import java.util.Scanner;

public class Dialog {
    static void askUser(){
        System.out.println("Input a value and press 'enter' ");
        Scanner sc = new Scanner(System.in);
      //  System.out.println("Input a value");
        System.out.println("You inputted: "+sc.nextLine());

    }
}
