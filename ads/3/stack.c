/*
Karolis Deveikis
PS-1
1 u�duotis
Stekas
*/

#include <stdlib.h>
#include <string.h>
#include "stack.h"

/* Steko struktura */
typedef struct stack_node
{
	void* data;
	struct stack_node* below;
} stack_node;
struct stack_t
{
	unsigned int data_size;
	int length;
	stack_node* stack_top;
};

/* Sukurti tu�cia steka, kurio elementu dydis lygus size */
stack_t* create_st(unsigned int size)
{
	if(size == 0)
	{
		/* Jei nurodytas dydis 0, toliau nevykdyti ir gra�inti NULL */
		return NULL;
	}
	stack_t* stack = malloc(sizeof(stack_t));
	if(!stack)
	{
		/* Jei nepavyksta i�skirt atminties, gra�int NULL */
		return NULL;
	}
	stack->length = 0;
	stack->data_size = size;
	stack->stack_top = NULL;
	return stack;
}

/* Sunaikinti steka */
int destroy_st(stack_t* stack)
{
	if(stack)
	{
		clear_st(stack);
		free(stack);
		return STACK_SUCCESS;
	}
	else
	{
		return STACK_FAILURE;
	}
}

/* Ikelti i steka */
int push_st(stack_t* stack, void* source)
{
	if(stack && source)
	{
		stack_node* temp = malloc(sizeof(stack_node));
		if(!temp)
		{
			/* Jei nepavyksta i�skirti atminties steko grand�iai, prane�ti apie klaida */
			return STACK_FAILURE;
		}
		void* data_ptr = malloc(stack->data_size);
		if(!data_ptr)
		{
			/* Jei nepavyksta i�skirti atminties duomenims, prane�ti apie klaida */
			free(temp);
			return STACK_FAILURE;
		}
		memcpy(data_ptr, source, stack->data_size);
		temp->data = data_ptr;
		temp->below = stack->stack_top;
		stack->stack_top = temp;
		stack->length++;
		return STACK_SUCCESS;
	}
	else
	{
		return STACK_FAILURE;
	}
}

/* I�tu�tinti steka */
int clear_st(stack_t* stack)						
{
	if(stack)
	{
		stack_node* current = stack->stack_top;
		while(current)
		{
			stack_node* temp = current;
			current = current->below;
			free(temp->data);
			free(temp);
		}
		stack->length = 0;
		stack->stack_top = NULL;
		return STACK_SUCCESS;
	}
	else
	{
		return STACK_FAILURE;
	}
}

/* I�imti i� steko */
int pop_st(stack_t* stack, void* dest)
{
	if(stack)
	{
		if(!stack->stack_top)
		{
			/* Jei stekas tu�cias, prane�ti apie klaida */
			return STACK_FAILURE;
		}
		if(dest)
		{
			/* Jei nurodytas adresas, juo nukopijuoti i�imta reik�me */
			memcpy(dest, stack->stack_top->data, stack->data_size);
		}
		stack_node* temp = stack->stack_top;
		stack->stack_top = stack->stack_top->below;
		free(temp->data);
		free(temp);
		stack->length--;
		return STACK_SUCCESS;
	}
	else
	{
		return STACK_FAILURE;
	}
}

/* Nuskaityti vir�utinio elemento reik�me jo nei�imant */
int peek_st(stack_t* stack, void* dest)
{
	if(stack && dest)
	{
		if(!stack->stack_top)
		{
			/* Jei stekas tu�cias, prane�ti apie klaida */
			return STACK_FAILURE;
		}
		memcpy(dest, stack->stack_top->data, stack->data_size);
		return STACK_SUCCESS;
	}
	else
	{
		return STACK_FAILURE;
	}
}

/* Ar stekas tu�cias? */
int	isempty_st(stack_t* stack)
{
	if(stack)
	{
		if(stack->length == 0)
		{
			return STACK_TRUE;
		}
		else
		{
			return STACK_FALSE;
		}
	}
	else
	{
		return STACK_FAILURE;
	}
}

/* Steko ilgis */
int getln_st(stack_t* stack)
{
	if(stack)
	{
		return stack->length;
	}
	else
	{
		return STACK_FAILURE;
	}
}
