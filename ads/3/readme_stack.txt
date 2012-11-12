Karolis Deveikis
PS-1
1 užduotis
Stekas

Antraščių failas:
	stack.h

TIPAI:

stack_t
	Steko tipas.
	Šio tipo kintamųjų tiesiogiai deklaruoti negalima, tačiau galima sukurti rodyklę į šį
	tipą ir jai priskirti adresą, grąžinta funkcijos create_st().

FUNKCIJOS:

stack_t* create_st(unsigned int size)
	Grąžina rodyklę į tuščią steką.
	Parametrai:
		size - steko elementų dydis baitais
	Jei nepavyksta išskirti atminties arba size lygu 0, grąžinama NULL.

int destroy_st(stack_t* stack)
	Sunaikina steką.
	Parametrai:
		stack - rodyklė į steką
	Kiekvieną steką, sukurtą funkcijos create_st(), būtina sunaikinti funkcija destroy_st().
	Jei stekas netuščias, jo elementai sunaikinami.
	Jei stack lygu NULL, grąžinama STACK_FAILURE.
	Jei steką sunaikinti pavyksta, gražinama STACK_SUCCESS.

int push_st(stack_t* stack, void* source)
	Įkelia į steką elementą, esantį nurodytu adresu.
	Parametrai:
		stack - rodyklė į steką
		source - elemento adresas
	Jei bent vienas iš parametrų lygus NULL arba nepavyksta išskirti atminties, grąžinama STACK_FAILURE.
	Jei elementą įkelti pavyksta, grąžinama STACK_SUCCESS.

int pop_st(stack_t* stack, void* dest)
	Išima iš steko elementą ir įrašo jį nurodytu adresu.
	Parametrai:
		stack - rodyklė į steką
		dest - rodyklė į vietą atmintyje, kur reikia įrašyti elementą
	Jei dest lygu NULL, steko elementas niekur neįrašomas.
	Jei stack lygu NULL arba stekas tuščias, grąžinama STACK_FAILURE.
	Jei elementą išimti pavyksta, grąžinama STACK_SUCCESS.

int clear_st(stack_t* stack)
	Ištuština steką.
	Parametrai:
		stack - rodyklė į steką
	Jei stack lygu NULL, grąžinama STACK_FAILURE.
	Jei steką ištuštinti pavyksta, gražinama STACK_SUCCESS.

int peek_st(stack_t* stack, void* dest)
	Nuskaito viršutinio steko elemento reikšmę ir ją įrašo nurodytu adresu.
	Parametrai:
		stack - rodyklė į steką
		dest - rodyklė į vietą atmintyje, kur reikia įrašyti elementą
	Elementas iš steko neišimamas, tik įrašomas nurodytu adresu.
	Jei bent vienas iš parametrų lygus NULL arba stekas tuščias, grąžinama STACK_FAILURE.
	Jei elementą įrašyti pavyksta, grąžinama STACK_SUCCESS.

int isempty_st(stack_t* stack)
	Patikrina, ar stekas tuščias.
	Parametrai:
		stack - rodyklė į steką
	Jei stack lygu NULL, grąžinama STACK_FAILURE.
	Jei stekas tuščias, grąžinama STACK_TRUE.
	Jei stekas netuščias, grąžinama STACK_FALSE.

int getln_st(stack_t* stack)
	Grąžina steko ilgį (elementų skaičių).
	Parametrai:
		rodyklė į steką
	Jei stack lygu NULL, grąžinama STACK_FAILURE.

KAI KURIŲ FUNKCIJŲ NAUDOJIMO PAVYZDYS:

/* Sukuriamas stekas, talpinantis int tipo elementus */
stack_t* st = create_st(sizeof(int));
int a = 5, b;
/* Į steką įkeliamas skaičius 5 */
push_st(st, &a);
/* Į kintamajį b įrašoma steko viršutinė reikšmė */
peek_st(st, &b);
/* Stekas sunaikinamas */
destroy_st(st);
