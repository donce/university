#ifndef CHARACTER_H
#define CHARACTER_H

#include <string>

class Character {
protected:
    std::string display;
public:
    Character();
    virtual bool set(int number);
    std::string getDisplay();
};

#endif
