/*
Karolis Deveikis
PS-1
1 užduotis
Stekas
*/

#ifndef _STACK_H
#define _STACK_H

#define STACK_FALSE		0
#define STACK_TRUE		1
#define STACK_FAILURE	-1
#define STACK_SUCCESS	2

/* Steko tipas */
typedef struct stack_t stack_t;

/* Sukurti tuščią steką, kuris talpina size dydžio elementus */
stack_t* create_st(unsigned int size);
/* Sunaikinti steką */
int destroy_st(stack_t* stack);
/* Įkelti į steką */
int push_st(stack_t* stack, void* source);
/* Išimti iš steko */
int pop_st(stack_t* stack, void* dest);
/* Ištuštinti steką */
int clear_st(stack_t* stack);
/* Nuskaityti viršutinio elemento reikšmę jo neišimant */
int peek_st(stack_t* stack, void* dest);
/* Ar stekas tuščias? */
int isempty_st(stack_t* stack);
/* Steko ilgis */
int getln_st(stack_t* stack);

#endif
