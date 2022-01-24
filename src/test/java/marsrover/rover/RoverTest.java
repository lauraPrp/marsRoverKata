package marsrover.rover;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoverTest {

    //--getting the input
    //1st creating the grid/plateau (Coordinates class)
    //2nd get rover position
    //3d getactual directions

    //4rd move/rotate the rover : R-L M


    @Test
    public void gettingInputCommands() {
        Rover rover = new Rover();
        Coordinates plateauGrid = rover.getPlateau();
        assertEquals(5, plateauGrid.getMaxX());
        assertEquals(5, plateauGrid.getMaxY());
    }


}
