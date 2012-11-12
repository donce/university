#include "character.h"


Character::Character() {
    display = '1';
}

bool Character::set(int number) {
    if (0 <= number && number <= 10) {
        if (number == 10)
            display = " ";
        else
            display = number + '0';
        return 0;
    }
    return 1;
}

std::string Character::getDisplay() {
    return display;
}
