
import Foundation

let inputDataPath = "./Day 4/input"
let bundlePath = Bundle.main.path(forResource: inputDataPath, ofType: "txt")
let inputData = try String(contentsOfFile: bundlePath!).components(separatedBy: "\n\n")

let requiredFields = ["byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"]
let hclRegex = try Regex("#[a-f0-9]{6}")
let eclRegex = try Regex("amb|blu|brn|gry|grn|hzl|oth")
let pidRegex = try Regex("[0-9]{9}")

var result = 0

let fieldTests: [String: (String) throws -> Bool] = [
    "byr": { isInRange(Int($0) ?? 0, 1920, 2002) },
    "iyr": { isInRange(Int($0) ?? 0, 2010, 2020) }, 
    "eyr": { isInRange(Int($0) ?? 0, 2020, 2030) },
    "hcl": { try hclRegex.wholeMatch(in: $0) != nil },
    "ecl": { try eclRegex.wholeMatch(in: $0) != nil },
    "pid": { try pidRegex.wholeMatch(in: $0) != nil },
    "hgt": { isHeightValid($0) && isHeightInRange($0) }
]

func isInRange(_ value: Int, _ lowerBound: Int, _ upperBound: Int) -> Bool {
    return value >= lowerBound && value <= upperBound
}

func isHeightValid(_ value: String) -> Bool {
    return value.hasSuffix("cm") || value.hasSuffix("in")
}

func isHeightInRange(_ value: String) -> Bool {
        let lowerBound = value.hasSuffix("cm") ? 150 : 59
        let upperBound = value.hasSuffix("cm") ? 193 : 76

        var fieldValue = value
        fieldValue.removeLast(2)

        return isInRange(Int(fieldValue) ?? 0, lowerBound, upperBound)
}


for passport in inputData {
    var isValid = true

    for field in requiredFields {
        if !passport.contains(field) {
            isValid = false
            break
        }
    }

    if isValid {
        var passportFields = passport.components(separatedBy: CharacterSet(charactersIn: " \n"))

        if passportFields.last!.isEmpty {
            passportFields.removeLast()
        }

        try passportFields.forEach({
            let fieldName = $0.components(separatedBy: ":")[0]
            let fieldValue = $0.components(separatedBy: ":")[1]

            if try (fieldName != "cid" && !fieldTests[fieldName]!(fieldValue)) {
                isValid = false
            }
        })
    }

    if isValid {
        result += 1
    }
}

print("Result: \(result)")
