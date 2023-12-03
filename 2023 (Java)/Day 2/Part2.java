import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> gamePowers = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("input.txt"))) {
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(": ");
                gamePowers.add(getGamePower(line[1]));
            }
        }

        System.out.println("Result: " + gamePowers.stream().reduce(Integer::sum).orElseThrow());
    }

    private static int getGamePower(String gameSets) {
        Map<String, Integer> minimumNumberOfCubes = new HashMap<>(
                Map.ofEntries(
                        Map.entry("red", 0),
                        Map.entry("green", 0),
                        Map.entry("blue", 0)
                )
        );

        for (String gameSet : gameSets.split(";")) {
            for (String cube : gameSet.strip().split(", ")) {
                int numberOfCubes = Integer.parseInt(cube.split(" ")[0]);
                String cubeColor = cube.split(" ")[1];

                if (numberOfCubes > minimumNumberOfCubes.get(cubeColor)) {
                    minimumNumberOfCubes.put(cubeColor, numberOfCubes);
                }
            }
        }

        return minimumNumberOfCubes.values().stream()
                                            .reduce(Math::multiplyExact)
                                            .orElseThrow();
    }
}
