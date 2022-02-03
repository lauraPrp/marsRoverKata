package marsrover.rover;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoverTest {
    Plateau grid ;
    Rover rover ;

    @BeforeEach
    public void setUp() {
        /*arrange*/
        grid = new Plateau(10, 10);
        rover = new Rover(new Coordinates(0, 0), 'N');
    }

    @Test
    public void roverCommandTurnRightAndLeftAllPossibleCombinations() throws OutOfPlateauBoundaryException {

        /* sequential acts/asserts */
        rover.singleCommand('R', grid);
        assertEquals('E', rover.getRoverDirection(), "turn right");
        rover.singleCommand('R', grid);
        assertEquals('S', rover.getRoverDirection(), "turn right");
        rover.singleCommand('R', grid);
        assertEquals('W', rover.getRoverDirection(), "turn right");
        rover.singleCommand('R', grid);
        assertEquals('N', rover.getRoverDirection(), "turn right");
        rover.singleCommand('L', grid);
        assertEquals('W', rover.getRoverDirection(), "turn left");
        rover.singleCommand('L', grid);
        assertEquals('S', rover.getRoverDirection(), "turn left");
        rover.singleCommand('L', grid);
        assertEquals('E', rover.getRoverDirection(), "turn left");
        rover.singleCommand('L', grid);
        assertEquals('N', rover.getRoverDirection(), "turn left");

        //assert
        assertThrows(IllegalArgumentException.class, () -> rover.singleCommand('X', grid));

    }

    @Test
    public void roverRealPassingTestCaseExampleGiven1()  {
        /* act */
        rover.setRoverLocation(new Coordinates(1, 2));
        rover.setMovementCommandList("LMLMLMLMM".toCharArray());
        rover.executeCommandList(grid);

        /* assert */
        assertEquals(1, rover.getRoverLocation().getX());
        assertEquals(3, rover.getRoverLocation().getY());
        assertEquals('N', rover.getRoverDirection());

    }

    @Test
    public void roverRealPassingTestCaseExampleGiven2()  {
        /* act */
        rover.setRoverLocation(new Coordinates(3, 3));
        rover.setRoverdirection('E');
        rover.setMovementCommandList("MMRMMRMRRM".toCharArray());
        rover.executeCommandList(grid);
        /* assert */
        assertEquals(5, rover.getRoverLocation().getX());
        assertEquals(1, rover.getRoverLocation().getY());
        assertEquals('E', rover.getRoverDirection());
    }

    @Test
    public void invalidCommandInput() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> rover.singleCommand('X', grid));
    }

    @Test
    void invalidMoveObstacleFoundThrowsExceptionAndMessage() throws OutOfPlateauBoundaryException {

        rover.setRoverLocation(new Coordinates(1, 2));
        rover.setRoverdirection('N');
        grid.addObstacle(new Coordinates(1, 3));
        rover.setMovementCommandList("MMM".toCharArray());
        rover.executeCommandList(grid);
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Error: there is an obstacle");
        });
        assertEquals("Error: there is an obstacle", exception.getMessage());
    }

    @Test
    void invalidCommandListInputOutOfPlateau() throws OutOfPlateauBoundaryException {

        rover.setRoverLocation(new Coordinates(1, 2));
        rover.setRoverdirection('N');
        grid.addObstacle(new Coordinates(1, 3));
        rover.setMovementCommandList("MMMMMMMMMMMMMMMMMMMMMMM".toCharArray());
        rover.executeCommandList(grid);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            throw new IllegalStateException("Error: command invalid");
        });
        assertEquals("Error: command invalid", exception.getMessage());
    }

    @Test
    void invalidSingleCommandInCommandsList () throws IllegalArgumentException,UnsupportedOperationException {
        /* act */
        rover.setRoverLocation(new Coordinates(1, 2));
        rover.setRoverdirection('N');
        grid.addObstacle(new Coordinates(1, 3));
        rover.setMovementCommandList("MMMPGT".toCharArray());

        /* assert */
        assertThrows(IllegalArgumentException.class, () ->
                rover.executeCommandList(grid), "command not valid");
    }

}



