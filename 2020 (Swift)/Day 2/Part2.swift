
import Foundation

let inputDataPath = "./Day 2/input"
let bundlePath = Bundle.main.path(forResource: inputDataPath, ofType: "txt")
let inputData = try String(contentsOfFile: bundlePath!).components(separatedBy: "\n")

var result = 0

inputData.forEach({
    if (!$0.isEmpty) { 
        let line = $0.components(separatedBy: ": ")
        let passwordPolicy = line[0]
        let password = line[1]
        
        let policyPositions = passwordPolicy.components(separatedBy: " ")[0]
        let char = Character(passwordPolicy.components(separatedBy: " ")[1])
        let firstPosition = Int(policyPositions.components(separatedBy: "-")[0])! - 1
        let secondPosition = Int(policyPositions.components(separatedBy: "-")[1])! - 1

        let passwordFirstChar = password[password.index(password.startIndex, offsetBy: firstPosition)]
        let passwordSecondChar = password[password.index(password.startIndex, offsetBy: secondPosition)]

        if ((passwordFirstChar == char && passwordSecondChar != char) ||
            (passwordSecondChar == char && passwordFirstChar != char)) {
                result += 1
            } 
     }
})

print("Result: \(result)")