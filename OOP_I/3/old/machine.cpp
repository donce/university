#include "machine.h"

Machine::Machine() {

}

void Machine::sendInput(string buffer) {
	input += buffer;
}

string Machine::recvOutput() {
	string o = output;
	output = "";
	return o;
}
