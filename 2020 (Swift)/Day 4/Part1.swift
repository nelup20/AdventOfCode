
import Foundation

let inputDataPath = "./Day 4/input"
let bundlePath = Bundle.main.path(forResource: inputDataPath, ofType: "txt")
let inputData = try String(contentsOfFile: bundlePath!).components(separatedBy: "\n\n")

let requiredFields = ["byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"]

var result = 0

for passport in inputData {
    var isValid = true

    for field in requiredFields {
        if !passport.contains(field) {
            isValid = false
            break
        }
    }

    if isValid {
        result += 1
    }
}

print("Result: \(result)")