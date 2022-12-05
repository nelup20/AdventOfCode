input = File.open(File.expand_path('Day 1/input.txt'))
            .readlines
            .map(&:to_i)
            .chunk(&:positive?)
            .map(&:last)

top_3_calories = input.map { |elf| elf.reduce(:+) }
                      .sort
                      .last(3)
                      .reduce(:+)

puts "Result: #{top_3_calories}"
