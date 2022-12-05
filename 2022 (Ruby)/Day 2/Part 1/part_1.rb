hand_shapes = {
  'rock' => {
    'enemy_letter' => 'A',
    'player_letter' => 'X',
    'player_score' => 1,
    'wins_against' => 'scissors'
  },
  'paper' => {
    'enemy_letter' => 'B',
    'player_letter' => 'Y',
    'player_score' => 2,
    'wins_against' => 'rock'
  },
  'scissors' => {
    'enemy_letter' => 'C',
    'player_letter' => 'Z',
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
  player_hand = hand_shapes.keys.find { |k| hand_shapes[k]['player_letter'] == round[1] }

  final_score += hand_shapes[player_hand]['player_score']

  final_score += 6 if hand_shapes[player_hand]['wins_against'] == enemy_hand
  final_score += 3 if enemy_hand == player_hand
end

puts "Result: #{final_score}"
