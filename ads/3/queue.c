/*

	Author: Rytis Karpuška
		jauleris@gmail.com

*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h> // << memset is used

#include "queue.h"

queue_t *queueNew()
{
	//allocate structure
	queue_t *q = malloc(sizeof(*q));
	if(q == NULL)
		return NULL;

	//initiate our structure
	memset(q, 0, sizeof(*q));

	return q;
}


int queueFree(queue_t *q)
{
	if(q == NULL)
		return QUEUE_FAIL;

	free(q);

	return QUEUE_SUCCESS;
}



int queueAdd(queue_t *q, void *data)
{
	if(q == NULL)
		return QUEUE_FAIL;

	//allocate our new elem
	queueElem_t *newElem = malloc(sizeof(*newElem));
	if(newElem == NULL)
		return QUEUE_FAIL;

	//copy data pointer in it
	newElem->next = NULL;
	newElem->data = data;	
	
	if(q->head == NULL)
		q->tail = newElem;//our queue is empty, so tail and head is the same thing
	else
		q->head->next = newElem;//otherwise update links

	q->head = newElem;//mark new head
	q->elemCnt++;

	return QUEUE_SUCCESS;
}



void *queueDeq(queue_t *q)
{
	if(q == NULL)
		return NULL;

	//we have empty queue
	if(q->tail == NULL)
		return NULL;

	//save our data pointer
	void *data = q->tail->data;

	//remove tail element
	queueElem_t *tmpElem;
	tmpElem = q->tail;
	
	//move one elem into the front
	q->tail = q->tail->next;

	//check if this is the end
	if(q->tail == NULL)
		q->head = NULL;

	//take care of eleme counter
	q->elemCnt--;

	//free memory
	free(tmpElem);

	return data;
}



void *queueGet(queue_t *q)
{
	if(q == NULL)
		return NULL;

	if(q->tail == NULL)
		return NULL;

	return q->tail->data;
}



int queueFlush(queue_t *q)
{
	if(q == NULL)
		return QUEUE_FAIL;

	while(queueDeq(q) != NULL);

	return QUEUE_SUCCESS;
}



int queueGetSize(queue_t *q)
{
	if(q == NULL)
		return QUEUE_FAIL;	

	return q->elemCnt;
}



int queueIsFull(queue_t *q)
{
	if(q == NULL)
		return QUEUE_FAIL;
	else
		return QUEUE_FALSE;


}

int queueIsEmpty(queue_t *q)
{
	if(q == NULL)
		return QUEUE_FAIL;

	if(q->tail == NULL)
		return QUEUE_TRUE;

	return QUEUE_FALSE;
}

