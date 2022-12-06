input = File.open(File.expand_path('Day 4/input.txt'))
            .readlines
            .map { |line| line.chomp.split(',').map { |assignment| assignment.split('-').map(&:to_i) } }

def fully_contains_other?(assignment)
  (first_assignment, second_assignment) = assignment

  first_assignment[0] >= second_assignment[0] && first_assignment[1] <= second_assignment[1] ||
    first_assignment[0] <= second_assignment[0] && first_assignment[1] >= second_assignment[1]
end

result = input.count { |assignment| fully_contains_other?(assignment) }

puts "Result: #{result}"
