#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

int n;
int *a;
int m;

int left;
bool *avail;


int min(int a, int b) {
	if (a < b)
		return a;
	return b;
}

int bestAnswer;
#define INF (1 << 5)
int bestResult = INF;

void calculate(int now, int pos, int bags) {
	//printf("%d %d %d\n", now, pos, bags);
	if (bestResult == bestAnswer)
        return;
	if (bags >= bestResult)
		return;
	if (!left)
		if (bags < bestResult) {
			bestResult = bags;
	}
	if (pos == n) {
		if (now)
			calculate(0, 0, bags+1);
        return;
	}

	int best = INF;
	if (now + a[pos] <= m) {
		if (avail[pos]) {
			avail[pos] = false;
			left--;
			//printf("%d\n", pos);
			calculate(now + a[pos], pos+1, bags);
			avail[pos] = true;
			left++;
		}
		calculate(now, pos+1, bags);
	}
	if (now)
		calculate(0, 0, bags+1);
}

int calcAnswer() {
	left = n;
	avail = malloc(n * sizeof(bool));
	int i;
	for (i = 0; i < n; i++)
		avail[i] = true;
	calculate(0, 0, 1);
	return bestResult;
}

int compare(const void *a, const void *b) {
	return *(int*)a - *(int*)b;
}

int main() {
    freopen("input", "r", stdin);

	//printf("Enter N:");
	scanf("%d", &n);
	a = malloc(n * sizeof(int));
	//printf("Enter v1, v2, ..., vN:");
	int i;
	for (i = 0; i < n; i++)
		scanf("%d", &a[i]);
	//printf("Enter M:");
	scanf("%d", &m);
	int s = 0;
	for (i = 0; i < n; i++) {
        s += a[i];
        if (a[i] > m)
        {
            printf("Neimanoma sudeti!\n");
            return 0;
        }
	}
	bestAnswer = s / n + (s % n != 0);
	qsort(a, n, sizeof(int), compare);
	printf("Number of needed bags: %d\n", calcAnswer());

	return 0;
}
