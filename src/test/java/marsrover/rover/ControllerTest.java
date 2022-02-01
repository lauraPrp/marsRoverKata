package marsrover.rover;

import marsrover.rover.Controller;
import marsrover.rover.Coordinates;
import marsrover.rover.Plateau;
import marsrover.rover.Rover;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    Controller mainAppController;
    Plateau plateauGrid;
    ArrayList<Rover> allRovers;

    @BeforeEach
    public void setUp() throws FileNotFoundException {

        mainAppController = Controller.getControllerInstance();
        mainAppController.initAll("testInput.txt");
        plateauGrid = mainAppController.getPlateau();
        allRovers = mainAppController.getAllRovers();
    }

    @Test
    public void createPlateauFromInput() throws FileNotFoundException {
        plateauGrid = mainAppController.getPlateau();
        assertEquals(5, plateauGrid.getMaxX(), "plateau x coord");
        assertEquals(5, plateauGrid.getMaxY(), "plateau y coord");
    }

    @Test
    public void createListOfRoversFromInput() throws FileNotFoundException {

       // mainAppController.initAll("testInput.txt");
       // plateauGrid = mainAppController.getPlateau();
        ArrayList<Rover> roverList = mainAppController.getAllRovers();



        Coordinates rover1Coordinates = new Coordinates(1, 2);
        Coordinates rover2Coordinates = new Coordinates(3, 3);
        Rover rover1 = new Rover(rover1Coordinates, 'N', plateauGrid);
        Rover rover2 = new Rover(rover2Coordinates, 'E', plateauGrid);

        assertInstanceOf(rover1.getClass(), roverList.get(0));
        assertInstanceOf(rover2.getClass(), roverList.get(1));
    }

    @Test
    public void moveToCorrectSpots() throws FileNotFoundException {

        ArrayList<Rover> roverList = mainAppController.getAllRovers();

        Coordinates rover1CoordinatesExpected = new Coordinates(1, 2);
        Coordinates rover2CoordinatesExpected = new Coordinates(3, 3);
        Rover rover1 = new Rover(rover1CoordinatesExpected, 'N', plateauGrid);
        Rover rover2 = new Rover(rover2CoordinatesExpected, 'E', plateauGrid);

        assertEquals(rover1.getRoverLocation().getX(), roverList.get(0).getRoverLocation().getX());
        assertEquals(rover1.getRoverLocation().getY(), roverList.get(0).getRoverLocation().getY());
        assertEquals(rover1.getRoverDirection(), roverList.get(0).getRoverDirection());

        assertEquals(rover2.getRoverLocation().getX(), roverList.get(1).getRoverLocation().getX());
        assertEquals(rover2.getRoverLocation().getY(), roverList.get(1).getRoverLocation().getY());
        assertEquals(rover2.getRoverDirection(), roverList.get(1).getRoverDirection());
    }

    @Test
    public void countRovers() {
        Controller mainAppController = Controller.getControllerInstance();
        mainAppController.getRoverCount();

        assertEquals(2, mainAppController.getRoverCount(), "rover count");
    }

    @Test
    public void wrongFileInputCommands() throws FileNotFoundException {
        Controller mainApp = Controller.getControllerInstance();
        assertThrows(FileNotFoundException.class, () -> mainApp.initAll("fileNotPresent.txt"));
    }

    //todo: add exceptions in case the file format is not how foreseen?
}
