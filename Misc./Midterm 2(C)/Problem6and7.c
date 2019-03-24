/*
 * Problem6.c
 *
 *  Created on: Feb 27, 2019
 *      Author: Owner
 */
#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int search(char* S, char c)
{
	int i = 0;

	while(S[i] != '\0')
	{
		if(S[i] == c)
			return i;

		i++;
	}

	return -1;

}

char* diff(char* A, char* B)
{

	char* R = NULL;
	R = calloc(strlen(A), sizeof(char));

	for(int i = 0; i < strlen(A); i++)
	{
		int check = search(A, *(B+i));
		printf("%d ", check);
	if(check == -1)
		*(R + i) = *(A + i);
	}
	*(R + strlen(A)) = '\0';

	return R;




}


int main()
{
	//printf("%s", diff("bean", "try"));
	printf("%d", search("orange", 'a'));
}
