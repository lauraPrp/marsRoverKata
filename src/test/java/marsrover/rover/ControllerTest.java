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
    public void createPlateauFromInput() {
        plateauGrid = mainAppController.getPlateau();
        assertEquals(5, plateauGrid.getMaxX(), "plateau x coord");
        assertEquals(5, plateauGrid.getMaxY(), "plateau y coord");
    }

    @Test
    public void createListOfRoversFromInput() {

        ArrayList<Rover> roverList = mainAppController.getAllRovers();

        Coordinates rover1Coordinates = new Coordinates(1, 2);
        Coordinates rover2Coordinates = new Coordinates(3, 3);

     /*   Rover rover1 = new Rover(rover1Coordinates, 'N', plateauGrid);
        Rover rover2 = new Rover(rover2Coordinates, 'E', plateauGrid);
*/
        Rover rover1 = new Rover(rover1Coordinates, 'N');
        Rover rover2 = new Rover(rover2Coordinates, 'E');
        assertInstanceOf(rover1.getClass(), roverList.get(0));
        assertInstanceOf(rover2.getClass(), roverList.get(1));
    }

    @Test
    public void startOperations() {
        mainAppController.startOperations(allRovers);
        ArrayList<Rover> roverList = mainAppController.getAllRovers();

        Coordinates rover1CoordinatesExpected = new Coordinates(1, 3);
        Coordinates rover2CoordinatesExpected = new Coordinates(3, 3);

        Rover rover1 = new Rover(rover1CoordinatesExpected, 'N');
        Rover rover2 = new Rover(rover2CoordinatesExpected, 'E');

        assertEquals(1, roverList.get(0).getRoverLocation().getX());
        assertEquals(3, roverList.get(0).getRoverLocation().getY());

        assertEquals(5, roverList.get(1).getRoverLocation().getX());
        assertEquals(1, roverList.get(1).getRoverLocation().getY());


    }

    @Test
    public void moveToCorrectSpots() {

        ArrayList<Rover> roverList = mainAppController.getAllRovers();

        Coordinates rover1CoordinatesExpected = new Coordinates(1, 2);
        Coordinates rover2CoordinatesExpected = new Coordinates(3, 3);
        Rover rover1 = new Rover(rover1CoordinatesExpected, 'N');
        Rover rover2 = new Rover(rover2CoordinatesExpected, 'E');

        assertEquals(rover1.getRoverLocation().getX(), roverList.get(0).getRoverLocation().getX());
        assertEquals(rover1.getRoverLocation().getY(), roverList.get(0).getRoverLocation().getY());
        assertEquals(rover1.getRoverDirection(), roverList.get(0).getRoverDirection());

        assertEquals(rover2.getRoverLocation().getX(), roverList.get(1).getRoverLocation().getX());
        assertEquals(rover2.getRoverLocation().getY(), roverList.get(1).getRoverLocation().getY());
        assertEquals(rover2.getRoverDirection(), roverList.get(1).getRoverDirection());
    }

    @Test
    public void countRovers() {
        assertEquals(2, mainAppController.getRoverCount(), "rover count");
    }


}
