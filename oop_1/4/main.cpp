#include <iostream>
#include "character.h"
#include "digit.h"
#include "number.h"
#include "message.h"
#include <windows.h>

const char *filename = "digits";

using namespace std;

//Digit digit(filename);


int main() {
	/*
	for (int i = 0; i < 10; i++) {
		lcd.setNumber(i);
		cout << lcd.getVisual();
	}
	*/
	Character *display[3];
	display[0] = new Digit(filename);
	display[1] = new Number(filename, true);
    display[2] = new Message(filename);

	int nr = -1;
	cin >> nr;
	while (nr != -1) {
	    int index = nr < 10 ? 0 : nr < 100 ? 1 : 2;
	    display[index]->set(nr);
	    system("cls");
	    std::cout << display[index]->getDisplay();
		cin >> nr;
	}


	return 0;
}
