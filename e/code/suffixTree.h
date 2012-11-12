/*

	Author: Rytis Karpuška
		rytis@elektromotus.lt

*/

#ifndef _SUFFIX_TREE_H
#define _SUFFIX_TREE_H

#include <vector>
#include <list>

#define NO_CHILD		NULL

using namespace std;


class Edge;

class Node{
	
	private:

	public:

		vector<Edge *> child;
		vector<Node *> suffixLink;

		void addSuffixLink(Node *node);
		int addChild(Node *node);
};




class Edge {
	private:
		int startPrivateAlloc:1;
		int endPrivateAlloc:1;
		
	public:
		int *end;
		int *start;
		Node *child;	

		
		Edge();
		Edge(int start, int end);
		Edge(int start, int *end);
		Edge(int *end);
		~Edge();

		int makeLocalEndCopy(int end);
		
};


class SuffixTree{
	private:
		int end;
		int rem;
		Node *root;
		string str;
		string suff;
		vector<string> seq;


		//active point description
		bool alreadyIn;
		Node *activeNode;
		Edge *activeEdge;
		int activeLength;

		void internalDebug();

		Edge *searchForEdge(Node *n, char c);
		Edge *searchForEdge(Node *n, string *str);
		bool compareEdge(Edge *e, string *str);
		int doSplit();
		int insertSuffix();
		int addNewEdge();
		int checkActivePoint();
		int updateActivePoint();
		int proccessActivePoint();
		int addSymb(int idx);

		int underLeafNode(Node *n);
		int goThroughTree(Node *n, Edge *e, string str);

	public:

		SuffixTree();

		int addString(string str);
		int debugPrintEdges(Node *n, int pad);
		int getSeqs();


};




#endif


