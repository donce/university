#include <stdio.h>
#include <stdlib.h>
#include "priority_queue.h"

priority_queue p;

void doAction(int nr)
{
    int e;
    switch (nr)
    {
        bool success;
        case 1:
            printf("Maksimalus dydis:");
            scanf("%d", &e);
            init(&p, e);
            printf("Sukurta.\n");
            break;
        case 2:
            destroy(&p);
            printf("Sunaikinta.\n");
            break;
        case 3:
            printf("Ideti reiksme: ");
            scanf("%d", &e);
            push(e, &p, &success);
            if (success)
                printf("Ideta.\n");
            else
                printf("Klaida, ideti nepavyko!\n");
            break;
        case 4:
            e = top(&p, &success);
            if (success)
                printf("Didziausia reiksme: %d.\n", e);
            else
                printf("Didziausios reiksmes gauti nepavyko!\n");
            break;
        case 5:
            e = pop(&p, &success);
            if (success)
                printf("Isimta reiksme: %d.\n", e);
            else
                printf("Isimti didziausio elemento nepavyko!\n");
            break;
        case 6:
            if (empty(&p))
                printf("Tuscia.\n");
            else
                printf("Ne tuscia.\n");
            break;
        case 7:
            if (full(&p))
                printf("Pilna.\n");
            else
                printf("Ne pilna.\n");
            break;
        case 8:
            printf("Ilgis: %d\n", size(&p));
            break;
        default:
            printf("Nezinoma komanda!\n");
            break;
    }
}

char *o[] = {"Sukurti",
             "Sunaikinti",
             "Ideti elementa",
             "Gauti didziausio elemento reiksme",
             "Isimti didziausia elementa",
             "Patikrinti, ar tuscia",
             "Patikrinti, ar pilna",
             "Gauti elementu skaiciu",
             "Gauti dydi"};
int oNr = 8;

int main()
{
    int nr = 1;
    while (nr)
    {
        printf("--------------------------------------\n");
        printf("              Operacijos              \n");
        printf("--------------------------------------\n");
        int i;
        for (i = 0; i < oNr; i++)
            printf("%d. %s.\n", i+1, o[i]);
        printf("%d. %s.\n", 0, "Baigti programa");
        printf("--------------------------------------\n");
        printf("Pasirinkimas: ");
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
