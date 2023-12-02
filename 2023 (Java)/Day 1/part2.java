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
        Map<Integer, Integer> digits = new HashMap<>();

        for (String numberWord : NUMBER_WORDS) {
            if (line.contains(numberWord)) {
                digits.put(line.indexOf(numberWord), WORD_TO_INT.get(numberWord));
                digits.put(line.lastIndexOf(numberWord), WORD_TO_INT.get(numberWord));
            }
        }

        String[] characters = line.split("");
        for (int i = 0; i < line.length(); i++) {
            if (NUMBER_REGEX.matcher(characters[i]).matches()) {
                digits.put(i, Integer.parseInt(characters[i]));
            }
        }

        List<Integer> indexes = digits.keySet().stream().sorted().toList();

        return digits.get(indexes.get(0)) + "" + digits.get(indexes.get(indexes.size() - 1));
    }
}
