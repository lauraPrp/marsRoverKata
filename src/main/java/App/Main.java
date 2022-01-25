package App;

import marsrover.rover.Coordinates;
import marsrover.rover.Plateau;
import marsrover.rover.Rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    ArrayList<String> input = new ArrayList();
    private static Plateau plateau;
    private int roverCount;


    /*public Plateau getPlateau() {
        return plateau;
    }*/
    public void getInputFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));

        ArrayList<String> allDataInput = new ArrayList<>();

        while (scanner.hasNext()) {
            allDataInput.add(scanner.next());
        }
        scanner.close();
        this.input=allDataInput;
    }

    public Plateau initPlateau() {
//extract plateau dimension from input
        String[] plat = input.get(0).split("");
        return new Plateau(Integer.parseInt(input.get(0)), Integer.parseInt(input.get(1)));
    }

    public ArrayList<Rover> initRovers() {
//extract Rovers position and command
        ArrayList allRovers = new ArrayList();
        System.out.println("------------------" + input);
//[5, 5, 1, 2, N, LMLMLMLMM, 3, 3, E, MMRMMRMRRM]

        // for(int i=2;i<allDataInput.size();i+=4){
        for (int i = 2; i < input.size(); i += 4) {

            //System.out.println(i+ " "+allDataInput.get(i));
            // roverStart = new Coordinates(Integer.parseInt(allDataInput.get(i)), Integer.parseInt(allDataInput.get(i + 1)));
            // System.out.println("------------------------------------------"+roverStart.toString());
            Rover roverToList = new Rover(new Coordinates(Integer.parseInt(input.get(i)), Integer.parseInt(input.get(i + 1)))
                    , input.get(i + 2));
            roverToList.setMovementCommandList(input.get(i + 3));
            allRovers.add(roverToList);
            // movementCommandList  = allDataInput.get(i+1).split(" ")    ;
            // allRovers.add(allDataInput.get(i+4));
        }
        // roverCount = allRovers.size();
        return allRovers;
    }


    public int getRoverCount() {
        return roverCount;
    }
}
