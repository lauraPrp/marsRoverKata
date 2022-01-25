package marsrover.rover;

public class Plateau {
   Coordinates plauteauCoordinates;
    int maxX;
    int maxY;

    public Plateau(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public Plateau(Coordinates plauteauCoord) {
        this.plauteauCoordinates = plauteauCoord;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    @Override
    public String toString() {
        return "Grid{}"+ maxX+" "+maxY;
    }
}
