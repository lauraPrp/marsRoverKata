package marsrover.rover;

public abstract class Vehicle  implements Movement {

    @Override
    public abstract char turnRight(char direction) ;

    @Override
    public abstract char turnLeft(char direction) ;

    @Override
    public abstract void move(Plateau plateau) throws OutOfPlateauBoundaryException, UnsupportedOperationException ;
}
