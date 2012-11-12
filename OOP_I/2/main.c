#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "stack.h"

stack s[4];

bool digit(char c) {
    return '0' <= c && c <= '9';
}

char toLower(char c) {
    if (c >= 'A' && c <= 'Z')
        return c - ('A' - 'a');
    if (c >= 'a' && c <= 'z')
        return c;
    return 0;
}

bool vowel(char c) {
    c = toLower(c);
    if (!c)
        return false;
    return (c == 'a' || c == 'e' || c == 'i' || c == 'u' ||
            c == 'o');
}

bool consonant(char c) {
    c = toLower(c);
    if (!c)
        return false;
    return !vowel(c);
}

int main(int argC, char *arg[])
{
	const int param = 6;
	if (argC != param) {
		printf("Blogas parametru skaicius, reikia %d, gauta %d.\n", param, argC);
		printf("Reikalingi parametrai: input, output1, output2, output3, output4\n");
		return 1;
	}

    FILE *f = fopen(arg[1], "r");
    if (!f) {
        printf("Nepavyko atidaryti duomenu failo!\n");
        return 1;
    }

    Type t;
    int res;
    while (res = fscanf(f, "%s", &t.c) > 0) {
        char c = t.c[0];
        int nr = -1;
        if (vowel(c))
            nr = 0;
        else if (consonant(c))
            nr = 1;
        else if (digit(c))
            nr = 2;
        if (nr != -1)
            push(&s[nr], t);
    }
    fclose(f);
    int i;
    for (i = 0; i < 4; i++) {
        int wordCount = 0;
        int charCount = 0;
        f = fopen(arg[2+i], "w");
        if (!f) {
            printf("Nepavyko sukurti %d isvedimo failo!\n");
            return 1;
        }
        while (!empty(&s[i])) {
            Type t = pop(&s[i]);
            if (i != 3)
                push(&s[3], t);
            fprintf(f, "%s\n", t.c);
            wordCount++;
            charCount += strlen(t.c);
        }
        fprintf(f, "Words: %d\nSymbols: %d\n", wordCount, charCount);
        fclose(f);
    }
    for (i = 0; i < 4; i++)
        destroy(&s[i]);
    return 0;
}
