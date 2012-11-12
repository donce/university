#include "Digit.h"
#include <fstream>
#include <windows.h>
/*
 0
1 2
 3
4 5
 6
*/

Digit::Digit(std::string file) {
    std::cout << "Atidarau: " << file << std::endl;
	std::ifstream f(file.c_str());
	if (!f)
        std::cout << "Nepavyko" << std::endl;

	f >> leds >> values;
	std::cout << leds << std::endl;

	digitTable = new bool*[values];
	for (int i = 0; i < values; i++)
		digitTable[i] = new bool[leds];

	//read lights
	for (int i = 0; i < values; i++)
		for (int j = 0; j < leds; j++) {
			char c;
			f >> c;
			digitTable[i][j] = (c == '1');
		}

	//read display
	f.ignore();
	char c;
	while (f.get(c)) {
		screen += c;
		std::cout << "adds: " << c << std::endl;
	}
    std::cout << screen;
	f.close();
}

bool Digit::set(int number) {
    std::cout << "set " << number << std::endl;
    std::cout << screen << std::endl;
    display = screen;
	for (int i = 0; i < display.length(); i++) {
		int value = display[i] - '0';
		if (value >= 0 && value < leds)
			display[i] = digitTable[number][value] ? '#' : '.';
	}
	std::cout << "end" << std::endl;
	std::cout << "setted: " << display << std::endl;
	return 0;
}

