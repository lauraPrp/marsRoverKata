package marsrover.rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Controller {
    private static volatile Controller controllerInstance = null;
    private ArrayList<String> input = new ArrayList<>();
    private Plateau plateau;
    private int roverCount;

    private ArrayList<Rover> allRovers;

    private Controller() { }

    public static Controller getControllerInstance() {
        if (controllerInstance == null)
            synchronized (Controller.class) {
                if (controllerInstance == null)
                    controllerInstance = new Controller();
            }
        return controllerInstance;
    }

    private static <T> Stream<T> convertListToStream(List<T> list) {
        return list.stream();
    }

    public void initAll(String file) throws FileNotFoundException {
        input = getInputFromFile(file);
        initPlateau();
        allRovers = initRovers();
    }

    private ArrayList<String> getInputFromFile(String filename) throws FileNotFoundException, IllegalArgumentException {
        ArrayList<String> allDataInput = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNext()) {
            allDataInput.add(scanner.next().toUpperCase());
        }
        scanner.close();
        if (validateInputFileData(allDataInput))
            return allDataInput;
        else
            throw new FileNotFoundException();

    }

    public Plateau getPlateau() {
        return this.plateau;
    }

    public ArrayList<Rover> getAllRovers() {
        return allRovers;
    }

    private boolean validateInputFileData(ArrayList<String> inputToValidate) {
        return ((inputToValidate.size() == 6) ||
                (inputToValidate.size() > 6)
                        && (inputToValidate.size() - 6) % 4 == 0);
    }

    private void initPlateau() {
        plateau = new Plateau(Integer.parseInt(input.get(0)), Integer.parseInt(input.get(1)));
    }

    /*
    from the second row of the input text file details of each single rover are listed in two rows
    so the first rover will be detailed in row 2 and row 3,
    the second rover 2 in row 4 and row 5 and so on for any following rover(if present).
     */
    private ArrayList<Rover> initRovers() {
        allRovers = new ArrayList<>();
        Rover roverToList;
        for (int i = 2; i < input.size(); i += 4) {
            try {
                roverToList = new Rover(
                        new Coordinates(Integer.parseInt(input.get(i)), Integer.parseInt(input.get(i + 1))),
                        input.get(i + 2).charAt(0));

                roverToList.setMovementCommandList(input.get(i + 3).toCharArray());
                allRovers.add(roverToList);
            } catch (IllegalArgumentException nfe) {
                System.out.println(" check rovers position ");
                throw new IllegalArgumentException("");
            }
        }
        roverCount = allRovers.size();

        return allRovers;
    }

    public int getRoverCount() {
        return roverCount;
    }

    public void startOperations(ArrayList<Rover> rovers) {
        try {
            for (Rover singleRover : rovers) {
                singleRover.executeCommandList(plateau);

                singleRover.setMessage(singleRover.getMessage()
                        .concat("  I stopped at:" + singleRover.getRoverLocation().getX() + "," +
                                singleRover.getRoverLocation().getY()));
                plateau.addObstacle(singleRover.getRoverLocation());
                System.out.println("  " + singleRover.getMessage());
            }
        } catch (NullPointerException npe) {
            System.out.println("ERROR: Something went VERY WRONG. Operation Aborted. NASA wont hire me :( ");
        } catch (UnsupportedOperationException uoe) {
            System.out.println("ERROR: Something went VERY WRONG. Operation Aborted. NASA wont hire me :( ");
        }
    }

    public void saveFinalReport() {
        File reportFile = new File("report.txt");
        FileWriter myWriter = null;
        StringBuilder sb = new StringBuilder();
        try {
            ArrayList<Coordinates> roverReport = plateau.getObstacles();
            for (Coordinates roverCoordinates : roverReport)
                if (reportFile.createNewFile()) {
                    System.out.println("File created: " + reportFile.getName());
                    myWriter = new FileWriter(reportFile);
                    sb.append("Rover report: \n ");
                } else {
                    myWriter = new FileWriter(reportFile, true);
                }

            Stream<Coordinates> streamList = convertListToStream(roverReport);

            streamList.forEach(coordinates -> sb.append("" + coordinates.toString()).append(" \n "));
            String reportContent = sb.toString();
            myWriter.write(reportContent);
            myWriter.close();
            System.out.println("Successfully wrote the report for this mission." + reportContent + "\n ");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the report.");
        } catch (NullPointerException npe) {
            System.out.println("An error occurred while writing the report.");
        }
    }
}

