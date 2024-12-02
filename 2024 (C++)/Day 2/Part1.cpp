
#include <iostream>
#include <fstream>
#include <vector>

int main() {
    std::ifstream inputFile{"../input.txt"};

    std::vector<std::vector<int>> input = {};

    std::string line;
    while (std::getline(inputFile, line)) {
        std::vector<int> report = {};

        unsigned long delimiterPosition = 0;
        while (delimiterPosition != std::string::npos) {
            delimiterPosition = line.find(' ');
            report.push_back(std::stoi(line.substr(0, delimiterPosition)));
            line.erase(0, delimiterPosition + 1);
        }

        input.push_back(report);
    }

    int result = 0;

    for (auto report : input) {
        bool isIncreasing = false;
        bool isDecreasing = false;
        bool isUnsafe = false;

        for (int i = 0; i < report.size() - 1; i++) {
            if (report[i] < report[i + 1]) isIncreasing = true;
            if (report[i] > report[i + 1]) isDecreasing = true;

            if (report[i] == report[i + 1] ||
                abs(report[i] - report[i + 1]) > 3 ||
                (isIncreasing && isDecreasing)) {
                isUnsafe = true;
                break;
            }
        }

        if (isUnsafe) continue;
        result += 1;
    }

    std::cout << "Result: " << result << std::endl;

    return 0;
}