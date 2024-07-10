
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
        
        let policyRange = passwordPolicy.components(separatedBy: " ")[0]
        let char = Character(passwordPolicy.components(separatedBy: " ")[1])
        let minCount = Int(policyRange.components(separatedBy: "-")[0])!
        let maxCount = Int(policyRange.components(separatedBy: "-")[1])!

        let count = password.reduce(0) { $1 == char ? $0 + 1 : $0 }
        
        if (count >= minCount && count <= maxCount) {
            result += 1
        }
     }
})

print("Result: \(result)")