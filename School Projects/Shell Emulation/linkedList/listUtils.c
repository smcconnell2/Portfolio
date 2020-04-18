#include "listUtils.h"
#include "command.h"

Node * buildNode(FILE * in, void *(*buildData)(FILE * in) )
{
   Node * nn = (Node*)calloc(1,sizeof(Node));
   nn-> data = buildData(in);
   nn-> next = NULL;

   return nn;
}// end buildNode

Node * buildNodeAlias(void * name, void * cmd, void *(*buildAliasData)(void * name, void * cmd))
{
	Node * nn = (Node*)calloc(1,sizeof(Node));
	nn-> data = buildAliasData(name, cmd);
	nn-> next = NULL;
	
}// end buildNodeAlias

Node * buildNodeString(void * pnt, void *(*buildData)(void * pnt)){

	Node * nn = (Node*)calloc(1,sizeof(Node));
	nn-> data = buildData(pnt);
	nn-> next = NULL;

}// end buildNodeString


void sort(LinkedList * theList, int (*compare)(const void *, const void *))
{
   if((theList-> size) != 0)
   {
	int x;
	Node * start = theList-> head;
	Node * min = NULL;
	void * temp = NULL;
	Node * walk = NULL;
	for(x = 0; x< (theList-> size) -1; start = start-> next, x ++)
	{
	   min = start;
	   for(walk = start-> next; walk!= NULL; walk = walk->next)
	   {
		if(compare(walk->data, min->data) < 0)
		{
		   min = walk;
		}// end if

	   }// end for
	
		temp = min->data;
		min->data = start->data;
		start->data = temp;

	}// end for
   }// end if
}// end sort


void buildList(LinkedList * myList, FILE * fin, int total, void * (*buildData)(FILE * in))
{

   int x;
   for(x = 0; x < total; x ++)
   {
	
	Node * nn = buildNode(fin, buildData);
	addLast(myList, nn);

   }// end for 
   myList-> size = total;

}// end buildList


void printListFile(const LinkedList * theList, FILE * fout, void (*printData)(FILE * fout, void *)) 
{

   if(theList-> size == 0)
   {
	printf("Empty list\n");
   }// end 

   else
   {
	Node * cur = theList-> head;
	while(cur != NULL)
	{
	   printData(fout, cur-> data);
	   cur = cur-> next;
	}// end while
   }// end else

}// end printList
