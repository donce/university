#include "number.h"


const int DIGIT_WIDTH = 5;
const int DIGIT_HEIGHT = 9;


void Number::set(std::vector<int> number) {
    Character *c;
    //if (digit)
        c = new Digit[number.size()];
    //else
        //c = new Character[number.size()];

    for (int i = 0; i < number.size(); i++) {
        std::cout << "a" << number[i] << std::endl;
        c[i].set(0);
        std::cout << c[i].getDisplay() << std::endl;
        std::cout << number[i] << std::endl;
    }
        //digits.push_back(Digit::getVisual(number[i]));
    display = "";
    for (int line = 0; line < DIGIT_HEIGHT; line++) {
        for (int digit = 0; digit < number.size(); digit++) {
            display.append(c[digit].getDisplay().substr((DIGIT_WIDTH+1) * line, DIGIT_WIDTH));
            if (digit + 1 != number.size())
                display.append(" ");
        }
        display.append("\n");
    }
}

bool Number::set(int number) {
    std::vector<int> rdigits;
    while (number) {
        rdigits.push_back(number % 10);
        number /= 10;
    }
    std::vector<int> digits;
    for (int i = rdigits.size()-1; i >= 0; i--)
        digits.push_back(rdigits[i]);
    set(digits);
    return 0;
}
