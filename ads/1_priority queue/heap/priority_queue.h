#ifndef INCLUDE_PRIORITY_QUEUE
#define INCLUDE_PRIORITY_QUEUE


#include <stdbool.h>


typedef struct {
    int end;

    int maxLength;
    int *n;
} priority_queue;

void init(priority_queue *p, int length);
void destroy(priority_queue *p);

void push(int element, priority_queue *p, bool *success);
int pop(priority_queue *p, bool *success);
int top(priority_queue *p, bool *success);

int size(priority_queue *p);
bool empty(priority_queue *p);
bool full(priority_queue *p);
#endif
