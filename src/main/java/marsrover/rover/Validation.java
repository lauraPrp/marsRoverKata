package marsrover.rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class Validation {
    public static boolean fileContainsInvalidDigit(String file) throws FileNotFoundException {
        boolean isThereInvalidDigit=false;
        ArrayList<String> allDataInput = new ArrayList<>();
        Scanner scanner = new Scanner(new File(file));
        while (scanner.hasNext()) {
            allDataInput.add(scanner.next().toUpperCase());
        }

        Pattern pattern = Pattern.compile("[^RLMNSEW[0-9]]", Pattern.CASE_INSENSITIVE);
        Matcher matcher ;
        for(String line :allDataInput) {
            isThereInvalidDigit =  pattern.matcher(line).find();
            if(isThereInvalidDigit)
                break;
        }
        return isThereInvalidDigit;
    }

}
