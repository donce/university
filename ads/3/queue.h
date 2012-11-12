/*

        Author: Rytis Karpuška
                jauleris@gmail.com

*/


#ifndef _QUEUE_H
#define _QUEUE_H

#define QUEUE_FAIL			-1
#define QUEUE_SUCCESS			0

#define QUEUE_TRUE			1
#define QUEUE_FALSE			0

typedef struct queueElem_t {
	struct queueElem_t *next;
	void *data;

} queueElem_t;


typedef struct queue_t {
	struct queueElem_t *tail;
	struct queueElem_t *head;
	int elemCnt;	

} queue_t;


/*
creates and initiates new queue structure
RETURN:
	queue_t structure address on success
	NULL on failure
*/
queue_t *queueNew();



/*
free queue structure
ARGS:
	queue_t - queue structure (MUST BE INITIALIZED BY queueNew() )
RETURN:
	SUCCESS - if everything is ok
	FAIL - if something went wrong

*/
int queueFree(queue_t *q);



/*
enqueues one element to queue. NOTE: queue saves only pointer, not the data itself
ARGS:
	queue_t - queue structure (MUST BE INITIALIZED BY queueNew() )
	data - pointer to the data (copy will not be made)
RETURN:
	SUCCESS - if everything was OK
	FAIL - if something went wrong
*/
int queueAdd(queue_t *q, void *data);



/*
dequeues one element from queue. 
ARGS:
	queue_t	- queue structure (MUST BE INITIALIZED BY queueNew() )
RETURN:
	pointer to the data given at queueAdd call
	NULL - if queue is empty
*/
void *queueDeq(queue_t *q);



/*
flushes all elements from queue
ARGS:
	queue_t - queue structure (MUST BE INITIALIZED BY queueNew() )
RETURN:
	SUCCESS - on success
	FAIL - on failure

*/
int queueFlush(queue_t *q);



/*
get one element without removing it from queue
ARGS:
	queue_t - queue structure (MUST BE INITIALIZED BY queueNew() )
RETURN:
	pointer to the data given at queueAdd call
	NULL - if queue is empty

*/
void *queueGet(queue_t *q);



/*
gets the size of queue
ARGS:
	queue_t - queue structure (MUST BE INITIALIZED BY queueNew() )
RETURN:
	number of elements on success
	FAIL - on failure
*/
int queueGetSize(queue_t *q);



/*
check if queue is full
ARGS:
	queue_t - queue structure (MUST BE INITIALIZED BY queueNew() )
RETURN:
	FAIL - on failure
	TRUE - if full
	FALSE - if not full
*/
int queueIsFull(queue_t *q);



/*
check if queue is empty
ARGS:
	queue_t - queue structure (MUST BE INITIALIZED BY queueNew() )
RETURN:
	FAIL - on failure
	TRUE - if empty
	FALSE - if not empty


*/
int queueIsEmpty(queue_t *q);

#endif
