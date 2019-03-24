//-----------------------------------------------------------------------------
// BSTsort.c
// This is not an ADT, just data types and helper functions for the
// BSTsort() function.
//
// compile: gcc -std=c99 -o BSTsort BSTsort.c
//
//-----------------------------------------------------------------------------

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
// types and methods to maintain Tree object

// NodeObj
typedef struct NodeObj{
   char* data;
   struct NodeObj* left;
   struct NodeObj* right;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// Node constructor
Node newNode(char* x){
   Node N = malloc(sizeof(NodeObj));
   N->data = x;
   N->left = N->right = NULL;
   return N;
}

// freeNode()
// Node destructor
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL){
      free(*pN);
      *pN = NULL;
   }
}

// TreeObj
typedef struct TreeObj{
   Node Root;
} TreeObj;

// Tree
typedef TreeObj* Tree;

// newTree()
Tree newTree(){
   Tree T = malloc(sizeof(TreeObj));
   T->Root = NULL;
   return T;
}

// freeSubTree()
// This is the recursive part of the Tree destructor, which follows.  Observe
// that it is essentialy postOrderTreeWalk, where the "processing" of a Node
// is to free it.
void freeSubTree(Node R){
   if( R!=NULL ){
      freeSubTree(R->left);
      freeSubTree(R->right);
      freeNode(&R);
   }
}

// freeTree()
// Tree destructor
void freeTree(Tree* pT){
   if( pT!=NULL && *pT!=NULL){
      freeSubTree( (*pT)->Root );
      free(*pT);
      *pT = NULL;
   }
}


// helper functions for BSTSort -----------------------------------------------

// insert()
// See inline comments for an explanation of this algorithm
void insert(Tree T, char* k){
   Node N = newNode(k);
   Node P = NULL;
   Node C = T->Root;
   while( C!=NULL ){ /* Parent and Child walk down the tree until C occupies */
      P = C;         /* the (NULL) position where the new Node N belongs     */
      C = ( strcmp(k, C->data) < 0 )?(C->left):(C->right);
   }
   if( P==NULL ) T->Root = N; /* T was initially empty so N becomes the Root */
   else if( strcmp(k, P->data) < 0 ) P->left = N;  /*  otherwise N becomes the left     */
   else P->right = N;                   /*  or right child of P              */
}

// subTreeCopy()
// This is the recursive part of the tree copy operation (which follows).
// Observe that it is really the inOrderTreeWalk algorithm, where the
// "processing" of a Node is to copy it's contents into an array.  Note
// that the array index is passed by reference so each recursive invocation
// can increment that index for use in the next invocation.
void subTreeCopy(Node R, char** A, int* pIndex){
   if( R!=NULL ){
      subTreeCopy(R->left, A, pIndex);
      A[(*pIndex)++] = R->data;
      subTreeCopy(R->right, A, pIndex);
   }
}

// inOrderTreeCopy()
void inOrderTreeCopy(Tree T, char** A){
   int Index = 0;
   subTreeCopy(T->Root, A, &Index);
}

// swap
void swap(char** A, int i, int j){
   char* temp = A[i];
   A[i] = A[j];
   A[j] = temp;
}

// BSTsort()
// Sorts an int array A of length n
void BSTsort(char** A, int n){
   int i, seed = 10001;

   /* Create new empty BST */
   Tree T = newTree();

   /* Randomize input array */
   srand(seed);
   for(i=n-1; i>0; i--) swap(A, i, rand()%(i+1));

   /* Insert array contents into BST */
   for(i=0; i<n; i++) insert(T, A[i]);

   /* Do an in order tree walk, copying contents back into array */
   inOrderTreeCopy(T, A);

   freeTree(&T);
}


// main()
// Simple test of BSTsort()
int main(int argc, char* argv[]){
   int i, n = 9;
   char* A[] = {"banana", "apple", "durian", "cantaloupe", "orange", "guava", "papaya", "eggplant", "cumquat"};

   // print unsorted array
   for(i=0; i<n; i++){
      printf("%s ", A[i]);
   }
   printf("\n");

   // sort array
   BSTsort(A, n);

   // print sorted array
   for(i=0; i<n; i++){
      printf("%s ", A[i]);
   }
   printf("\n");

   return EXIT_SUCCESS;
}
