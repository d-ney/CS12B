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
void freeNode(Node* pN){
if( pN!=NULL && *pN!=NULL ){
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
	  freeNode(N);
	  return NULL;

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D)
{
	if( D->numItems == 0)
		return 1;
	else
		return 0;

}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D)
{
	return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k)
{
	Node N;
	N = find(k, D);

	      if(N != NULL)
	      {
	    	  freeNode(N);
	    	  return N->item;
	      }
	      else
	      {
	    	  freeNode(N);
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

		fprintf(stderr, "cannot insert duplicate keys");
		exit(EXIT_FAILURE);

	}


D->numItems++;

int i = size(D);
 if( i == 0)
  {
	 Node N;
	 N-> key = k;
	 N-> item = v;
	 N->next = D->head;
	 D->head = N;
	 freeNode(N);
  }

{
 Node N;
 N-> key = k;
 N-> item = v;
 N->next = D->head;
 D->head = N;
 freeNode(N);
}
}



// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k)
{
	if( lookup(D, k)== NULL)
	{
		fprintf(stderr,
		"cannot delete non-existent key\n");
		exit(EXIT_FAILURE);
	}

		D->numItems--;

		Node N = malloc(sizeof(NodeObj));
		  N->next = D->head;
		  N->item = NULL;
		  N->key = NULL;
		  if(strcmp(N->next->key, k) == 0)
		  {
			  D->head = N->next->next;
		  }
		  while(N->next != NULL)
		  {

			   if(strcmp(N->next->key, k) == 0)
			  {
				 Node T = N->next;
				 N->next = T->next;
			  }
			  else
			  {
				  N = N->next;
			  }
	  }
		  freeNode(N);

}


// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D)
{
	D->head = NULL;
	D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D)
{
	for ( ; N!=NULL; N=N->next)
		{
			Node N = D-> head;
			fprintf(out, "%s %s\n", N->key, N->item);
		}
	freeNode(N);
}


}

