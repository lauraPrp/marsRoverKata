package marsrover.rover;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoverTest {

    @Test
    public void roverCommandTurnRightAndLeft() throws IllegalStateException {
        Plateau grid = new Plateau(10, 10);
        Rover rover = new Rover(new Coordinates(0, 0), "N", grid);


        assertThrows(IllegalStateException.class, () -> rover.command(rover, "Invalid command"));

        rover.command(rover, "R");
        assertEquals("E", rover.getRoverDirection(), "turn right");
        rover.command(rover, "R");
        assertEquals("S", rover.getRoverDirection(), "turn right");
        rover.command(rover, "R");
        assertEquals("W", rover.getRoverDirection(), "turn right");
        rover.command(rover, "R");
        assertEquals("N", rover.getRoverDirection(), "turn right");
        rover.command(rover, "L");
        assertEquals("W", rover.getRoverDirection(), "turn left");
        rover.command(rover, "L");
        assertEquals("S", rover.getRoverDirection(), "turn left");
        rover.command(rover, "L");
        assertEquals("E", rover.getRoverDirection(), "turn left");
        rover.command(rover, "L");
        assertEquals("N", rover.getRoverDirection(), "turn left");
    }

    @Test
    public void checkifMovementIsValid() throws IllegalStateException {
        Plateau grid = new Plateau(10, 10);
        Rover rover = new Rover(new Coordinates(0, 0), "N", grid);


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
        Rover rover = new Rover(new Coordinates(1, 2), "N", grid);

        rover.command(rover, "L");
        rover.command(rover, "M");
        rover.command(rover, "L");
        rover.command(rover, "M");
        rover.command(rover, "L");
        rover.command(rover, "M");
        rover.command(rover, "L");
        rover.command(rover, "M");
        rover.command(rover, "M");

        assertEquals(1, rover.getRoverLocation().getX());
        assertEquals(3, rover.getRoverLocation().getY());
        assertEquals("N", rover.getRoverDirection());

    }

    @Ignore
    public void roverRealPassingTestCaseExampleGiven2() throws IllegalStateException {
        Plateau grid = new Plateau(10, 10);
        Rover rover = new Rover(new Coordinates(3, 3), "E", grid);

        rover.command(rover, "M");
        rover.command(rover, "M");
        rover.command(rover, "R");
        rover.command(rover, "M");
        rover.command(rover, "M");
        rover.command(rover, "R");
        rover.command(rover, "M");
        rover.command(rover, "R");
        rover.command(rover, "R");
        rover.command(rover, "M");

        assertEquals(5, rover.getRoverLocation().getX());
        assertEquals(1, rover.getRoverLocation().getY());
        assertEquals("E", rover.getRoverDirection());
    }

    @Test
    public void invalidCommandInput() throws IllegalStateException {
        Plateau grid = new Plateau(10, 10);
        Rover rover = new Rover(new Coordinates(1, 2), "N", grid);

        assertThrows(IllegalStateException.class,() -> rover.command(rover, "X"));
    }

    @Test
    public void  invalidMoveObstacleFound() {
        Plateau grid = new Plateau(10, 10);
        Rover rover = new Rover(new Coordinates(1, 2), "N", grid);
        ArrayList<Coordinates> obstacles  = new ArrayList<>();
        obstacles.add(new Coordinates(1,5));
        System.out.println(rover.getRoverLocation().getX() +" " + rover.getRoverLocation().getY());
        rover.command(rover,"M");
        rover.command(rover,"M");
       rover.command(rover,"M");
        System.out.println(rover.getRoverLocation().getX() +" " + rover.getRoverLocation().getY());
        assertThrows(java.lang.UnsupportedOperationException.class,() -> rover.command(rover, "M"));
    }


}
