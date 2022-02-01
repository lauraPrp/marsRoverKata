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
        String inputTestFromConsole = in.nextLine();
        inputTestFromConsole = switch (inputTestFromConsole) {

            case "1" -> "testInput.txt";
            case "2" -> "testInputInvalidMovementCmd.txt";
            case "3" -> "testInputOutOfRange.txt";
            case "4" -> "testInputWithObstacle.txt";
            case "5" -> "testInputWrongFormat.txt";
            default -> "testInput.txt";

        };

        try {
            roverController.initAll(inputTestFromConsole);
        } catch (FileNotFoundException exc) {
            System.out.println("Check input file");
            isThereAnErrorInInputFile = true;
        } catch (NumberFormatException exc) {
            System.out.println("Check rover coordinates");
            isThereAnErrorInInputFile = true;
        }
        ArrayList<Rover> rovers = roverController.getAllRovers();
        if (!isThereAnErrorInInputFile) {
            roverController.startOperations(rovers);
        } else {
            System.out.println("Something went VERY WRONG. Operation Aborted. NASA wont hire me :( ");
        }
    }

}
