package marsrover.rover;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Rover {

    private Coordinates roverStart;
    private String directionStart;
    private String movementCommandList;


    public String getMovementCommandList() {
        return movementCommandList;
    }

    public void setMovementCommandList(String movementCommandList) {
        this.movementCommandList = movementCommandList;
    }


    public Rover(Coordinates start, String direction) {
        this.roverStart = start;
        this.directionStart = direction;
    }


    public Coordinates getRoverLocation() {
        return roverStart;
    }

    public String getRoverdirection() {
        return directionStart;
    }

    public void setRoverLocation(Coordinates newCoordinates) {
        this.roverStart = newCoordinates;
    }

    public void setRoverdirection(String newDirection) {
        this.directionStart = newDirection;
    }
}


