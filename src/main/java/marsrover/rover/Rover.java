package marsrover.rover;

public class Rover {


    private Coordinates roverStart;
    private String direction;
    private String movementCommandList;


    public String getMovementCommandList() {
        return movementCommandList;
    }

    public void setMovementCommandList(String movementCommandList) {
        this.movementCommandList = movementCommandList;
    }


    public Rover(Coordinates start, String direction) {
        this.roverStart = start;
        this.direction = direction;
    }


    public Coordinates getRoverLocation() {
        return roverStart;
    }

    public String getRoverdirection() {
        return direction;
    }

    public void setRoverLocation(Coordinates newCoordinates) {
        this.roverStart = newCoordinates;
    }

    public void setRoverdirection(String newDirection) {
        this.direction = newDirection;
    }

    public Rover command(Rover rover, String command) throws IllegalStateException{
    if (command.equals("R")){
       String actualDir= rover.getRoverdirection();
       //rotate accordimngly
       rover.setRoverdirection("");

    }
    else throw new IllegalStateException("command invalid");
        return rover;
    }

}


