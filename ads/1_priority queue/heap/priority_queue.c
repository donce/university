#include "priority_queue.h"
#include <stdlib.h>

void init(priority_queue *p, int length)
{
    p->end = 0;
    p->maxLength = length;
    p->n = malloc(length*sizeof(int));
}

void destroy(priority_queue *p)
{
    free(p->n);
}

void push(int element, priority_queue *p, bool *success)
{
    if (full(p))
    {
        *success = false;
        return;
    }
    *success = true;
    int nr = p->end++;
    p->n[nr] = element;
    while (p->n[nr] > p->n[nr / 2])
    {
        swap(&p->n[nr], &p->n[nr/2]);
        nr /= 2;
    }
}

void swap(int *a, int *b)
{
    int t = *a;
    *a = *b;
    *b = t;
}

int pop(priority_queue *p, bool *success)
{
    if (empty(p))
    {
        *success = false;
        return;
    }
    *success = true;
    int value = p->n[0];
    p->n[0] = p->n[--p->end];

    int nr = 0;
    bool finished = 0;
    while (!finished)
    {
        if (nr*2 < p->end)
        {
            int max = nr*2;
            if (max+1 < p->end && p->n[max+1] > p->n[max])
                max++;
            if (p->n[max] > p->n[nr])
            {
                swap(&p->n[nr], &p->n[max]);
                nr = max;
            }
            else
                finished = 1;
        }
        else
            finished = 1;
    }
    return value;
}

int top(priority_queue *p, bool *success)
{
    if (empty(p))
    {
        *success = false;
        return;
    }
    *success = true;
    return p->n[0];
}

int size(priority_queue *p)
{
    return p->end;
}

bool empty(priority_queue *p)
{
    return size(p) == 0;
}

bool full(priority_queue *p)
{
    return size(p) == p->maxLength;
}
