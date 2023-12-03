import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Part1 {
    public static Pattern numberRegex = Pattern.compile("\\d");

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> numbers = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("input.txt"))) {
            while (scanner.hasNext()) {
                ArrayList<String> lineChars = new ArrayList<>(List.of(scanner.nextLine().split("")));

                int firstNumber = findFirstInteger(lineChars);

                Collections.reverse(lineChars);
                int lastNumber = findFirstInteger(lineChars);

                numbers.add(Integer.parseInt(firstNumber + "" + lastNumber));
            }
        }

        System.out.println("Result: " + numbers.stream().reduce(Integer::sum).get());
    }

    private static int findFirstInteger(List<String> list) {
        return list.stream()
                   .filter(character -> numberRegex.matcher(character).matches())
                   .mapToInt(Integer::parseInt)
                   .findFirst()
                   .orElseThrow();
    }
}
