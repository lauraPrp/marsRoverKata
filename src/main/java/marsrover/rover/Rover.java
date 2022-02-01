package marsrover.rover;

import java.util.ArrayList;

public class Rover {

    private Coordinates roverCoordinates;
    private char direction;
    private char[] movementCommandList;
    private final Plateau plateau;
    boolean pathCompleted;

    String message;

    public Plateau getPlateau() {
        return plateau;
    }

    public char[] getMovementCommandList() {
        return movementCommandList;
    }

    public void setMovementCommandList(char[] movementCommandList) {
        this.movementCommandList = movementCommandList;
    }


    public Rover(Coordinates start, char direction, Plateau grid) {
        this.roverCoordinates = start;
        this.direction = direction;
        this.plateau = grid;
        this.pathCompleted = false;
        this.message=" ";
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

    public void setRoverdirection(char newDirection) {
        this.direction = newDirection;
    }


    public Rover executeCommandList(Rover rover) throws UnsupportedOperationException {
        char[] commandlist = rover.getMovementCommandList();
        message=message.concat(
        "I start at: "+rover.getRoverLocation().getX()+"," +
                getRoverLocation().getY());
        try {
            for (char element : commandlist) {
                rover = singleCommand(rover, element);
            }
            message=message.concat("  I stopped at:"+rover.getRoverLocation().getX()+"," +
                    getRoverLocation().getY());
        } catch (UnsupportedOperationException use) {
            //use.printStackTrace();

        } finally {

            rover.pathCompleted = true;
            rover.setMessage(message);
            plateau.addObstacle(rover.getRoverLocation());
        }


        return rover;
    }


    public Rover singleCommand(Rover rover, char command) throws IllegalStateException {
        char dir = rover.getRoverDirection();
        switch (command) {
            case 'R' -> dir = turnRight(dir);
            case 'L' -> dir = turnLeft(dir);
            case 'M' -> rover = move(rover);
            default -> throw new IllegalStateException("Error: command invalid");
        }

        rover.setRoverdirection(dir);
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

    private boolean isThereAnObstacleinNextStep(Coordinates newCoordinates, ArrayList<Coordinates> obstacles) {
        boolean ret = false;
        //for some reason the Arraylist.contains method returns false despite having an obstacle at the same coordinates of "new coordimnates"
        /*@todo: investigate why*/
    	/*if(obstacles.contains(newCoordinates))	System.out.println("obstacle: "+obstacles.toString());
    	else System.out.println("no obstacle: ");
        return obstacles.contains(newCoordinates);*/

        if (obstacles.size() > 0) {
            for (Coordinates obstacle : obstacles) {
                if (obstacle.getX() == newCoordinates.getX() &&
                        obstacle.getY() == newCoordinates.getY())
                    return true;
            }
        }
        return ret;
    }

    private Rover move(Rover roverMoving) throws UnsupportedOperationException {
        char dir = roverMoving.getRoverDirection();
        Coordinates newCoordinates = new Coordinates(roverMoving.getRoverLocation().getX(), roverMoving.getRoverLocation().getY());

        switch (dir) {
            case 'N' -> newCoordinates.setY(newCoordinates.getY() + 1);
            case 'W' -> newCoordinates.setX(newCoordinates.getX() - 1);
            case 'S' -> newCoordinates.setY(newCoordinates.getY() - 1);
            case 'E' -> newCoordinates.setX(newCoordinates.getX() + 1);
            default -> throw new UnsupportedOperationException("Error: new Coordinates invalid");
        }

        if (isThereAnObstacleinNextStep(newCoordinates, plateau.getObstacles())) {
            roverMoving.setMessage(message+=" Early stop, obstacle found. I stopped at: "+ roverMoving.getRoverLocation().getX()+","+ roverMoving.getRoverLocation().getY());
            throw new UnsupportedOperationException("Error: there is an obstacle");

        } else {
            roverMoving.setRoverLocation(newCoordinates);
            //roverMoving.setMessage(message+=" I am at: "+ roverMoving.getRoverLocation().getX()+","+ roverMoving.getRoverLocation().getY());
        }
        //System.out.println("new coord: " + newCoordinates.getX() + " " + newCoordinates.getY());
        return roverMoving;
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


