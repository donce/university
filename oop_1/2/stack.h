#ifndef STACK_H
#define STACK_H

#include <stdbool.h>


typedef struct {
    char c[256];
} Type;

typedef struct node {
    struct node *next;
    Type data;
} node;

typedef struct {
    node *top;
    int size;
} stack;

void init(stack *s);
void destroy(stack *s);
void push(stack *s, Type data);
Type pop(stack *s);

bool empty(stack *s);

#endif
