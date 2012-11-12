#ifndef INCLUDE_PRIORITY_QUEUE
#define INCLUDE_PRIORITY_QUEUE

#include "type.h"
#include <stdbool.h>


typedef struct Node {
    int priority;
    priority_queue_type data;
    struct Node *next;
} Node;

typedef struct {
    Node *begin;
    int size;
} priority_queue;


void init(priority_queue *p);
void destroy(priority_queue *p);

void push(priority_queue_type element, int priority, priority_queue *p);
priority_queue_type pop(priority_queue *p, bool *success);
priority_queue_type top(priority_queue *p, bool *success);

int size(priority_queue *p);
bool empty(priority_queue *p);
void merge(priority_queue *from, priority_queue *to);
#endif
