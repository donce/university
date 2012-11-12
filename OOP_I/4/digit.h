#ifndef LCD_H
#define LCD_H

#include <string>
#include <iostream>
#include "character.h"

class Digit: public Character {
protected:
	int leds;
	int values;

	bool **digitTable;

	std::string screen;

    bool set(int number);
public:
	Digit(std::string file);
	Digit() {Digit("digits");};
};

#endif
