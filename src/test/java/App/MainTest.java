package App;

import marsrover.rover.Coordinates;
import marsrover.rover.Plateau;
import marsrover.rover.Rover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    // todo: why the @before/@beforeAll/@beforeEach doesnt work?
    @BeforeEach
    public void setUp() throws FileNotFoundException {

        Main mainAppController = new Main();
        mainAppController.getInputFromFile("testInput.txt");
        Plateau plateauGrid = mainAppController.initPlateau();
    }

    @Test
    public void wrongFileInputCommands() throws FileNotFoundException {
        Main mainApp = new Main();
        assertThrows(FileNotFoundException.class, () -> mainApp.getInputFromFile("fileNotPresent.txt"));
    }

    //todo: add exceptions in case the file format is not how foreseen?

    @Test
    public void createPlateauFromInput() throws FileNotFoundException {


      //  assertEquals(5, plateauGrid.getMaxX(), "plateau x coord");
      //  assertEquals(5, plateauGrid.getMaxY(), "plateau y coord");

    }

    @Test
    public void createListOfRoversFromInput() throws FileNotFoundException {
        Main mainAppController = new Main();
        mainAppController.getInputFromFile("testInput.txt");
        Plateau plateau = mainAppController.initPlateau();


        ArrayList<Rover> roverList = mainAppController.initRovers();
        ArrayList<Rover> testRoverList = new ArrayList<>();
        Coordinates rover1Coordinates = new Coordinates(1, 2);
        Coordinates rover2Coordinates = new Coordinates(3, 3);
        Rover rover1 = new Rover(rover1Coordinates, "N", plateau);
        Rover rover2 = new Rover(rover2Coordinates, "E",plateau);
        //test list content?
        testRoverList.add(rover1);
        testRoverList.add(rover2);
        //how to compare 2 objects ?
        assertInstanceOf(rover1.getClass(),roverList.get(0));
        assertInstanceOf(rover1.getClass(),roverList.get(1));

    }

    public void countRovers() throws FileNotFoundException {
        Main mainAppController = new Main();
        mainAppController.getInputFromFile("testInput.txt");
        mainAppController.initRovers();

        assertEquals(2, mainAppController.getRoverCount(), "rover count");
    }
}
