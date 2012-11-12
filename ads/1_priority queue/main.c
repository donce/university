#include <stdio.h>
#include <stdlib.h>
#include "priority_queue.h"

priority_queue p;

char *o[] = {"Sukurti",
             "Sunaikinti",
             "Ideti elementa",
             "Gauti didziausio elemento reiksme",
             "Isimti didziausia elementa",
             "Patikrinti, ar tuscia",
             "Gauti elementu skaiciu",
             "Isvalyti ekrana"};
int oNr = 8;

void printOperations() {
    printf("--------------------------------------\n");
    printf("              Operacijos              \n");
    printf("--------------------------------------\n");
    int i;
    for (i = 0; i < oNr; i++)
        printf("%d. %s.\n", i+1, o[i]);
    printf("%d. %s.\n", 0, "Baigti programa");
}

void doAction(int nr)
{
    int priority;
    priority_queue_type t;
    switch (nr)
    {
        bool success;
        case 1:
            printf("Sukurta.\n");
            break;
        case 2:
            destroy(&p);
            printf("Sunaikinta.\n");
            break;
        case 3:
            printf("Ideti elementa: ");
            //scanf("\n");
            //scanf("%c", &t);
            getchar();
            t = getchar();
            printf("Jo prioritetas: ");
            scanf("%d", &priority);
            push(t, priority, &p);
            break;
        case 4:
            t = top(&p, &success);
            if (success)
                printf("Svarbiausias elementas: %c.\n", t);
            else
                printf("Svarbiausio elemento gauti nepavyko!\n");
            break;
        case 5:
            t = pop(&p, &success);
            if (success)
                printf("Isimtas svarbiausias elementas: %c.\n", t);
            else
                printf("Isimti svarbiausio elemento nepavyko!\n");
            break;
        case 6:
            if (empty(&p))
                printf("Tuscia.\n");
            else
                printf("Ne tuscia.\n");
            break;
        case 7:
            printf("Ilgis: %d\n", size(&p));
            break;
        case 8:
            system("cls");
            printOperations();
            break;
        default:
            printf("Nezinoma komanda!\n");
            break;
    }
}

int main()
{
    priority_queue a, b;
    init(&a);
    init(&b);
    push('a', 1, &a);
    push('b', 4, &a);
    push('c', 7, &a);
    push('d', 5, &b);
    push('e', 6, &b);
    push('f', 9, &b);
    merge(&a, &b);
    bool success;
    while (!empty(&b))
        printf("Got: %c\n", pop(&b, &success));

    return 0;

    int nr = 1;
    printOperations();
    while (nr)
    {
        printf("--------------------------------------\n");
        printf("Operacija: ");
        scanf("%d", &nr);
        if (nr)
            doAction(nr);
    }

    /*
    bool success;

    init(&p, 10000000);

    printf("Pushing...\n");
    while (!full(&p))
        push(rand() % 1000, &p, &success);
    printf("Poping...\n");
    while (!empty(&p))pop(&p, &success);
        //printf("%d ", pop(&p, &success));

    destroy(&p);
    */
    return 0;
}
