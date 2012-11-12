#include "generator.h"
#include <stdbool.h>
int length;
bool *used;
int *nr;

void backtrack(int position)
{
    if (position == length)
    {
        int i;
        for (i = 0; i < length; i++)
            printf("%d", nr[i]);
        printf("\n");
    }
    else
    {
        int i;
        int start = 0;
        if (position == 0)
            start = 1;
        for (i = start; i < 10; i++)
            if (!used[i])
            {
                used[i] = true;
                nr[position] = i;
                backtrack(position + 1);
                used[i] = false;
            }
    }
}

void generate(int n)
{
    length = n;
    if (length <= 0)
        printf("Blogas ilgis!");
    else if (length <= 10)
    {
        used = calloc(sizeof(bool), length);
        nr = calloc(sizeof(int), length);
        backtrack(0);
    }
}
