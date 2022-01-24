package marsrover.rover;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Rover {
    private static Coordinates plateau;
   // private static String fileInput = "testInput.txt";

        public Coordinates getPlateau() {
        return plateau;
    }

    public ArrayList getInputFromFile(String filename) throws  FileNotFoundException{
        Scanner scanner = new Scanner(new File(filename));

        ArrayList<String> allDataInput = new ArrayList<>();

        while (scanner.hasNext()) {
            allDataInput.add(scanner.nextLine());
        }
        scanner.close();
//extract plateau dimension from input
        String[] plat = allDataInput.get(0).split(" ");
        plateau = new Coordinates(Integer.parseInt(plat[0]), Integer.parseInt(plat[1]));
//extract Rovers position and command

        return allDataInput;
    }

}


