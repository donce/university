/*

	Author: Rytis Karpuška
		rytis@elektromotus.lt

*/

#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "common.h"
#include "suffixTree.h"

using namespace std;


Edge::Edge()
{
	this->child = NULL;

	//if no starting and ending information give, assume, that it is zero
	this->start = (int *)malloc(sizeof(this->start));
	this->startPrivateAlloc = TRUE;

	this->end = (int *)malloc(sizeof(this->start));
	this->endPrivateAlloc = TRUE;

	this->start = this->end = 0;

	return;
}

Edge::Edge(int start, int end)
{
	this->child = NULL;

	this->start = new int;
	*this->start = start;
	this->startPrivateAlloc = TRUE;

	this->end = new int;
	*this->end = end;
	this->endPrivateAlloc = TRUE;

	return;
}

Edge::Edge(int start, int *end)
{
	this->child = NULL;


	this->start = new int;
	*this->start = start;
	this->startPrivateAlloc = TRUE;

	this->end = end;
	this->endPrivateAlloc = FALSE;

	return;
}


Edge::Edge(int *end)
{
	this->child = NULL;

	this->start = new int;
	this->startPrivateAlloc = TRUE;

	this->end = end;
	this->endPrivateAlloc = FALSE;

	return;
}

Edge::~Edge()
{
	if(this->startPrivateAlloc)
		free(this->start);

	if(this->endPrivateAlloc)
		free(this->end);

	return;
}


int Edge::makeLocalEndCopy(int end)
{
	if(this->endPrivateAlloc)
		return FAIL;

	//allocate memory
	this->end = new int;
	*this->end = end;
	this->endPrivateAlloc = TRUE;

	return SUCC;
}



int Node::addChild(Node *node)
{

	return SUCC;
}

void Node::addSuffixLink(Node *node)
{
	this->suffixLink.push_back(node);

	return;
}







SuffixTree::SuffixTree()
{
	this->end = 0;
	this->root = new Node();
	this->activeNode = this->root;
	this->activeEdge = NULL;
	this->activeLength = 0;

	return;
}



int SuffixTree::checkActivePoint()
{
	this->activeEdge = this->searchForEdge(this->activeNode, this->suff[0]);

	if(this->activeEdge == NULL){
		this->activeLength = 0;
		return SUCC;
	}


	int len = *this->activeEdge->end - *this->activeEdge->start;

	//if active edge string is longer, the active point, do nothing
	if(len > this->activeLength)
		return SUCC;

	this->activeLength -= len;
	this->activeNode = this->activeEdge->child;

	this->suff.erase(0, len);
	
	if(this->suff.size() > 0){
		this->activeEdge = this->searchForEdge(this->activeNode, this->suff[0]);
		if(!this->activeEdge){
			this->activeLength = 0;
			return SUCC;
		}
		this->checkActivePoint();
	} else {
		this->activeEdge = NULL;
		this->activeLength = 0;
	}

	return SUCC;
}

Edge *SuffixTree::searchForEdge(Node *n, char c)
{
	Edge *e = NULL;

	//search for repeated character
	vector<Edge *>::iterator it;
	for(it = n->child.begin(); it != n->child.end(); it++)
		if(this->str[*(*it)->start] == c){
			e = *it;			
			break;	
		}


	return e;
}

Edge *SuffixTree::searchForEdge(Node *n, string *str)
{
	Edge *e = NULL;

	//some simple optimizations
	if(str->size() == 0)
		return NULL;
	else if(str->size() == 1)
		return searchForEdge(n, str->at(0));

	//search for substring in edge string
	string edgeStr;
	vector<Edge *>::iterator it;
	int len, eq;
	for(it = n->child.begin(); it != n->child.end(); it++){
		//form edge string
		edgeStr = this->str.substr(*(*it)->start, *(*it)->end - *(*it)->start);
		if(edgeStr.size() < str->size())
			continue;

		//look for inequalities
		eq = FALSE;
		len = str->size();
		for(int i = 0; i < len; i++){
			if(edgeStr[i] != str->at(i)){
				eq = TRUE;
				break;		
			}	
		}
	
		//if we found, just return	
		if(eq == TRUE){
			e = *it;
			break;	
		}
	}

	return e;
}


bool SuffixTree::compareEdge(Edge *e, string *str)
{
	if(e == NULL)
		return FALSE;

	string edge = this->str.substr(*e->start, str->size());

	if(edge.compare(*str) == 0)
		return TRUE;

	return FALSE;
}

int SuffixTree::updateActivePoint()
{
	//check if we are adding the first letter
	if(this->activeEdge == NULL){
		//we are changing our active edge, redo search
		this->activeEdge = this->searchForEdge(this->activeNode, this->suff[0]);
	}

	this->alreadyIn = this->compareEdge(this->activeEdge, &this->suff);

	//if it exists - update active length
	if(this->alreadyIn == TRUE)
		this->activeLength++;

	return SUCC;
}


int SuffixTree::doSplit()
{
	static Node *oldNode = NULL;
	static int oldEnd = -1;

	//create nodes
	Node *node = new Node();
	Node *hNode = new Node();
	Edge *low = new Edge(*this->activeEdge->start,
					*this->activeEdge->start + this->activeLength);
	Edge *high = new Edge(*this->activeEdge->start + this->activeLength, 
					this->activeEdge->end);

	//copy all parent children
	hNode->child = this->activeEdge->child->child;

	//take care of suffix links
	if(oldEnd == this->end){
		oldNode->suffixLink.push_back(node);
	}
	oldNode = node;
	oldEnd = this->end;


	//if edge had its own end, make copy for this one too
	if(hNode->child.size() > 0)
		high->makeLocalEndCopy(*high->end);
	high->child = hNode;

	//add new node to our child
	low->child = node;
	node->child.push_back(high);

	//add our little splitted tree, to active point
	this->activeNode->child.push_back(low);

	//remove old long edge
	for(unsigned int i = 0; i < this->activeNode->child.size(); i++){
		if(this->activeEdge == this->activeNode->child[i]){
			delete this->activeNode->child[i];
			this->activeNode->child[i] = this->activeNode->child.back();
			this->activeNode->child.pop_back();
			break;
		}
	}

	//update active point
	this->activeNode = node;
	this->activeEdge = high;
	this->activeLength = 0; 

	return SUCC;
}


void SuffixTree::internalDebug()
{

	char c;
	printf("============\n");
	printf("dbG: %lu %c %c\n", this->suff.size(), this->suff[0],
								this->suff[this->suff.size() - 1]);
	if(this->activeEdge)
		c = this->str[*this->activeEdge->start];
	else
		c = '$';

	char node1;
	node1 = this->str[*this->activeNode->child[0]->start];

	printf("act: %c %c %d\n", node1, c, this->activeLength);
	this->debugPrintEdges(NULL, 0);
	printf("============\n");

	return;
}



int SuffixTree::insertSuffix()
{
	Edge *primaryEdge = this->activeEdge;
	Node *primaryNode = this->activeNode;
	int primaryLength = this->activeLength;
	
	//check if we need to do split
	if(this->activeLength > 0)
		if(this->doSplit() != SUCC)
			return FAIL;

	//create new edge and new node
	Node *node = new Node;
	Edge *edge = new Edge(this->end - 1, &this->end);

	//add those into our tree
	this->activeNode->child.push_back(edge);
	edge->child = node;

	//restore active point if split happened
	this->activeNode = primaryNode;
	this->activeLength = primaryLength;
	this->activeEdge = primaryEdge;
	
	return SUCC;

}



int SuffixTree::addNewEdge()
{
	this->checkActivePoint();

	if(this->activeEdge){
		string edgeStr = this->str.substr(*this->activeEdge->start,
					*this->activeEdge->end - *this->activeEdge->start);

		if(edgeStr.find(this->suff) == 0){
			this->activeLength = this->suff.size();
			return SUCC;
		}
	}

	if(this->insertSuffix() != SUCC)
		return FAIL;

//	this->internalDebug();

	//follow suffix link
	unsigned int i;
	for(i = 0; i < this->activeNode->suffixLink.size(); i++){
		this->activeEdge = this->searchForEdge(this->activeNode->suffixLink[i],
						this->suff[0]);
		this->activeNode = this->activeNode->suffixLink[i];
		this->addNewEdge();
		return SUCC;
	}	

	//go to root 
	if(this->activeNode != this->root){ 
		this->activeNode = this->root;
		this->activeEdge = this->searchForEdge(this->root, this->suff[0]);
		this->addNewEdge();

		return SUCC;
	}


	//take care of suffix string
	if(this->suff.size() > 0)
		this->suff.erase(0, 1);

	//active point changing
	if(this->activeNode == this->root){
		this->activeEdge = this->searchForEdge(this->root, this->suff[0]);

		//decrement active length
		if(this->activeLength > 0)
			this->activeLength--;

		//put all implicitly contained suffixes into the tree
		if(this->suff.size() > 0)
			this->addNewEdge();

	} 

	return SUCC;
}


int SuffixTree::proccessActivePoint()
{
	//if edge with current suffix does not exist, create it
	if(this->alreadyIn == FALSE)		
		if(this->addNewEdge() != SUCC)
			return FAIL;

	return SUCC;
}

int SuffixTree::addSymb(int idx)
{
	//increment our end pointer for every edge
	//and update suffix string
	this->suff.push_back(this->str[this->end]);

	//increment our back pointer
	this->end++;

	//update active point and string to be added
	if(this->updateActivePoint() != SUCC)
		return FAIL;

	//proccess accumulated string
	if(this->proccessActivePoint() != SUCC)
		return FAIL;

	return SUCC;
}



int SuffixTree::addString(string str)
{
	unsigned int i;

	this->str = str;

	for(i = 0;  i < str.size(); i++){
		if(this->addSymb(i) != SUCC)
			return FAIL;
	}

	return SUCC;
}



int SuffixTree::underLeafNode(Node *n)
{
	unsigned int i;

	for(i = 0; i < n->child.size(); i++){
		if(n->child[i]->child->child.size() > 0)
			return FALSE;
	}

	return TRUE;
}


int SuffixTree::goThroughTree(Node *n, Edge *e, string str)
{
	unsigned int i;

	if(e != NULL){
		str.append(this->str.substr(*e->start, *e->end - *e->start));
	}	

	if(this->underLeafNode(n)){
		cout << n->child.size() << " " << str << endl;
	}	

	for(i = 0; i < n->child.size(); i++){
		if(n->child[i]->child->child.size() > 0)
			this->goThroughTree(n->child[i]->child, n->child[i], str);
	}

	return SUCC;
}


int SuffixTree::getSeqs()
{
	string str;

	if(this->goThroughTree(this->root, NULL, str) == SUCC)
		return SUCC;

	return FAIL;
}






int SuffixTree::debugPrintEdges(Node *n, int pad)
{
	if(n == NULL)
		n = this->root;
	
	int start, length;


	//print padding
//	for(int i = 0; i < pad; i++)
//		printf(" ");
//
//	printf("0x%lx %lu | Suffix: ", (unsigned long int)n, n->child.size());
//	for(unsigned int i = 0; i < n->suffixLink.size(); i++){
//		printf("0x%lx ", (unsigned long int)n->suffixLink[i]);
//	}
//	printf(" |\n");

	vector<Edge *>::iterator it;

	for(it = n->child.begin(); it != n->child.end(); it++){
		//get string info
		start = *(*it)->start;
		length = *(*it)->end - *(*it)->start;

		//print padding
		for(int i = 0; i < pad; i++)
			printf(" ");

		//print edge string
		cout <<  this->str.substr(start, length) << endl;


		if((*it)->child != NULL)
			this->debugPrintEdges((*it)->child, pad + 4);
	}

	return SUCC;
}


