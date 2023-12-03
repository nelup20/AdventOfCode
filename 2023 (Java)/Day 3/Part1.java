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

            int rowMax = input.size();
            int columnMax = input.get(0).size();

            for (int row = 0; row < rowMax; row++) {
                String currentNumber = "";
                boolean currentNumberIsValid = false;

                for (int column = 0; column < columnMax; column++) {
                    String currentCharacter = input.get(row).get(column);

                    if (NUMBER_REGEX.matcher(currentCharacter).matches()) {
                        currentNumber += currentCharacter;

                        if (isAdjacentToSymbol(input, row, column)) currentNumberIsValid = true;
                        if (column == columnMax - 1 && currentNumberIsValid) partNumbers.add(Integer.parseInt(currentNumber));
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
        for (int relativeRow = -1; relativeRow <= 1; relativeRow++) {
            for (int relativeColumn = -1; relativeColumn <= 1; relativeColumn++) {
                try {
                    String adjacentCharacter = input.get(row + relativeRow).get(col + relativeColumn);
                    if (SYMBOL_REGEX.matcher(adjacentCharacter).matches()) return true;
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }

        return false;
    }
}
