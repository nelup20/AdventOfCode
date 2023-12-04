import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Double> points = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("input.txt"))) {
            while (scanner.hasNext()) {
                String[] numbers = scanner.nextLine().split(":")[1].split("[|]");

                Set<Integer> winningNumbers = getNumbersAsSet(numbers[0]);
                Set<Integer> potentialNumbers = getNumbersAsSet(numbers[1]);

                long numberOfWinningCards = potentialNumbers.stream()
                                                            .filter(winningNumbers::contains)
                                                            .count();

                if (numberOfWinningCards > 0) points.add(Math.pow(2, numberOfWinningCards - 1));
            }
        }

        System.out.println("Result: " + points.stream().reduce(Double::sum).orElseThrow());
    }

    private static Set<Integer> getNumbersAsSet(String numbers) {
        return Arrays.stream(numbers.trim().split(" "))
                     .filter(number -> !number.isEmpty())
                     .map(Integer::parseInt)
                     .collect(Collectors.toSet());
    }
}

