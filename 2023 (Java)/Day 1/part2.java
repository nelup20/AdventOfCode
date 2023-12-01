import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class part2 {
    private static final Pattern NUMBER_REGEX = Pattern.compile("\\d");
    private static final List<String> NUMBER_WORDS = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    private static final Map<String, Integer> WORD_TO_INT = Map.ofEntries(
            Map.entry("one", 1),
            Map.entry("two", 2),
            Map.entry("three", 3),
            Map.entry("four", 4),
            Map.entry("five", 5),
            Map.entry("six", 6),
            Map.entry("seven", 7),
            Map.entry("eight", 8),
            Map.entry("nine", 9)
    );

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> numbers = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("input.txt"))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                numbers.add(Integer.parseInt(getNumber(line)));
            }
        }

        System.out.println("Result: " + numbers.stream().reduce(Integer::sum).get());
    }

    private static String getNumber(String line) {
        List<Integer> digitIndexes = new ArrayList<>();

        for (String numberWord : NUMBER_WORDS) {
            if (line.contains(numberWord)) {
                digitIndexes.add(line.indexOf(numberWord));
                digitIndexes.add(line.lastIndexOf(numberWord));
            }
        }

        String[] characters = line.split("");
        for (int i = 0; i < line.length(); i++) {
            if (NUMBER_REGEX.matcher(characters[i]).matches()) {
                digitIndexes.add(i);
            }
        }

        digitIndexes.sort(Integer::compareTo);

        int firstDigit = getDigitFromString(characters, digitIndexes.get(0));
        int lastDigit = getDigitFromString(characters, digitIndexes.get(digitIndexes.size() - 1));

        return firstDigit + "" + lastDigit;
    }

    private static int getDigitFromString(String[] characters, int digitStartIndex) {
        if (NUMBER_REGEX.matcher(characters[digitStartIndex]).matches()) {
            return Integer.parseInt(characters[digitStartIndex]);
        } else {
            String numberWord = "";

            for (int i = digitStartIndex; i <= characters.length; i++) {
                if (NUMBER_WORDS.contains(numberWord)) {
                    return WORD_TO_INT.get(numberWord);
                }

                numberWord += characters[i];
            }
        }

        return -1;
    }
}
