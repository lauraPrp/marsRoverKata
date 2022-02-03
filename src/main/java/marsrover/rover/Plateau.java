package marsrover.rover;

import java.util.ArrayList;

public class Plateau {
    int maxX;
    int maxY;
    ArrayList<Coordinates> obstacles;

    public Plateau(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.obstacles = new ArrayList<>();
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public ArrayList<Coordinates> getObstacles() {
        return obstacles;
    }

    public void addObstacle(Coordinates obstaclesCoord) {
        obstacles = this.getObstacles();
        obstacles.add(obstaclesCoord);
    }

    protected boolean isMovementOutOfPlateau(Coordinates targetPlace, Plateau grid) {
        return !(targetPlace.getX() < 0 || targetPlace.getY() < 0
                || targetPlace.getX() > grid.getMaxX() || targetPlace.getY() > grid.getMaxY());

    }

    protected boolean isThereAnObstacleinNextStep(Coordinates newCoordinates, ArrayList<Coordinates> obstacles) {
        boolean ret = false;
        if (obstacles.size() > 0) {
            for (Coordinates obstacle : obstacles) {
                if (obstacle.getX() == newCoordinates.getX() &&
                        obstacle.getY() == newCoordinates.getY())
                    return true;
            }
        }
        return ret;
    }
}
