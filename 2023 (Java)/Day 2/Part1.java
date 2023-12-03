import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {
    private final static Map<String, Integer> COLOR_TO_MAX = Map.ofEntries(
            Map.entry("red", 12),
            Map.entry("green", 13),
            Map.entry("blue", 14)
    );

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> possibleGameIds = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("input.txt"))) {
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(": ");
                int gameId = Integer.parseInt(line[0].split(" ")[1]);

                String[] gameSets = line[1].split(";");
                if (Arrays.stream(gameSets).allMatch(Part1::isSetPossible)) {
                    possibleGameIds.add(gameId);
                }
            }
        }

        System.out.println("Result: " + possibleGameIds.stream().reduce(Integer::sum).orElseThrow());
    }

    private static boolean isSetPossible(String gameSet) {
        for (String cube : gameSet.strip().split(", ")) {
            int numberOfCubes = Integer.parseInt(cube.split(" ")[0]);
            String cubeColor = cube.split(" ")[1];

            if (numberOfCubes > COLOR_TO_MAX.get(cubeColor)) return false;
        }

        return true;
    }
}
