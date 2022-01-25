package marsrover.rover;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class RoverTest {

    //--getting the input
    //1st creating the grid/plateau (Coordinates class)
    //2nd get rover position
    //3d getactual directions

    //4rd move/rotate the rover : R-L M

    @Test
    public void roverExecCommandMoveOrTurn() throws IllegalStateException {
        Rover rover = new Rover(new Coordinates(0,0),"N");

        assertThrows(IllegalStateException.class, () -> rover.command(rover,"Invalid command"));
        rover.command(rover,"R");
        assertEquals("E",rover.getRoverdirection() , "turn right");
    }




    }
