package hibernate.utils;

import java.util.Arrays;

public class Parser {

    /**
     * @param userInput string inputted by user in the terminal window
     * @return array of strings from source string
     */
    public static String[] getInput(String userInput){
        String [] res = userInput.trim().split(" ");
        return res;
    }
}
