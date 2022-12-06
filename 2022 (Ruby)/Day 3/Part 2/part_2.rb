input = File.open(File.expand_path('Day 3/input.txt'))
            .readlines
            .map(&:chomp)
            .each_slice(3)
            .to_a
            .map { |group| group.map(&:chars) }

item_types = [*('a'..'z'), *('A'..'Z')]

total_priority = 0

input.each do |group|
  common_type = (group[0] & group[1] & group[2])[0]
  total_priority += (item_types.find_index(common_type) + 1)
end

puts "Result: #{total_priority}"

# one-liner
# total_priority = input.reduce(0) { |sum, group| sum + (item_types.find_index((group[0] & group[1] & group[2])[0]) + 1) }
