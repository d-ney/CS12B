#include "stdio.h"
#include "stdlib.h"


char* cat(char* s1, char* s2)
{
	char s = s1[0];
	int s1len = 0;
	while(s!='\0')
	{
		s1len++;
		s = s1[s1len];
	}

	char sTwo = s2[0];
	int s2len = 0;
	while(sTwo!='\0')
	{
		s2len++;
		sTwo = s2[s2len];
	}

	char* catted = calloc(s1len + s2len + 1, sizeof(char));
	for(int i = 0; i < s1len; i++)
	{
		catted[i] = s1[i];
	}
	for(int j = 0; j < s2len; j++)
	{
		catted[j + s1len] = s2[j];
	}

	catted[s1len + s2len] = '\0';

	return catted;
}

int main()
{
	printf("%s", cat("bear", "pig"));
}

