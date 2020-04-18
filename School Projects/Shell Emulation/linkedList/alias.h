
#ifndef ALIAS_H_
#define ALIAS_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../utils/myUtils.h"

struct alias
{
   char * name;
   char * cmd;
};

typedef struct alias Alias;

void set(void * aliasVoid, void * cmdVoid);
int equals(void * aliasVoid, void * nameVoid);
void printTypeAlias(void * passedIn);
void * buildTypeAliasString(void * name, void * cmd);
void cleanTypeAlias(void * ptr);

#endif /* ALIAS_H */
