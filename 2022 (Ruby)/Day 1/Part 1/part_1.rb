input = File.open(File.expand_path('Day 1/input.txt'))
            .readlines
            .map(&:to_i)
            .chunk(&:positive?)
            .map(&:last)

most_calories = input.map { |elf| elf.reduce(:+) }
                     .max

puts "Result: #{most_calories}"
