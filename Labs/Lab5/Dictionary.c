//-----------------------------------------------------------------------------
// Dictionary.c
// Main code for Dictionary ADT
// Author: Dillon Ney ID # 1705097
//-----------------------------------------------------------------------------



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


//DictionaryObj
typedef struct DictionaryObj
{
int numItems;
Node head;

} DictionaryObj;

//Dictionary
typedef struct DictionaryObj* Dictionary;

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void)
{
	Dictionary D = malloc(sizeof(DictionaryObj));
	D->numItems = 0;
	D->head = NULL;
	return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD)
{
	if( pD!=NULL && *pD!=NULL )
	{
	free(*pD);
	*pD = NULL;
	}
}

//helper function
//finds Node that has same key as input

Node find(char* k, Dictionary D)
{
	Node N = D->head;

	  for( ; N!=NULL; N=N->next)
	  {
		 if (strcmp(N->key, k) == 0)
			return N;

	  }
	  return NULL;
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

	return(D->numItems==0);

}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D)
{
	if( D==NULL )
	{
	      fprintf(stderr,
	         "Dictionary Error: calling size() on NULL Dictionary reference\n");
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
	         "Dictionary Error: calling insert() on NULL Dictionary reference\n");
	      exit(EXIT_FAILURE);
	}

	Node N = find(k, D);

	      if(N != NULL)
	      {
	    	  return N->item;
	      }
	      else
	      {
	    	  return NULL;
	      }
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v)
{
	if(lookup(D, k) != NULL)
	{

		fprintf(stderr, "Error: key collision");
		exit(EXIT_FAILURE);

	}

	 Node N = newNode(k, v);
	 N->next = D->head;
	 D->head = N;
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
	         "Dictionary Error: calling delete() on NULL Dictionary reference\n");
	      exit(EXIT_FAILURE);
	}

	D->numItems --;

	Node T = D->head;
	Node P = newNode(NULL, NULL);

	if(lookup(D, k)!= NULL)
	{
		 if (T != NULL && strcmp(T->key, k) == 0)
			{
				D->head = T->next;
				return;
			}

			while (T != NULL && strcmp(T->key, k) != 0)
			{
				P = T;
				T = T->next;
			}

			if (T == NULL)
				return;

			P->next = T->next;
	}
	else
	{
		fprintf(stderr,
			         "Dictionary Error: key not found\n"
				);
		exit(EXIT_FAILURE);
	}
}


// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D)
{
	while( D->numItems > 0 )
	{
		   Node N = D->head;
		      D->head = D->head->next;
		      N->next = NULL;
		      freeNode(&N);
		      D->numItems--;
	}

	D->head = NULL;
}

void printHelper(FILE* out, Dictionary D, Node N)
{
	if(N == NULL)
	{
	      return;
	}

	   printHelper(out, D, N->next);
	   fprintf(out, " %s %s\n",N->key, N->item);
}
// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D)
{

	if( D==NULL )
	{
	      fprintf(stderr,
	         "Dictionary Error: calling printDictionary() on NULL"
	         " Dictionary reference\n");
	}

	printHelper(out, D, D->head);

	//freeNode(&N);
}







