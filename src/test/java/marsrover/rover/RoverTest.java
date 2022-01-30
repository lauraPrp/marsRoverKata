package marsrover.rover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class RoverTest {

    @Test
    public void roverCommandTurnRightAndLeft() throws IllegalStateException {
        Plateau grid = new Plateau(10, 10);
        Rover rover = new Rover(new Coordinates(0, 0), 'N', grid);


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
    public void checkifMovementIsValid() throws IllegalStateException {
        Plateau grid = new Plateau(10, 10);
        Rover rover = new Rover(new Coordinates(0, 0), 'N', grid);


        Coordinates validCoordinates = new Coordinates(1, 1);
        Coordinates invalidCoordinates = new Coordinates(-1, -3);
        Coordinates outOfGridCoordinates = new Coordinates(88, 99);


        assertTrue(rover.isMovementValid(validCoordinates, grid));
        assertFalse(rover.isMovementValid(invalidCoordinates, grid));
        assertFalse(rover.isMovementValid(outOfGridCoordinates, grid));
    }

    @Test
    public void roverRealPassingTestCaseExampleGiven1() throws IllegalStateException {
        Plateau grid = new Plateau(10, 10);
        Rover rover = new Rover(new Coordinates(1, 2), 'N', grid);
        rover.setMovementCommandList("LMLMLMLMM".toCharArray());
        rover.executeCommandList(rover);

        assertEquals(1, rover.getRoverLocation().getX());
        assertEquals(3, rover.getRoverLocation().getY());
        assertEquals('N', rover.getRoverDirection());

    }

    @Test
    public void roverRealPassingTestCaseExampleGiven2() throws IllegalStateException {
        Plateau grid = new Plateau(10, 10);
        Rover rover = new Rover(new Coordinates(3, 3), 'E', grid);
        rover.setMovementCommandList("MMRMMRMRRM".toCharArray());
        rover.executeCommandList(rover);
        assertEquals(5, rover.getRoverLocation().getX());
        assertEquals(1, rover.getRoverLocation().getY());
        assertEquals('E', rover.getRoverDirection());
    }

    @Test
    public void invalidCommandInput() throws IllegalStateException {
        Plateau grid = new Plateau(10, 10);
        Rover rover = new Rover(new Coordinates(1, 2), 'N', grid);

        assertThrows(IllegalStateException.class,() -> rover.singleCommand(rover, 'X'));
    }

    @Test
    public void  invalidMoveObstacleFoundThrowsException() {
        Plateau grid = new Plateau(10, 10);
        
        grid.addObstacle(new Coordinates(1,3));
        Rover rover = new Rover(new Coordinates(1, 2), 'N', grid);
        rover.setMovementCommandList("MMM".toCharArray());
        rover.executeCommandList(rover);
        
        assertThrows(java.lang.UnsupportedOperationException.class,() -> rover.singleCommand(rover, 'M'));

        }
    }



