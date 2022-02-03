package marsrover.rover;


public class Rover {
    private Coordinates roverCoordinates;
    private char direction;
    private char[] movementCommandList;
    boolean pathCompleted;

    String message;

    public char[] getMovementCommandList() {
        return movementCommandList;
    }

    public void setMovementCommandList(char[] movementCommandList) {
        this.movementCommandList = movementCommandList;
    }

    public Rover(Coordinates start, char direction) {
        this.roverCoordinates = start;
        this.direction = direction;
        this.pathCompleted = false;
        this.message = " ";
    }

    public Coordinates getRoverLocation() {
        return roverCoordinates;
    }

    public char getRoverDirection() {
        return direction;
    }

    public void setRoverLocation(Coordinates newCoordinates) {
        this.roverCoordinates = newCoordinates;
    }

    public void setRoverDirection(char newDirection) {
        this.direction = newDirection;
    }


    public void executeCommandList(Plateau plateau) throws UnsupportedOperationException {
        char[] commandList = this.getMovementCommandList();
        try {
            for (char element : commandList) {
                singleCommand(element, plateau);
            }
        }
        catch(OutOfPlateauBoundaryException use) {
            throw new UnsupportedOperationException("Rover out of plateau range");
        }
        catch (UnsupportedOperationException uoe ) {
            throw new UnsupportedOperationException("error executing command");
        }
                catch (IllegalArgumentException iae ) {
            throw new IllegalArgumentException("command not valid");
        }
    }

    public void singleCommand(char command, Plateau plateau) throws OutOfPlateauBoundaryException,IllegalArgumentException {
        char dir = this.getRoverDirection();
        try {
            switch (command) {
                case 'R' -> dir = turnRight(dir);
                case 'L' -> dir = turnLeft(dir);
                case 'M' -> move(plateau);
                default -> throw new IllegalArgumentException();
            }
        }
          catch (OutOfPlateauBoundaryException oobpe) {
          //    this.setMessage(message += " Rover out of plateau range ");
            throw new OutOfPlateauBoundaryException(" commands error,Rover out of plateau range ");
        }
        catch (UnsupportedOperationException uoe) {
            this.setMessage(message += " error during movement ");
            throw new UnsupportedOperationException("error during movement");
        }
        catch (IllegalArgumentException iae){
            throw new IllegalArgumentException("command not valid");

        }
        this.setRoverDirection(dir);
    }




    private void move(Plateau plateau) throws OutOfPlateauBoundaryException,UnsupportedOperationException {

        char dir = this.getRoverDirection();

        Coordinates newCoordinates = new Coordinates(this.getRoverLocation().getX(), this.getRoverLocation().getY());

        switch (dir) {
            case 'N' -> newCoordinates.setY(newCoordinates.getY() + 1);
            case 'W' -> newCoordinates.setX(newCoordinates.getX() - 1);
            case 'S' -> newCoordinates.setY(newCoordinates.getY() - 1);
            case 'E' -> newCoordinates.setX(newCoordinates.getX() + 1);
            default -> throw new UnsupportedOperationException("invalid coordinates");
        }
        if (plateau.isMovementOutOfPlateau(newCoordinates, plateau)) {
            if (plateau.isThereAnObstacleinNextStep(newCoordinates, plateau.getObstacles())) {
                this.setMessage(message += " Early stop, obstacle found. ");

            } else {
                this.setRoverLocation(newCoordinates);
            }
        } else {
            this.setMessage(message += " Error: Rover out of plateau range: ");
            throw new OutOfPlateauBoundaryException("Error: Rover out of plateau range");
        }
    }


    private char turnLeft(char dir) {
        switch (dir) {
            case 'N' -> dir = 'W';
            case 'W' -> dir = 'S';
            case 'S' -> dir = 'E';
            case 'E' -> dir = 'N';
            default -> throw new UnsupportedOperationException("Error: cant turn a way that is not NSWE");
        }

        return dir;
    }

    private char turnRight(char dir) {
        switch (dir) {
            case 'N' -> dir = 'E';
            case 'E' -> dir = 'S';
            case 'S' -> dir = 'W';
            case 'W' -> dir = 'N';
            default -> throw new UnsupportedOperationException("Error: cant turn a way that is not NSWE");
        }
        return dir;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


