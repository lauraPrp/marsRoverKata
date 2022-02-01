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

public static void main(String[] args){
    Controller roverController = Controller.getControllerInstance();
    Scanner in = new Scanner(System.in);
    System.out.println("Enter file path");
    String s = in.nextLine(); //C:\\testing.txt
    try {
        roverController.initAll(s);
    }catch(FileNotFoundException exc){
        exc.printStackTrace();
    }
 Plateau  plateauGrid = roverController.getPlateau();
   ArrayList<Rover> rovers = roverController.getAllRovers();
   for(Rover singleRover:rovers){
       singleRover.executeCommandList(singleRover);
       System.out.println("  " + singleRover.getMessage());
   }
}

}
