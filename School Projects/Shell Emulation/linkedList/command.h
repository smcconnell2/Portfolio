
#ifndef COMMAND_H_
#define COMMAND_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../utils/myUtils.h"

struct command
{
   char * ltrs;
   int len;
};

typedef struct command Command;

void printTypeCommand(void * passedIn0);
void printToFile(FILE * fout, void * passedIn);
void * buildTypeCommandString(void * pnt);
void cleanTypeCommand(void * ptr);
void * buildTypeCommand(FILE * fin);
void * buildTypeCommand_Prompt();
int compareCommands(const void * p1, const void * p2);

#endif /* WORD_H_ */
