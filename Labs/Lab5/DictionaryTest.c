////-----------------------------------------------------------------------------
//// DictionaryTest.c
//// Test client for the Dictionary ADT
//// Author: Dillon Ney ID # 1705097
////-----------------------------------------------------------------------------
//
//#include<stdio.h>
//#include<stdlib.h>
//#include<string.h>
//
//#include "Dictionary.h"
//
//#define MAX_LEN 180
//
//int main(int argc, char* argv[])
//{
//	Dictionary D = newDictionary();
//
//	char* key[] = {"a","b","c","d","e"};
//	char* value[] = {"apple","banana","cucumber","durian","eggplant"};
//	int i;
//
//   for(i=0; i<5; i++){
//	  insert(D, key[i], value[i]);
//   }
//
//   printDictionary(stdout, D);
//
//   delete(D, "d");
//   printDictionary(stdout, D);
//
//   delete(D, "b");
//
//   insert(D, "f", "fig");
//
//   printf("%s\n", (isEmpty(D)?"true":"false"));
//
//   makeEmpty(D);
//
//   printf("%s\n", (isEmpty(D)?"true":"false"));
//
//   insert(D, "g", "guava");
//
//   insert(D, "h", "honeydew");
//
//   printDictionary(stdout, D);
//
//   delete(D, "a");
//
//   insert(D, "g", "grapefruit");
//
//	freeDictionary(&D);
//
//	return(EXIT_SUCCESS);
//}
