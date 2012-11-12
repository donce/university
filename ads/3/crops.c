#include <stdlib.h>
#include <stdio.h>
#include "queue.h"
#include "stack.h"
#include <stdbool.h>

stack_t *stack;
queue_t *queue;

typedef struct {
	int buyAmount;
	int buyError;

	int cost;
	int costError;

	int markup;

	int total;//current amount
} Warehouse;

typedef struct {
	int amount;
	int cost;
} Crops;

int days;

Warehouse w;

int min(int a, int b) {
	if (a < b)
		return a;
	return b;
}

//get value with error in percents
int getValue(int value, int error) {
	int amount = value * error /  100;
	int e = rand() % (2 * amount);
	return value + (e - amount);
}

bool read() {
	FILE *f = fopen("input", "r");
	if (f == NULL)
		return 1;
	fscanf(f, "%d %d %d %d %d %d", &w.buyAmount, &w.buyError, &w.cost, &w.costError, &w.markup, &days);
	return 0;
}


int main() {
	srand(time(0));
	if (read()) {
		printf("Can't read file!\n");
		return 1;
	}

	queue = queueNew();
	if (queue == NULL) {
		printf("Can't create queue!\n");
		return 1;
	}
	stack = create_st(sizeof(Crops));
	if (stack == NULL) {
		printf("Can't create stack!\n");
		return 1;
	}
	
	w.total = 0;
	int i;
	for (i = 0; i < days; i++) {
		
		//buy
		Crops *c = malloc(sizeof(Crops));
		c->amount = getValue(w.buyAmount, w.buyError);
		c->cost = getValue(w.cost, w.costError);

		Crops *c2 = malloc(sizeof(Crops));
		*c2 = *c;

		if (queueAdd(queue, c) != QUEUE_SUCCESS) {
			printf("Can't push element to queue!\n");
			return 1;
		}
		if (push_st(stack, c2) != STACK_SUCCESS) {
			printf("Can't push element to stack!\n");
			return 1;
		}
		printf("%d %d\n", c->amount, c->cost);
		w.total += c->amount;

		//sell
		//int sell = w.total * (rand() % 101) / 100;
		int sell = 0;

		int left;
		//queue
		left = sell;
		while (left) {
			c = queueGet(queue);
			if (c == NULL) {
				printf("Can't get element from queue!\n");
				return 1;
			}
			int amount = min(left, c->amount);
			left -= amount;
			c->amount -= amount;
			if (!c->amount)
				if (queueDeq(queue) == NULL) {
					printf("Can't pop element from queue!\n");
					return 1;
				}
		}
		//stack
		left = sell;
		while (left) {
			if (peek_st(stack, c) != STACK_SUCCESS) {
				printf("Can't get element from stack!\n");
				return 1;
			}
			int amount = min(left, c->amount);
			left -= amount;
			c->amount -= amount;
			if (!c->amount)
				if (pop_st(stack, c) != STACK_SUCCESS) {
					printf("Can't pop element from stack\n");
					return 1;
				}
		}

		w.total -= sell;
	}
	queueFree(queue);
	destroy_st(stack);

   	return 0;
}
