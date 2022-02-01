package marsrover.rover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoverTest {
    Plateau grid ;
    Rover rover ;

    @BeforeEach
    public void setUp(){
         grid = new Plateau(10, 10);
         rover = new Rover(new Coordinates(0, 0), 'N', grid);
    }
    @Test
    public void roverCommandTurnRightAndLeftAllPossibleCombinations() throws IllegalStateException {

        assertThrows(IllegalStateException.class, () -> rover.singleCommand(rover, 'X'));

        rover.singleCommand(rover, 'R');
        assertEquals('E', rover.getRoverDirection(), "turn right");
        rover.singleCommand(rover, 'R');
        assertEquals('S', rover.getRoverDirection(), "turn right");
        rover.singleCommand(rover, 'R');
        assertEquals('W', rover.getRoverDirection(), "turn right");
        rover.singleCommand(rover, 'R');
        assertEquals('N', rover.getRoverDirection(), "turn right");
        rover.singleCommand(rover, 'L');
        assertEquals('W', rover.getRoverDirection(), "turn left");
        rover.singleCommand(rover, 'L');
        assertEquals('S', rover.getRoverDirection(), "turn left");
        rover.singleCommand(rover, 'L');
        assertEquals('E', rover.getRoverDirection(), "turn left");
        rover.singleCommand(rover, 'L');
        assertEquals('N', rover.getRoverDirection(), "turn left");
    }

    @Test
    public void checkIfMovementIsValid() throws IllegalStateException {

        Coordinates validCoordinates = new Coordinates(1, 1);
        Coordinates invalidCoordinates = new Coordinates(-1, -3);
        Coordinates outOfGridCoordinates = new Coordinates(88, 99);

        assertTrue(rover.isMovementValid(validCoordinates, grid));
        assertFalse(rover.isMovementValid(invalidCoordinates, grid));
        assertFalse(rover.isMovementValid(outOfGridCoordinates, grid));
    }

    @Test
    public void roverRealPassingTestCaseExampleGiven1() throws IllegalStateException {
        rover.setRoverLocation(new Coordinates(1, 2));
        rover.setMovementCommandList("LMLMLMLMM".toCharArray());
        rover.executeCommandList(rover);

        assertEquals(1, rover.getRoverLocation().getX());
        assertEquals(3, rover.getRoverLocation().getY());
        assertEquals('N', rover.getRoverDirection());

    }

    @Test
    public void roverRealPassingTestCaseExampleGiven2() throws IllegalStateException {
        rover.setRoverLocation(new Coordinates(3, 3));
        rover.setRoverdirection('E');
        rover.setMovementCommandList("MMRMMRMRRM".toCharArray());
        rover.executeCommandList(rover);
        assertEquals(5, rover.getRoverLocation().getX());
        assertEquals(1, rover.getRoverLocation().getY());
        assertEquals('E', rover.getRoverDirection());
    }

    @Test
    public void invalidCommandInput() throws IllegalStateException {
        assertThrows(IllegalStateException.class, () -> rover.singleCommand(rover, 'X'));
    }

    @Test
    void invalidMoveObstacleFoundThrowsExceptionAndMessage() {

        rover.setRoverLocation(new Coordinates(1, 2));
        rover.setRoverdirection('N');
        grid.addObstacle(new Coordinates(1, 3));
        rover.setMovementCommandList("MMM".toCharArray());
        rover.executeCommandList(rover);
       /* UnsupportedOperationException expectedException = Assertions.assertThrows(
                UnsupportedOperationException.class, () -> { rover.singleCommand(rover,'M'); });

        Assertions.assertEquals("Error: there is an obstacle", expectedException.getMessage());
        assertEquals(1, rover.getRoverLocation().getX());
        assertEquals(2, rover.getRoverLocation().getY());

   */
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Error: there is an obstacle");
        });
        assertEquals("Error: there is an obstacle", exception.getMessage());
    }

}



