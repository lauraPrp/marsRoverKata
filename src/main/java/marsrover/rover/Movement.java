package marsrover.rover;

interface Movement {
    char turnRight(char direction);

    char turnLeft(char direction);

    void move(Plateau plateau) throws OutOfPlateauBoundaryException, UnsupportedOperationException;
}
