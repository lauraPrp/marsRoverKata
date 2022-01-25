package marsrover.rover;

public class Rover {

    private Coordinates roverCoordinates;
    private String direction;
    private String movementCommandList;
    private final Plateau plateau;

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

    public String getRoverDirection() {
        return direction;
    }

    public void setRoverLocation(Coordinates newCoordinates) {
        this.roverCoordinates = newCoordinates;
    }

    public void setRoverdirection(String newDirection) {
        this.direction = newDirection;
    }

    public Rover command(Rover rover, String command) throws IllegalStateException {
        String dir = rover.getRoverDirection();
        switch (command) {
            case "R" -> dir = turnRight(dir);
            case "L" -> dir = turnLeft(dir);
            case "M" -> rover = move(rover);
            default -> throw new IllegalStateException("Error: command invalid");
        }

        rover.setRoverdirection(dir);
//todo:add rover to moved list to check collisions later
        return rover;
    }


    public boolean isMovementValid(Coordinates targetPlace, Plateau grid) {
        boolean isValid = true;
        if (targetPlace.getX() < 0
                || targetPlace.getY() < 0
                || targetPlace.getX() > grid.getMaxX()
                || targetPlace.getY() > grid.getMaxY()
        )
            return false;

        return isValid;
    }

    private Rover move(Rover roverMoving) {
        String dir = roverMoving.getRoverDirection();
        Coordinates actualCoord = roverMoving.getRoverLocation();

        switch (dir) {
            case "N" -> actualCoord.setY(actualCoord.getY() + 1);
            case "W" -> actualCoord.setX(actualCoord.getX() - 1);
            case "S" -> actualCoord.setY(actualCoord.getY() - 1);
            case "E" -> actualCoord.setX(actualCoord.getX() + 1);
            default -> throw new UnsupportedOperationException("Error: new Coordinates not valid");
        }

        return roverMoving;
    }

    private String turnLeft(String dir) {
        switch (dir) {
            case "N" -> dir = "W";
            case "W" -> dir = "S";
            case "S" -> dir = "E";
            case "E" -> dir = "N";
            default -> throw new UnsupportedOperationException("Error: cant turn a way that is not NSWE");
        }

        return dir;
    }

    private String turnRight(String dir) {
        switch (dir) {
            case "N" -> dir = "E";
            case "E" -> dir = "S";
            case "S" -> dir = "W";
            case "W" -> dir = "N";
            default -> throw new UnsupportedOperationException("Error: cant turn a way that is not NSWE");
        }
        return dir;

    }

}


