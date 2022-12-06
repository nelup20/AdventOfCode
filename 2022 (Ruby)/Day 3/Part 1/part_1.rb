input = File.open(File.expand_path('Day 3/input.txt'))
            .readlines
            .map { |line| line.chomp.chars.each_slice(line.size / 2).to_a }

item_types = [*('a'..'z'), *('A'..'Z')]

total_priority = 0

input.each do |rucksack|
  common_type = (rucksack[0] & rucksack[1])[0]
  total_priority += (item_types.find_index(common_type) + 1)
end

puts "Result: #{total_priority}"

# one-liner
# total_priority = input.reduce(0) { |sum, rucksack| sum + (item_types.find_index((rucksack[0] & rucksack[1])[0]) + 1) }
