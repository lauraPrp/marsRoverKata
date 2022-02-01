package App;

import marsrover.rover.Controller;
import marsrover.rover.Coordinates;
import marsrover.rover.Plateau;
import marsrover.rover.Rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

public static void main(String[] args) {
    boolean isThereAnErrorInInputFile = false;
    Controller roverController = Controller.getControllerInstance();
    Scanner in = new Scanner(System.in);
    System.out.println("Enter file name: ");
    String s = in.nextLine(); //C:\\testing.txt
    try {
        roverController.initAll(s);
    }catch (NumberFormatException nfe) {
        isThereAnErrorInInputFile = true;
    }
    catch (FileNotFoundException exc) {
        isThereAnErrorInInputFile = true;
        // exc.printStackTrace();
    }
    Plateau plateauGrid = roverController.getPlateau();
    ArrayList<Rover> rovers = roverController.getAllRovers();
  //  rovers.size() == 0 ? isThereAnErrorInInputFile = true: isThereAnErrorInInputFile=isThereAnErrorInInputFile;
    if (!isThereAnErrorInInputFile) {
        for (Rover singleRover : rovers) {
            singleRover.executeCommandList(singleRover);
            System.out.println("  " + singleRover.getMessage());
        }
    } else {
        System.out.println("An error occurred, input file not valid");
    }
}

}
