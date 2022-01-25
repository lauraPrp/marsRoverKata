package marsrover.rover;

public class Rover {


    private Coordinates roverCoordinates;
    private String direction;
    private String movementCommandList;
    private Plateau plateau;

    public Plateau getPlateau() {
        return plateau;
    }

    public String getMovementCommandList() {
        return movementCommandList;
    }

    public void setMovementCommandList(String movementCommandList) {
        this.movementCommandList = movementCommandList;
    }


    public Rover(Coordinates start, String direction, Plateau grid) {
        this.roverCoordinates = start;
        this.direction = direction;
        this.plateau = grid;
    }


    public Coordinates getRoverLocation() {
        return roverCoordinates;
    }

    public String getRoverdirection() {
        return direction;
    }

    public void setRoverLocation(Coordinates newCoordinates) {
        this.roverCoordinates = newCoordinates;
    }

    public void setRoverdirection(String newDirection) {
        this.direction = newDirection;
    }

    public Rover command(Rover rover, String command) throws IllegalStateException {
        String dir = rover.getRoverdirection();
        Coordinates actualCoord = rover.getRoverLocation();
        Plateau grid = rover.getPlateau();
        if (command.equals("R")) {
           if (dir.equals("N")) {
                dir = "E";
            } else if (dir.equals("E")) {
                dir = "S";
            } else if (dir.equals("S")) {
                dir = "W";
            } else if (dir.equals("W")) {
                dir = "N";
            } else {
                throw new UnsupportedOperationException("Error: cant turn a way that is not NSWE");
            }

        } else if (command.equals("L")) {
            if (dir.equals("N")) {
                dir = "W";
            } else if (dir.equals("W")) {
                dir = "S";
            } else if (dir.equals("S")) {
                dir = "E";
            } else if (dir.equals("E")) {
                dir = "N";
            } else {
                throw new UnsupportedOperationException("Error: cant turn a way that is not NSWE");
            }
        } else if (command.equals("M")) {
            if (dir.equals("N")) {
                actualCoord.setY(actualCoord.getY() + 1);

            } else if (dir.equals("W")) {
                actualCoord.setX(actualCoord.getX() - 1);
            } else if (dir.equals("S")) {
                actualCoord.setY(actualCoord.getY() - 1);
            } else if (dir.equals("E")) {
                actualCoord.setX(actualCoord.getX() + 1);
            } else {
                throw new UnsupportedOperationException("Error: invalid attempt to move");
            }
            if (isMovementValid(actualCoord,rover.getPlateau())) {
                setRoverLocation(actualCoord);
            } else {
                throw new UnsupportedOperationException("Error: new Coordinates not valid");
            }
        } else throw new IllegalStateException("Error: command invalid");

        rover.setRoverdirection(dir);


        return rover;
    }



    public boolean isMovementValid(Coordinates coordinates, Plateau grid) {
        boolean isValid = true;
        if (coordinates.getX() < 0
                || coordinates.getY() < 0
                || coordinates.getX() > grid.getMaxX()
                || coordinates.getY() > grid.getMaxY()
        )
            return false;

        return isValid;
    }

 /*   private Rover moveOne() {
        getRoverdirection();
        getRoverLocation();
    }

    private void turnLeft() {
    }

    private void turnRight() {
    }
*/
}


