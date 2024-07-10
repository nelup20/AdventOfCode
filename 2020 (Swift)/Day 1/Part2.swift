
import Foundation

let inputDataPath = "./Day 1/input"
let bundlePath = Bundle.main.path(forResource: inputDataPath, ofType: "txt")
var inputData = try String(contentsOfFile: bundlePath!).components(separatedBy: "\n")
inputData.sort()

// n^3, Hash table & (2020 - a - b = c) to reduce complexity to n^2?
for i in inputData.indices {
    for j in inputData[(i + 1)...].indices {
        for k in inputData[(j + 1)...].indices {
            if (inputData[i].isEmpty || inputData[j].isEmpty || inputData[k].isEmpty) {
                continue
            }

            var firstNum = Int(inputData[i])!
            var secondNum = Int(inputData[j])!
            var thirdNum = Int(inputData[k])!

            if (firstNum + secondNum + thirdNum == 2020) {
                print("Result: \(firstNum * secondNum * thirdNum)")
            }
        }
    }
}