/*

	Author: Rytis Karpuška
		rytis@elektromotus.lt

*/

#include <iostream>

#include <stdio.h>
#include <stdlib.h>
#include <fstream>

#include "common.h"
#include "suffixTree.h"
#include "suffixTreeTest.h"

using namespace std;


int suffixTreeTest()
{
	SuffixTree t;
	string test;
	//char c;
	//
	//for(int i = 0; i < 1000000; i++){
	//	c = random() % 10 + '0';
	//	test.append(1, c);
	//}
	//test.append(1, 'e');

	ifstream data;
	data.open("output");
	getline(data, test);
	

	cout << "str: " << test << endl;
//	t.addString("abcabxabcd");
//	t.addString("banana$");
//	t.addString("516571614e");
	t.addString(test);
//	t.addString("1231241e");
//	t.debugPrintEdges(NULL, 0);
//	printf("pasikartojimai: \n");
	t.getSeqs();
	

	

	return SUCC;
}


