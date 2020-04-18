#include "linkedList.h"


LinkedList * linkedList()
{
   LinkedList * myList = (LinkedList *)calloc(1,sizeof(LinkedList));
   return myList;

}// end linkedList


void addLast(LinkedList * theList, Node * nn)
{

   if(theList-> size == 0)
   {

	addFirst(theList, nn);

   }// end if
   else
   {

	Node * cur = theList-> head; 
	int x;
	for(; cur-> next != NULL; cur = cur-> next){}// end for
	cur-> next = nn;
	theList-> size = theList-> size + 1;
   }// end else
   
   

}// end addLast


void addFirst(LinkedList * theList, Node * nn)
{

   nn-> next = theList-> head;

   theList-> head = nn;

   theList-> size = (theList-> size) + 1;

}// end addFirst


void removeLast(LinkedList * theList, void (*removeData)(void *))
{
   if(theList-> size != 0)
   {
		Node * prev = NULL;
		Node * cur = theList-> head;

		for(; cur-> next != NULL; cur = cur-> next)
		{
			prev = cur;
		}// end for

		if(prev != NULL)
		{
			prev-> next = NULL;
		}// end if

		removeData(cur-> data);
		free(cur);
		cur = NULL;
		theList-> size = (theList-> size)-1;

    }// end if

}// end removeLast

void removeFirst(LinkedList * theList, void (*removeData)(void *))
{
	if(theList-> size != 0)
	{

		Node * cur = theList->head;
		theList->head = cur->next;

		removeData(cur->data);
		free(cur);
		cur = NULL;

		theList-> size = (theList-> size)-1;
	}// end if

}// end removeFirst

void removeItem(LinkedList * theList, Node * nn, void (*removeData)(void *), int (*compare)(const void *, const void *))
{
   if(theList-> size != 0 )
   {
	Node * prev = NULL;
	Node * cur = theList-> head;
	for(; cur != NULL; cur = cur-> next)
	{
	   
	   if(compare(cur-> data, nn-> data) == 0)
	   {
		break;
	   }// end if
	   
	   prev = cur;
	}// end for

	
	if(cur != NULL)
	{
	   if(prev!=NULL)
	   {
	      prev-> next = cur-> next;
	   }// end if
	   else 
	   {
		theList-> head = cur-> next;
	   }

	   removeData(cur-> data);
	   cur-> next = NULL;
	   free(cur);
	   cur = NULL;
	   theList-> size = (theList-> size)-1;
	}// end if

   }// end if
   if(nn != NULL)
   {
	removeData(nn-> data);
	free(nn);
	nn = NULL;

   }// end if
}// end removeItem


void clearList(LinkedList * theList, void (*removeData)(void *))
{
   if(theList != NULL && theList-> head != NULL)
   {
	Node * cur = theList-> head;
	Node * cur2 = cur;
	int x; 
	for(x = 0; x < theList-> size; x ++)
	{
		removeData(cur-> data);
		cur2 = cur->next;
		free(cur);
		cur = NULL;
		cur = cur2;
	}// end for

	theList-> size = 0;

   }// end if
}// end clearList

void * getLast(LinkedList * theList){
	if(theList->size == 0){
		return NULL;
	}
	else{

		Node * cur = theList-> head;
		while(cur->next != NULL) {
			cur = cur->next;
		}// end while

		return cur->data;

	}// end if
}

void * getIndexOpposite(LinkedList * theList, int number)
{
	int x = (theList->size)-1;
	Node * cur = theList->head;
	
	if(number>x)
	{
		return NULL;
	}// end if

	for(; x > number; x --)
	{
		cur = cur->next;
	}// end for

	return cur->data;

}// end getIO



void printList(const LinkedList * theList, void (*convertData)(void *)) 
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

	   convertData(cur-> data);
	   cur = cur-> next;
	}// end while
   }// end else

}// end printList

void setData(LinkedList * theList, void * name, void * cmd, void (*set)(void * alias, void * cmd), int (*equals)(void * alias, void * name))
{
	int found = 0;
	Node * cur = theList->head;
	while(cur!=NULL)
	{
		if(equals(cur->data, name))
		{
			set(cur->data, cmd);
			found = 1;
			break;
		}// end if
		cur = cur->next;
	}// end while
}// end set

void * find(LinkedList * theList, void * name, int (*equals)(void * alias, void * name))
{

	Node * cur = theList->head;
	while(cur!=NULL)
	{
		if(equals(cur->data, name))
		{
			Alias * alias = (Alias*) cur->data;
			return alias;
		}// end if
		cur = cur->next;
	}// end while
	
	return NULL;
}// end find

void findAndRemove(LinkedList * theList, void * name, int (*equals)(void * alias, void * name), void (*removeData)(void *))
{
	Node * prev = NULL;
	Node * cur = theList->head;

	while(cur!=NULL)
	{
		if(equals(cur->data, name))
		{
			if(prev == NULL)
			{
				removeFirst(theList, removeData);
				break;
			}// end if
			else
			{
				prev->next = cur->next;
				removeData(cur->data);
				free(cur);
				cur = NULL;
				theList-> size --;
				break;
			}// end else
		}// end if
		
		prev = cur;
		cur = cur->next;
	}// end while


}// end findAndRemove


LinkedList * cleanList(LinkedList * theList, void (*removeData)(void *))
{
   clearList(theList, removeData);
   free(theList);
   return NULL;

}// end cleanList



