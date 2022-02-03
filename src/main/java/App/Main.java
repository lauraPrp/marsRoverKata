package App;

import marsrover.rover.Controller;
import marsrover.rover.OutOfPlateauBoundaryException;
import marsrover.rover.Rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static ArrayList<Rover> rovers;
    private static boolean fileContainsInvalidCommand =false;
    private static boolean isThereAnErrorInInputFile =false ;

    public static boolean fileContainsInvalidCommands(String file) throws FileNotFoundException {
        ArrayList<String> allDataInput = new ArrayList<>();
        Scanner scanner = new Scanner(new File(file));
        while (scanner.hasNext()) {
            allDataInput.add(scanner.next().toUpperCase());
        }

        Pattern pattern = Pattern.compile("[^RLMNSEW[0-9]]", Pattern.CASE_INSENSITIVE);
        Matcher matcher ;
        for(String line :allDataInput) {
            fileContainsInvalidCommand =  pattern.matcher(line).find();
            if(fileContainsInvalidCommand)
            break;
        }
        return fileContainsInvalidCommand;
    }

    public static void main(String[] args) throws OutOfPlateauBoundaryException {


        Controller roverController = Controller.getControllerInstance();

        Scanner in = new Scanner(System.in);

        System.out.println("Enter 1 for the plain optimistic test case from the briefing ");
        System.out.println("Enter 2 to test invalid movement command given to the rover ");
        System.out.println("Enter 3 to test coordinates over the Plateau ");
        System.out.println("Enter 4 to test the case which have obstacles ");
        System.out.println("Enter 5 to test the case which have wrong coordinates for the rover at start ");
        System.out.println("Enter 6 to use a custom test file entering the file name(txt format)");
        String file = in.nextLine();
        String inputTestFromConsole = switch (file) {

            case "1" -> "autotests/testInput.txt";
            case "2" -> "autotests/testInputInvalidMovementCmd.txt";
            case "3" -> "autotests/testInputOutOfRange.txt";
            case "4" -> "autotests/estInputWithObstacle.txt";
            case "5" -> "autotests/testInputWrongFormat.txt";
            case "6" -> "autotests/custom.txt";
            default -> "";

        };


        execute(roverController, inputTestFromConsole);


    }

    private static void execute(Controller roverController, String inputTestFromConsole) throws OutOfPlateauBoundaryException {
        try {
            if (!fileContainsInvalidCommands(inputTestFromConsole)) {
                roverController.initAll(inputTestFromConsole);

                rovers = roverController.getAllRovers();
            }
            else {
                System.out.println("File contains invalid commands, check format and try again");
            }
        } catch (FileNotFoundException exc) {
            System.out.println("Check input file");
            isThereAnErrorInInputFile = true;
        } catch (NumberFormatException exc) {
            System.out.println("Check rover coordinates");
            isThereAnErrorInInputFile = true;
        }
        catch (UnsupportedOperationException exc) {
            isThereAnErrorInInputFile = true;
        }

        if (isThereAnErrorInInputFile) {
            System.out.println("Something went VERY WRONG. Operation Aborted. NASA wont hire me :( ");
        } else if(!fileContainsInvalidCommand) {
            roverController.startOperations(rovers);
        }
    }
}
