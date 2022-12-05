hand_shapes = {
  'rock' => {
    'enemy_letter' => 'A',
    'player_score' => 1,
    'wins_against' => 'scissors'
  },
  'paper' => {
    'enemy_letter' => 'B',
    'player_score' => 2,
    'wins_against' => 'rock'
  },
  'scissors' => {
    'enemy_letter' => 'C',
    'player_score' => 3,
    'wins_against' => 'paper'
  }
}

final_score = 0

rounds = File.open(File.expand_path('Day 2/input.txt'))
             .readlines
             .map(&:split)

rounds.each do |round|
  enemy_hand = hand_shapes.keys.find { |k| hand_shapes[k]['enemy_letter'] == round[0] }

  case round[1]
  when 'X'
    final_score += hand_shapes[hand_shapes[enemy_hand]['wins_against']]['player_score']
  when 'Y'
    final_score += (3 + hand_shapes[enemy_hand]['player_score'])
  else
    winning_hand = hand_shapes.keys.find { |k| hand_shapes[k]['wins_against'] == enemy_hand }
    final_score += (6 + hand_shapes[winning_hand]['player_score'])
  end
end

puts "Result: #{final_score}"

