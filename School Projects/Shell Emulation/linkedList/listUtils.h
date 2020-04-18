#ifndef LISTUTILS_H
#define LISTUTILS_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "../linkedList/linkedList.h"

#define MAX 100

Node * buildNode(FILE * in, void *(*buildData)(FILE * in) );
Node * buildNodeAlias(void * name, void * cmd, void *(*buildAliasData)(void * name, void * cmd));
Node * buildNodeString(void * pnt, void *(*buildData)(void * pnt));
void sort(LinkedList * theList, int (*compare)(const void *, const void *));
void printListFile(const LinkedList * theList, FILE * fout, void (*printData)(FILE * fout, void *));
void buildList(LinkedList * myList, FILE * fin, int total, void * (*buildData)(FILE * in));

#endif // LISTUTILS_H
