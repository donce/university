#ifndef MESSAGE_H
#define MESSAGE_H

#include "number.h"

class Message: public Number {
public:
    void print(int number);
    Message(std::string file) : Number(file, true) {};
};

#endif
