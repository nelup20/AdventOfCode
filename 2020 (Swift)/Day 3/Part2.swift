
import Foundation

let inputDataPath = "./Day 3/input"
let bundlePath = Bundle.main.path(forResource: inputDataPath, ofType: "txt")
let initialInputData = try String(contentsOfFile: bundlePath!).components(separatedBy: "\n")

let slopes = [
    (row: 1, column: 1),
    (row: 1, column: 3),
    (row: 1, column: 5),
    (row: 1, column: 7),
    (row: 2, column: 1)
]

var finalResult = 1

var inputData: [String] = initialInputData.filter({ !$0.isEmpty })
                                          .map({ String(repeating: $0, count: 73) }) // (322 rows * 7) / 31 initial columns


for slope in slopes {
    var (row, column, slopeResult) = (0, 0, 0)

    while (row < inputData.count - 1) {
        row += slope.row
        column += slope.column

        let line = inputData[row]
        let char = line[line.index(line.startIndex, offsetBy: column)]
        
        if (char == "#") {
            slopeResult += 1
        }
    }

    finalResult *= slopeResult
}

print("Result: \(finalResult)")