#ifndef PROCESS_H
#define PROCESS_H

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>
#include "../tokenize/makeArgs.h"
#include "../pipes/pipes.h"
#include "../tokenize/makeArgs.h"
#include "../utils/myUtils.h"
#include "../linkedList/linkedList.h"
#include "../linkedList/command.h"
#include "../linkedList/listUtils.h"

int checkAndParseAlias(LinkedList * aliasList, char * command);
void parseCreateAlias(char *s, LinkedList * aliasList);
void forkIt(char ** argv);
int cleanCommand(char * s, LinkedList * history, LinkedList * aliasList);
int evaluate(char * tempTok, LinkedList * history, LinkedList * aliasList, char * temp);
void handleCommand(char * s);
void handlePoshrc(int * HC, int * HFC, LinkedList * list);
void setVars(char * s, int * HC, int * HFC);
int hasEquals(char * s);

#endif
