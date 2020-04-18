#ifndef PIPES_H
#define PIPES_H

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include "../tokenize/makeArgs.h"

int containsPipe(char *s);
char ** parsePrePipe(char *s, int * preCount);
char ** parsePostPipe(char *s, int * postCount);
char ** parsePostPipe2(char *s, int * postCount2);
void pipeIt(char ** prePipe, char ** postPipe);
void pipeItTwice(char ** prePipe, char ** postPipe, char ** postPipe2 );

#endif 
