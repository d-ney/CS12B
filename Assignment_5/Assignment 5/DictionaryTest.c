////-----------------------------------------------------------------------------
//// DictionaryClient.c
//// Test client for the Dictionary ADT
//// Author: Dillon Ney ID# 1705097
////-----------------------------------------------------------------------------
//
//#include<stdio.h>
//#include<stdlib.h>
//#include<string.h>
//#include"Dictionary.h"
//
//#define MAX_LEN 180
//
//int main(int argc, char* argv[]){
//	printf("working\n");
//	Dictionary A = newDictionary();
//	printf("%d\n", isEmpty(A));
//	char* v = lookup(A, "b");
//	printf("%s\n", v==NULL?"not found ":"value=");
//	insert(A, "a", "apple");
//	char* v2 = lookup(A, "a");
//	printf("size: %d", size(A));
//	printf("%s\n", v2);
//		printf("%s\n", v2==NULL?"not found ":"value=");
//
//	insert(A, "b", "banana");
//	insert(A, "c", "cantaloupe");
//	printDictionary(stdout, A);
//	delete(A, "b");
//	printDictionary(stdout, A);
//	makeEmpty(A);
//	printDictionary(stdout, A);
//	printf("%d\n", isEmpty(A));
//	printf("size: %d", size(A));
//   return(EXIT_SUCCESS);
//}
