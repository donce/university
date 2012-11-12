#include "stack.h"
#include <stdlib.h>

#include <stdio.h>

void init(stack *s) {
    s = NULL;
}

void destroy(stack *s) {
    while (s->top != NULL) {
        node *next = s->top->next;
        free(s->top);
        s->top = next;
    }
}

void push(stack *s, Type data) {
    node *n = malloc(sizeof(stack));
    n->data = data;
    n->next = s->top;
    s->top = n;
}

Type pop(stack *s) {
    Type t;
    if (!empty(s)) {
	node *n = s->top;
	s->top = s->top->next;
        t = n->data;
        free(n);
    }
    return t;
}

bool empty(stack *s) {
    return s->top == NULL;
}
