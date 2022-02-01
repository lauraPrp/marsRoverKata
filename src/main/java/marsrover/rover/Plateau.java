package marsrover.rover;

import java.util.ArrayList;

public class Plateau {
    Coordinates plauteauCoordinates;
    ArrayList<Coordinates> obstacles;

    public ArrayList<Coordinates> getObstacles() {
        return obstacles;
    }

    public void addObstacle(Coordinates obstaclesCoord) {
        obstacles = this.getObstacles();
        obstacles.add(obstaclesCoord);
    }


    int maxX;
    int maxY;

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


    @Override
    public String toString() {
        return "Grid{}" + maxX + " " + maxY;
    }
}
