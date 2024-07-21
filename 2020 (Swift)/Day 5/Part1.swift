
import Foundation

let inputDataPath = "./Day 5/input"
let bundlePath = Bundle.main.path(forResource: inputDataPath, ofType: "txt")
let inputData = try String(contentsOfFile: bundlePath!).components(separatedBy: "\n")

var result = 0

for boardingPass in inputData {
    var column = 0
    var row = 0
    
    var lowerRow = 0
    var upperRow = 127
    var lowerColumn = 0
    var upperColumn = 7

    for char in boardingPass {
        switch char {
            case "F":
                if (upperRow - lowerRow > 1) {
                    upperRow = getNewBound(lowerBound: lowerRow, upperBound: upperRow, rounding: floor)
                } else {
                    row = lowerRow
                }
            case "B":
                if (upperRow - lowerRow > 1) {
                    lowerRow = getNewBound(lowerBound: lowerRow, upperBound: upperRow, rounding: ceil)
                } else {
                    row = upperRow
                }
            case "L":
                if (upperColumn - lowerColumn > 1) {
                    upperColumn = getNewBound(lowerBound: lowerColumn, upperBound: upperColumn, rounding: floor)
                } else {
                    column = lowerColumn
                }
            case "R":
                if (upperColumn - lowerColumn > 1) {
                    lowerColumn = getNewBound(lowerBound: lowerColumn, upperBound: upperColumn, rounding: ceil)
                } else {
                    column = upperColumn
                }
            default:
                break
        }
    }

    let seatId = (row * 8) + column

    if seatId > result {
        result = seatId
    }
}

func getNewBound(lowerBound: Int, upperBound: Int, rounding: (Double) -> Double) -> Int {
    return lowerBound + Int(rounding(Double(upperBound - lowerBound) / 2))
}

print("Result: \(result)")