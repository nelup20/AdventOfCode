
#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

int main() {
    std::ifstream inputFile{"../input.txt"};
    
    std::vector<int> leftSide = {};
    std::vector<int> rightSide = {};

    std::string line;
    while (std::getline(inputFile, line)) {
        unsigned long delimiterPosition = line.find(' ');
        leftSide.push_back(std::stoi(line.substr(0, delimiterPosition)));
        rightSide.push_back(std::stoi(line.substr(delimiterPosition, line.size() - 1)));
    }

    std::sort(leftSide.begin(), leftSide.end());
    std::sort(rightSide.begin(), rightSide.end());

    int result = 0;

    for (int i = 0; i < leftSide.size(); i++) {
        result += std::abs((rightSide[i] - leftSide[i]));
    }

    std::cout << "Result: " << result << std::endl;

    return 0;
}