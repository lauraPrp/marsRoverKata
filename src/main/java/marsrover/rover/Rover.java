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

        ArrayList<String> fileContents = new ArrayList<>();

        while (scanner.hasNext()) {
            fileContents.add(scanner.nextLine());
        }
        scanner.close();
        return fileContents;
    }

}


