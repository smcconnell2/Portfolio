#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "./alias.h"

struct node
{
    void * data;
    struct node * next;
};
typedef struct node Node;


struct linkedlist
{
    Node * head;
    int size;
};
typedef struct linkedlist LinkedList;


LinkedList * linkedList();
void findAndRemove(LinkedList * theList, void * name, int (*equals)(void * alias, void * name), void (*removeData)(void *));

void * find(LinkedList * theList, void * name, int (*equals)(void * alias, void * name));
void setData(LinkedList * theList, void * name, void * cmd, void (*set)(void * alias, void * cmd), int (*equals)(void * alias, void * name));

void * getIndexOpposite(LinkedList * theList, int number);
void * getLast(LinkedList * theList);

void addLast(LinkedList * theList, Node * nn);
void addFirst(LinkedList * theList, Node * nn);

void removeFirst(LinkedList * theList, void (*removeData)(void *));
void removeLast(LinkedList * theList, void (*removeData)(void *));
void removeItem(LinkedList * theList, Node * nn, void (*removeData)(void *), int (*compare)(const void *, const void *));

void clearList(LinkedList * theList, void (*removeData)(void *));
void printList(const LinkedList * theList, void (*convertData)(void *));

LinkedList * cleanList(LinkedList * theList, void (*removeData)(void *));

#endif // LINKEDLIST_H
