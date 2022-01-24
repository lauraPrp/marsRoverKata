import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoverTest {

    //--getting the input
    //1st creating the grid/plateau (Coordinates class)
    //2nd get rover position
    //3d getactual directions

    //4rd move/rotate the rover : R-L M


    @Test
    public void gettingInputCommands(){
        Rover rover = new Rover();
          int linesNumber=  rover.getInput();
         assertEquals(3,linesNumber);
    }


}
