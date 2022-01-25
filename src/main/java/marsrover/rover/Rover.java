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
        //NESW
        //R +1
        //L -1
    String dir = rover.getRoverdirection();
        if (command.equals("R")){
     // turnRight();
            if(dir.equals("N")){
            dir="E";
            }
            else if(dir.equals("E")){
                dir="S";
            }
            else if(dir.equals("S")){
                dir="W";
            }
            else if(dir.equals("W")){
                dir="N";
            }else{
                throw new UnsupportedOperationException("Error: cant turn a way that is not NSWE");
            }

    } else if(command.equals("L")){
       // turnLeft();
            if(dir.equals("N")){
                dir="W";
            }
            else if(dir.equals("W")){
                dir="S";
            }
            else if(dir.equals("S")){
                dir="E";
            }
            else if(dir.equals("E")){
                dir="N";
            }else{
                throw new UnsupportedOperationException("Error: cant turn a way that is not NSWE");
            }
    }else if(command.equals("M")){
       // moveOne();
            if(dir.equals("N")){
                //y+1

            }
            else if(dir.equals("W")){
            //x-1
            }
            else if(dir.equals("S")){
                //y-1
            }
            else if(dir.equals("E")){
                //x+1
            }else{
                throw new UnsupportedOperationException("Error: invalid attempt to move");
            }
    }
    else throw new IllegalStateException("Error: command invalid");

    rover.setRoverdirection(dir);

        return rover;
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


