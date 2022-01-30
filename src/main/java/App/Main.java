package App;

import marsrover.rover.Coordinates;
import marsrover.rover.Plateau;
import marsrover.rover.Rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {


    private ArrayList<String> input = new ArrayList<>();
    private int roverCount;

    public void getInputFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));

        ArrayList<String> allDataInput = new ArrayList<>();

        while (scanner.hasNext()) {
            allDataInput.add(scanner.next());
        }
        scanner.close();
        setInput(allDataInput);
    }

    public Plateau initPlateau() {
        String[] plat = input.get(0).split("");
        return new Plateau(Integer.parseInt(input.get(0)), Integer.parseInt(input.get(1)));
    }

    /*
    from the second row of the input text file details of each single rover are listed in two rows
    so the first rover will be detailed in row 2 and row 3,
    the second rover 2 in row 4 and row 5 and so on for any following rover(if present).
     */
    public ArrayList<Rover> initRovers() {
        Plateau grid = initPlateau();
        ArrayList<Rover> allRovers = new ArrayList<>();

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

    public ArrayList<String> getInput() {
        return input;
    }

    private void setInput(ArrayList<String> input) {
        this.input = input;
    }

    public int getRoverCount() {
        return roverCount;
    }

    public boolean validateInputFileData() {
        // if (input.size() > 6) { //6 is the minimum number of data : 2 numbers Plateau,2 numbers and a char for the rover and a String for commands
        //     }
        return true;
    }
}
