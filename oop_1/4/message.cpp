#include "message.h"

#include <vector>
#include <windows.h>
#include <iostream>

void Message::print(int number) {
    std::vector<int> digits;
    while (number) {
        digits.push_back(number % 10);
        number /= 10;
    }

    std::vector<int> screen;
    screen.resize(digits.size(), 10);

    int now = 0;
    for (int i = digits.size()-1; i >= -int(digits.size()); i--) {
        for (int j = 0; j < screen.size()-1; j++)
            screen[j] = screen[j+1];
        int now = i >= 0 ? digits[i] : 10;
        screen[screen.size()-1] = now;
        system("cls");
        Number::set(screen);
        std::cout << Character::getDisplay();
        Sleep(200);
    }
}
