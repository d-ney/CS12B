/*
 * Dictionary.c
 * Dictionary ADT using Hash Table Data Structure
 * Author: Dillon Ney ID# 1705097
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


// private types
//--------------------------------------------------------
// NodeObj
typedef struct NodeObj
{
char* key;
char* item;
struct NodeObj* next;
} NodeObj;


// Node
typedef NodeObj* Node;


// newNode()
// constructor of the Node type
Node newNode(char* key, char* item)
{
Node N = malloc(sizeof(NodeObj));
assert(N!=NULL);
N->item = item;
N->key = key;
N->next = NULL;
return(N);
}

//freeNode()
// destructor for the Node type
void freeNode(Node* pN)
{
	if( pN!=NULL && *pN!=NULL )
	{
	free(*pN);
	*pN = NULL;
	}
}


//hash functions
const int tableSize = 101;
#define hashArraySize 101

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
	int sizeInBits = 8*sizeof(unsigned int);
	shift =shift & (sizeInBits -1);
	if ( shift == 0 ) return value;
	return (value << shift) | (value >> (sizeInBits -shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
	unsigned int result = 0xBAE86554;
	while (*input) {
		result ^= *input++;
		result = rotate_left(result, 5);
	}
	return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
	return pre_hash(key) % tableSize;
}


typedef struct DictionaryObj
{

int numItems;
Node hashArray[hashArraySize];



} DictionaryObj;

//Dictionary
typedef struct DictionaryObj* Dictionary;

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void)
{
	Dictionary D = malloc(sizeof(DictionaryObj));
	D->numItems = 0;
	for(int i = 0; i < tableSize; i++)
	{
		D->hashArray[i] = newNode(NULL, NULL);
	}
	return D;
}

void resetDictionary(Dictionary D);

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD)
{
	resetDictionary(*pD);
	if( pD!=NULL && *pD!=NULL )
	{
	free(*pD);
	*pD = NULL;
	}

}

void resetDictionary(Dictionary D)
{

	for(int i = 0; i < tableSize; i++)
	{

		 freeNode(&D->hashArray[i]);
	}
}
// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D)
{
	if( D==NULL )
	{
	      fprintf(stderr,
	         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
	      exit(EXIT_FAILURE);
	}

	if(D->numItems == 0)
		return 1;
	return 0;
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D)
{
	if( D==NULL )
	{
	      fprintf(stderr,
	         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
	      exit(EXIT_FAILURE);
	}

	return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k)
{
	if( D==NULL )
	{
	      fprintf(stderr,
	         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
	      exit(EXIT_FAILURE);
	}

	if(D->numItems != 0)
	{


		int key = hash(k);


			if(D->hashArray[key]->key != NULL)
			{
				Node N = D->hashArray[key];
				for( ; N!= NULL; N = N->next)
				{
					if(N->key == k)
					{

						return N->item;
					}
				}
			}

	}

		return NULL;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v)
{
	if( D==NULL )
	{
	      fprintf(stderr,
	         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
	      exit(EXIT_FAILURE);
	}


	if(lookup(D, k) != NULL)
		{

			fprintf(stderr, "Error: key collision");
			exit(EXIT_FAILURE);

		}


	int key = hash(k);
	Node N = newNode(k, v);

	if (D->hashArray[key] == NULL)
	{
	   Node T = D->hashArray[key];
	   D->hashArray[key] = N;
	   freeNode(&T);
	}
	else
	{

		 N->next = D->hashArray[key];
		 D->hashArray[key] = N;

	}
    D->numItems++;

}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k)
{
	if( D==NULL )
	{
	      fprintf(stderr,
	         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
	      exit(EXIT_FAILURE);
	}


	if(lookup(D, k) != NULL)
		{
		int key = hash(k);
		D->numItems --;


			Node T = D->hashArray[key];


			if (T != NULL && hash(T->key) == key)
						{
							Node N = D->hashArray[key];
							D->hashArray[key] = D->hashArray[key]->next;
							N->next = NULL;
							freeNode(&N);
							return;
						}

			Node P = newNode(NULL, NULL);

						while (T != NULL && hash(T->key) != key)
						{

							P = T;
							T = T->next;
						}

						if (T == NULL)
							return;

						P->next = T->next;
						freeNode(&P);

		}
	else
	{
		fprintf(stderr, "Dictionary Error: key not found\n");
						exit(EXIT_FAILURE);
	}

}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D)
{
	resetDictionary(D);
	for(int i = 0; i < hashArraySize; i++)
	{

			Node N = D->hashArray[i];
			for( ; N != NULL; N = N->next)
			{
					N = newNode(NULL, NULL);
			}
	}

	D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D)
{
	if( D==NULL )
	{
	      fprintf(stderr,
	         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
	      exit(EXIT_FAILURE);
	}

	for(int i = 0; i < tableSize; i++)
	{
		if(D->hashArray[i]->key != NULL && D->hashArray[i]->item !=NULL)
		{
			Node N = D->hashArray[i];
			for( ; N != NULL; N = N->next)
			{
				if(N->item != NULL)
					fprintf(out ,"%s %s\n", N->key, N->item);
			}
		}
	}
}
