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
        System.out.println("Enter 6 to use a custom test file entering the file name(txt format)");
        String file =in.nextLine();
        String inputTestFromConsole = switch (file) {

            case "1" -> "autotests/testInput.txt";
            case "2" -> "autotests/testInputInvalidMovementCmd.txt";
            case "3" -> "autotests/testInputOutOfRange.txt";
            case "4" -> "autotests/estInputWithObstacle.txt";
            case "5" -> "autotests/testInputWrongFormat.txt";
            case "6" -> "autotests/custom.txt";
            default -> "";

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
