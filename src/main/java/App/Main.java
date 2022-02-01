package App;

import marsrover.rover.Controller;
import marsrover.rover.Rover;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

public static void main(String[] args) {
    boolean isThereAnErrorInInputFile = false;
    Controller roverController = Controller.getControllerInstance();
    Scanner in = new Scanner(System.in);

    System.out.println("Enter 1 for the plain optimistic test case from the briefing ");
    System.out.println("Enter 2 to test invalid movement command given to the rover ");
    System.out.println("Enter 3 to test coordinates over the Plateau ");
    System.out.println("Enter 4 to test the case which have obstacles ");
    System.out.println("Enter 5 to test the case which have wrong coordinates for the rover at start ");
    System.out.println("Use a custom test file entering the file name(txt format)");

    String s = in.nextLine();
    if (s.equals("1"))
        s = "testInput.txt";
    if (s.equals("2"))
    s="testInputInvalidMovementCmd.txt";
    if (s.equals("3"))
    s="testInputOutOfRange.txt";
    if (s.equals("4"))
    s="testInputWithObstacle.txt";
    if (s.equals("5"))
    s="testInputWrongFormat.txt";
    try {
        roverController.initAll(s);
    }catch (FileNotFoundException exc) {
        System.out.println("Check input file");
        isThereAnErrorInInputFile = true;
        // exc.printStackTrace();
    }
    catch (NumberFormatException exc) {
        System.out.println("Something went VERY WRONG. Operation Aborted. NASA wont hire me :( ");
        isThereAnErrorInInputFile = true;
        // exc.printStackTrace();
    }
    ArrayList<Rover> rovers = roverController.getAllRovers();
    if (!isThereAnErrorInInputFile) {
       roverController.startOperations(rovers);
    } else {
        System.out.println("An error occurred, input file not valid");
    }
}

}
