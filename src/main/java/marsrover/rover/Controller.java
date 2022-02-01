package marsrover.rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Controller {
    private static Controller controllerInstance = null;
    private ArrayList<String> input;
    private Plateau plateau;
    private int roverCount;

    private ArrayList<Rover> allRovers;

    private Controller() {
        input = new ArrayList<>();
    }

    public static Controller getControllerInstance() {
        if (controllerInstance == null)
            synchronized (Controller.class) {
                if (controllerInstance == null)
                    controllerInstance = new Controller();
            }
        return controllerInstance;
    }

    public void initAll(String file) throws FileNotFoundException {
        input = getInputFromFile(file);
        initPlateau();
        initRovers();
    }

    private ArrayList<String> getInputFromFile(String filename) throws FileNotFoundException, NoSuchElementException {
        ArrayList<String> allDataInput = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));

        while (scanner.hasNext()) {
            allDataInput.add(scanner.next().toUpperCase());
        }
        scanner.close();
        if (validateInputFileData(allDataInput))
            return allDataInput;
        else
            throw new FileNotFoundException();

    }

    public Plateau getPlateau() {
        return this.plateau;
    }

    public ArrayList<Rover> getAllRovers() {
        return allRovers;
    }

    private boolean validateInputFileData(ArrayList<String> inputToValidate) {
        //6 is the minimum number of data : 2 numbers Plateau,2 numbers and a char for the rover and a String for commands
        return inputToValidate.size() >= 6;


    }

    private void initPlateau() {
        String[] plat = input.get(0).split("");
        plateau = new Plateau(Integer.parseInt(input.get(0)), Integer.parseInt(input.get(1)));
    }

    /*
    from the second row of the input text file details of each single rover are listed in two rows
    so the first rover will be detailed in row 2 and row 3,
    the second rover 2 in row 4 and row 5 and so on for any following rover(if present).
     */
    private ArrayList<Rover> initRovers() {
        Plateau grid = getPlateau();
        allRovers = new ArrayList<>();
            for (int i = 2; i < input.size(); i += 4) {

                Rover roverToList = new Rover(
                        new Coordinates(Integer.parseInt(input.get(i)), Integer.parseInt(input.get(i + 1))),
                        input.get(i + 2).charAt(0),
                        grid);

                roverToList.setMovementCommandList(input.get(i + 3).toCharArray());
                allRovers.add(roverToList);
            }
        roverCount = allRovers.size();

        return allRovers;
    }

    public int getRoverCount() {
        return roverCount;
    }
}
