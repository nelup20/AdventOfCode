
import Foundation

let inputDataPath = "./Day 1/input"
let bundlePath = Bundle.main.path(forResource: inputDataPath, ofType: "txt")
let inputData = try String(contentsOfFile: bundlePath!).components(separatedBy: "\n")

for i in inputData.indices {
    for j in inputData[i...].indices {
        if (i == j || inputData[i].isEmpty || inputData[j].isEmpty) {
            continue
        }

        var firstNum = Int(inputData[i])!
        var secondNum = Int(inputData[j])!

        if (firstNum + secondNum == 2020) {
            print("Result: \(firstNum * secondNum)")
        }
    }
}