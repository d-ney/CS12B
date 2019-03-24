//#include<stdio.h>
//#include<string.h>
//#include<stdlib.h>
//
//int CountComparisons(int* A, int n, int i){
//
//	int comp = *(A + i);
//	int less;
//
//	for(int i = 0; i < n; i++)
//	{
//		if(comp > *(A+i))
//			less++;
//	}
//
//	return less;
//}
//
//int main()
//{
//	int *A;
//
//	A = calloc(5, sizeof(int));
//
//	for(int i = 0; i < 5; i ++)
//		*(A + i) = i + 1;
//
//	printf("%d", CountComparisons(A, 4, 4));
//
//	free(A);
//}
