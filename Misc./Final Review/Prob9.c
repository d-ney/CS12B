//#include"stdio.h"
//#include"stdlib.h"
//#include<assert.h>
//
//typedef struct NodeObj{int item;struct NodeObj* next;} NodeObj;typedef NodeObj* Node;
//
//Node newNode(int item)
//{
//Node N = malloc(sizeof(NodeObj));
//assert(N!=NULL);
//N->item = item;
//N->next = NULL;
//return(N);
//
//}
//int sumList(Node H)
//{
//	if(H->next == NULL)
//		return H->item;
//	int sum = H->item;
//	return sum + (sumList(H->next));
//}
//
//int main()
//{
//	Node head = newNode(1);
//	head->next = newNode(2);
//	head->next->next = newNode(3);
//	printf("%d", sumList(head));
//}
