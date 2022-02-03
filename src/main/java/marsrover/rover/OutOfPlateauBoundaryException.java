package marsrover.rover;

public class OutOfPlateauBoundaryException extends Throwable {
    public OutOfPlateauBoundaryException(String errorMessage) {
            super(errorMessage);
        }
}

