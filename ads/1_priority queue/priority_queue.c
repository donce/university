#include "priority_queue.h"
#include <stdlib.h>

void init(priority_queue *p)
{
    p->begin = NULL;
    p->size = 0;
}

void destroy(priority_queue *p)
{
    Node *n = p->begin;
    while (n != NULL) {
        Node *next = n->next;
        free(n);
        n = next;
    }
    p->size = 0;
}

void push(priority_queue_type element, int priority, priority_queue *p)
{
    Node *prev = NULL, *now = p->begin;
    while (now != NULL && now->priority > priority) {
        prev = now;
        now = now->next;
    }

    Node *n = malloc(sizeof(Node));
    n->data = element;
    n->priority = priority;
    n->next = now;

    if (prev == NULL)
        p->begin = n;
    else
        prev->next = n;
    p->size++;
}

priority_queue_type pop(priority_queue *p, bool *success)
{
    if (p->begin == NULL) {
        *success = false;
        return 0;
    }
    *success = true;
    priority_queue_type e = p->begin->data;
    Node *n = p->begin;
    p->begin = p->begin->next;
    free(n);
    p->size--;
    return e;
}

priority_queue_type top(priority_queue *p, bool *success)
{
    if (p->begin == NULL) {
        *success = false;
        return 0;
    }
    *success = true;
    return p->begin->data;
}

int size(priority_queue *p)
{
    return p->size;
}

bool empty(priority_queue *p)
{
    return size(p) == 0;
}

void merge(priority_queue *from, priority_queue *to)
{
    Node *n = from->begin;
    for (n = from->begin; n != NULL; n = n->next)
        push(n->data, n->priority, to);
}
