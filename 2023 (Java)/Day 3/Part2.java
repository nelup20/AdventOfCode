import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Part2 {
    private static final Pattern NUMBER_REGEX = Pattern.compile("\\d");
    private static int rowMax = 0;
    private static int columnMax = 0;

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> gearRatios = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("input.txt"))) {
            List<List<String>> input = new ArrayList<>();

            while (scanner.hasNext()) {
                input.add(List.of(scanner.nextLine().split("")));
            }

            rowMax = input.size();
            columnMax = input.get(0).size();

            for (int row = 0; row < rowMax; row++) {
                for (int column = 0; column < columnMax; column++) {
                    String currentCharacter = getCharacter(input, row, column);

                    if (currentCharacter.equals("*") && isGear(input, row, column)) {
                        gearRatios.add(getGearRatio(input, row, column));
                    }
                }
            }
        }

        System.out.println("Result: " + gearRatios.stream().reduce(Integer::sum).orElseThrow());
    }


    private static boolean isDigit(String character) {
        return NUMBER_REGEX.matcher(character).matches();
    }

    private static boolean isGear(List<List<String>> input, int row, int column) {
        int adjacentPartNumbers = 0;
        List<List<Integer>> parsedNumbers = new ArrayList<>();

        for (int relativeRow = -1; relativeRow <= 1; relativeRow++) {
            for (int relativeColumn = -1; relativeColumn <= 1; relativeColumn++) {
                try {
                    if (relativeRow == 0 && relativeColumn == 0) continue;

                    int currentRow = row + relativeRow;
                    int currentColumn = column + relativeColumn;

                    String adjacentCharacter = getCharacter(input, currentRow, currentColumn);
                    if (isDigit(adjacentCharacter)) {
                        if (hasBeenParsed(parsedNumbers, currentRow, currentColumn)) {
                            parsedNumbers.add(List.of(currentRow, currentColumn));
                            continue;
                        }

                        adjacentPartNumbers++;
                        parsedNumbers.add(List.of(currentRow, currentColumn));
                    }

                    if (adjacentPartNumbers > 2) return false;
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }

        return adjacentPartNumbers == 2;
    }

    private static String getCharacter(List<List<String>> input, int row, int column) {
        return input.get(row).get(column);
    }

    private static boolean hasBeenParsed(List<List<Integer>> parsedNumbers, int row, int column) {
        return parsedNumbers.stream().anyMatch(number -> number.get(0) == row && Math.abs(number.get(1) - column) == 1);
    }

    private static boolean isColumnWithinLimits(int column) {
        return column >= 0 && column < columnMax;
    }

    private static Integer getGearRatio(List<List<String>> input, int row, int column) {
        int result = 1;
        List<List<Integer>> parsedNumbers = new ArrayList<>();

        for (int relativeRow = -1; relativeRow <= 1; relativeRow++) {
            for (int relativeColumn = -1; relativeColumn <= 1; relativeColumn++) {
                try {
                    int currentRow = row + relativeRow;
                    int currentColumn = column + relativeColumn;

                    String adjacentCharacter = getCharacter(input, currentRow, currentColumn);

                    if (isDigit(adjacentCharacter) && !hasBeenParsed(parsedNumbers, currentRow, currentColumn)) {
                        String currentNumber = "";

                        int currentDigitColumn = currentColumn;
                        while (isColumnWithinLimits(currentDigitColumn) &&
                               isDigit(adjacentCharacter = getCharacter(input, currentRow, currentDigitColumn))) {
                            currentNumber += adjacentCharacter;
                            parsedNumbers.add(List.of(currentRow, currentDigitColumn));
                            currentDigitColumn++;
                        }

                        currentDigitColumn = currentColumn - 1;
                        while (isColumnWithinLimits(currentDigitColumn) &&
                               isDigit(adjacentCharacter = getCharacter(input, currentRow, currentDigitColumn))) {
                            currentNumber = adjacentCharacter + currentNumber;
                            parsedNumbers.add(List.of(currentRow, currentDigitColumn));
                            currentDigitColumn--;
                        }

                        result *= Integer.parseInt(currentNumber);
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }

        return result;
    }
}
