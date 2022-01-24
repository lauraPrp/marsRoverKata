package marsrover.rover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverTest {

    //--getting the input
    //1st creating the grid/plateau (Coordinates class)
    //2nd get rover position
    //3d getactual directions

    //4rd move/rotate the rover : R-L M


    @Test
    public void wrongFileInputCommands ()  throws FileNotFoundException {
        Rover rover = new Rover();
        assertThrows(FileNotFoundException.class, () -> rover.getInputFromFile("fileNotPresent.txt"));
    }
    @Test
    public void gettingInputCommands ()  throws FileNotFoundException {
        Rover rover = new Rover();
        rover.getInputFromFile("testInput.txt");
    }



}
