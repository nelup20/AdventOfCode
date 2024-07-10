
import Foundation

let inputDataPath = "./Day 3/input"
let bundlePath = Bundle.main.path(forResource: inputDataPath, ofType: "txt")
let initialInputData = try String(contentsOfFile: bundlePath!).components(separatedBy: "\n")

var (row, column, result) = (0, 0, 0)

var inputData: [String] = []

for initialLine in initialInputData {
    if !initialLine.isEmpty {
        inputData.append(String(repeating: initialLine, count: initialLine.count + 1))
    }
}

while (row < inputData.count - 1) {
    row += 1
    column += 3

    let line = inputData[row]
    let char = line[line.index(line.startIndex, offsetBy: column)]
    
    if (char == "#") {
        result += 1
    }
}

print("Result: \(result)")