import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Part1 {
    private static final Pattern SYMBOL_REGEX = Pattern.compile("[^\\w.]");
    private static final Pattern NUMBER_REGEX = Pattern.compile("\\d");

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> partNumbers = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("input.txt"))) {
            List<List<String>> input = new ArrayList<>();

            while (scanner.hasNext()) {
                input.add(List.of(scanner.nextLine().split("")));
            }

            for (int row = 0; row < input.size(); row++) {
                String currentNumber = "";
                boolean currentNumberIsValid = false;

                for (int col = 0; col < 140; col++) {
                    String currentCharacter = input.get(row).get(col);

                    if (NUMBER_REGEX.matcher(currentCharacter).matches()) {
                        currentNumber += currentCharacter;

                        if (isAdjacentToSymbol(input, row, col)) currentNumberIsValid = true;
                        if (col == 139 && currentNumberIsValid) partNumbers.add(Integer.parseInt(currentNumber));
                    } else {
                        if (currentNumberIsValid) partNumbers.add(Integer.parseInt(currentNumber));

                        currentNumber = "";
                        currentNumberIsValid = false;
                    }
                }
            }
        }

        System.out.println("Result: " + partNumbers.stream().reduce(Integer::sum).orElseThrow());
    }

    private static boolean isAdjacentToSymbol(List<List<String>> input, int row, int col) {
        int positionsChecked = 0;
        int relativeRow = -1;
        int relativeCol = -1;

        while (positionsChecked <= 8) {
            try {
                if (SYMBOL_REGEX.matcher(input.get(row + relativeRow).get(col + relativeCol)).matches()) return true;
            } catch (IndexOutOfBoundsException ignored) {
            } finally {
                positionsChecked++;

                if (relativeCol >= 1) {
                    relativeRow++;
                    relativeCol = -1;
                } else {
                    relativeCol++;
                }
            }
        }

        return false;
    }
}
