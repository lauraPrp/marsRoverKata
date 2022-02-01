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


    @Override
    public String toString() {
        return "Grid{}" + maxX + " " + maxY;
    }
}
