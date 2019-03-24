//-----------------------------------------------------------------------------
// Sort.c
//
// compile: gcc -std=c99 -o Sort Sort.c
//
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include<math.h>
#include<string.h>

void printArray(char** A, int n){
   int i;
   for(i=0; i<n; i++){
      printf("%s ", A[i]);
   }
   printf("\n");
}

void swap(char** A, int i, int j){
   char* temp;
   temp = A[i];
   A[i] = A[j];
   A[j] = temp;
}

int Partition(char** A, int p, int r){
   int i, j;
   char* x;
   x = A[r];
   i = p;
   for(j=p; j<r; j++){
      if( strcmp(A[j], x) <= 0){
         i++;
         swap(A, i, j);
      }
   }
   swap(A, i+1, r);
   return(i);
}

void QuickSort(char** A, int p, int r){
   int q;
   if( p<r ){
      q = Partition(A, p, r);
      QuickSort(A, p, q-1);
      QuickSort(A, q+1, r);
   }
}

int main(int argc, char** argv){
   int n=4;
   char* A[] = {"banana", "durian", "apple", "cantaloupe"};

   printArray(A, n);

   //BubbleSort(A, n);
   //SelectionSort(A, n);
   //InsertionSort(A, n);
   //MergeSort(A, 0, n-1);
   QuickSort(A, 0, n-1);
   //CountingSort(A, n, 9);


   printArray(A, n);

   return(EXIT_SUCCESS);
}
