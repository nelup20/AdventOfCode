
#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_map>
#include <numeric>

int main() {
    std::ifstream inputFile{"../input.txt"};
    
    std::vector<int> leftSide = {};
    std::unordered_map<int, int> appearances = {};

    std::string line;
    while (std::getline(inputFile, line)) {
        unsigned long delimiterPosition = line.find(' ');
        int leftNumber = std::stoi(line.substr(0, delimiterPosition));
        int rightNumber = std::stoi(line.substr(delimiterPosition, line.size() - 1));
        
        if (!appearances.contains(rightNumber)) {
            appearances[rightNumber] = 1;
        } else {
            appearances[rightNumber] += 1;
        }
        
        leftSide.push_back(leftNumber);
    }

    int result = std::reduce(leftSide.begin(), leftSide.end(), 0, [&appearances](int accumulator, int leftNumber) {
        return (leftNumber * appearances[leftNumber]) + accumulator;
    });

    std::cout << "Result: " << result << std::endl;

    return 0;
}