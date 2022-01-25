package marsrover.rover;

import App.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoverTest {

    //--getting the input
    //1st creating the grid/plateau (Coordinates class)
    //2nd get rover position
    //3d getactual directions

    //4rd move/rotate the rover : R-L M



    @Test
    public void roverExecCommandTurnRightAndLeft() throws IllegalStateException {
        Plateau grid = new Plateau(10,10);
        Rover rover = new Rover(new Coordinates(0, 0), "N",grid);


        assertThrows(IllegalStateException.class, () -> rover.command(rover, "Invalid command"));
        rover.command(rover, "R");
        assertEquals("E", rover.getRoverdirection(), "turn right");
        rover.command(rover, "R");
        assertEquals("S", rover.getRoverdirection(), "turn right");
        rover.command(rover, "R");
        assertEquals("W", rover.getRoverdirection(), "turn right");
        rover.command(rover, "R");
        assertEquals("N", rover.getRoverdirection(), "turn right");
        rover.command(rover, "L");
        assertEquals("W", rover.getRoverdirection(), "turn left");
        rover.command(rover, "L");
        assertEquals("S", rover.getRoverdirection(), "turn left");
        rover.command(rover, "L");
        assertEquals("E", rover.getRoverdirection(), "turn left");
        rover.command(rover, "L");
        assertEquals("N", rover.getRoverdirection(), "turn left");
    }

    @Test
    public void checkifMovementIsValid() throws IllegalStateException {
        Plateau grid = new Plateau(10,10);
        Rover rover = new Rover(new Coordinates(0, 0), "N",grid);


        Coordinates validCoordinates = new Coordinates(1, 1);
        Coordinates invalidCoordinates = new Coordinates(-1, -3);
        Coordinates outOfGridCoordinates = new Coordinates(88, 99);


        assertTrue(rover.isMovementValid(validCoordinates, grid));
        assertFalse(rover.isMovementValid(invalidCoordinates, grid));
        assertFalse(rover.isMovementValid(outOfGridCoordinates, grid));
    }
    @Test
    public void roverExecCommandMove() throws IllegalStateException {
        Plateau grid = new Plateau(10,10);
        Rover rover = new Rover(new Coordinates(1, 2), "N",grid);

      // LMLMLMLMM  Rover rover = new Rover(new Coordinates(0, 0), "N",grid);
        rover.command(rover, "L");
        rover.command(rover, "M");
        rover.command(rover, "L");
        rover.command(rover, "M");
        rover.command(rover, "L");
        rover.command(rover, "M");
        rover.command(rover, "L");
        rover.command(rover, "M");
        rover.command(rover, "M");
        System.out.println("rover coordinates x "+rover.getRoverLocation().getX()+
                " y "+rover.getRoverLocation().getY() +" heading To: "+rover.getRoverdirection());
      //  assertEquals("E", rover.getRoverdirection(), "turn right");
     //   rover.command(rover, "M");
       // System.out.println("rover coordinates x "+rover.getRoverLocation().getX()+" y "+rover.getRoverLocation().getY());
       // assertEquals("E", rover.getRoverdirection(), "move");
    }
}
