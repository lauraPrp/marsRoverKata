package marsrover.rover;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerEdgeCasesAndErrorsTest {

    Controller mainApp;

    @BeforeEach
    public void setUp() {
        mainApp = Controller.getControllerInstance();
    }

    @Test
    public void nonexistentFile() {

        Plateau grid = mainApp.getPlateau();
        ArrayList<Rover> rovers = mainApp.getAllRovers();
        assertThrows(FileNotFoundException.class, () -> mainApp.initAll("noFile.txt"));
    }

    @Test
    public void wrongFileInvalidRoverPosition() {
        assertThrows(IllegalArgumentException.class, () ->
                mainApp.initAll("autotests/testInputWrongFormat.txt"), "input invalid, check format");
    }

    @Test
    public void checkPositionIfRoverMeetAnObstacle() throws FileNotFoundException {
        mainApp.initAll("autotests/testInputWithObstacle.txt");
        ArrayList<Rover> roverList = mainApp.getAllRovers();
        mainApp.startOperations(roverList);

        assertEquals(1, roverList.get(0).getRoverLocation().getX());
        assertEquals(3, roverList.get(0).getRoverLocation().getY());

        assertEquals(5, roverList.get(1).getRoverLocation().getX());
        assertEquals(1, roverList.get(1).getRoverLocation().getY());

        assertEquals(1, roverList.get(2).getRoverLocation().getX());
        assertEquals(2, roverList.get(2).getRoverLocation().getY());
        assertNotNull(roverList.get(2).getMessage());

    }

}
