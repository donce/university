#ifndef NUMBER_H
#define NUMBER_H

#include "digit.h"
#include <vector>

class Number: public Digit {
    bool digit;
    bool set(int number);
protected:
    void set(std::vector<int> number);
public:
    Number(std::string file, bool _digit) : Digit(file) {digit = _digit;};
};

#endif
