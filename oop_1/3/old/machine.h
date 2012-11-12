#ifndef MACHINE_H
#define MACHINE_H

#include <string>

using namespace std;

class Machine {
private:
	string output;
	string input;
public:
	Machine();
	
	void sendInput(string buffer);
	string recvOutput();
};

#endif
