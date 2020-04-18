#include "alias.h"
#include "./listUtils.h"


void printTypeAlias(void * passedIn)
{
	Alias * temp = (Alias *)passedIn;
   	printf("%s = %s\n", temp->name, temp->cmd);

}// end printType

void * buildTypeAliasString(void * nameVoid, void * cmdVoid){

    char * name = (char*) nameVoid;
    char * cmd = (char*) cmdVoid;

    Alias * aAlias = (Alias *)calloc(1, sizeof(Alias));

    aAlias->name = (char *)calloc(strlen(name)+1, sizeof(char));
    strcpy(aAlias->name, name);
    
    aAlias->cmd = (char *)calloc(strlen(cmd)+1, sizeof(char));
    strcpy(aAlias->cmd, cmd);

    return aAlias;
}// end buildTypeAliasChar

int equals(void * aliasVoid, void * nameVoid)
{
    char * name = (char*) nameVoid;
    Alias * alias = (Alias*) aliasVoid;

    if(strcmp(name,alias->name)==0)
    {
        return 1;
    }// end if
    
    return 0;

}// end equals

void set(void * aliasVoid, void * cmdVoid)
{
    Alias * alias = (Alias*) aliasVoid;
    free(alias->cmd);
    alias->cmd = NULL;

    char * cmd1 = (char*)calloc(strlen(cmdVoid)+1,sizeof(char));
    char * cmd2 = (char*) cmdVoid;

    strcpy(cmd1,cmd2);
    alias->cmd = cmd1;

}// end set

void cleanTypeAlias(void * ptr)
{
   
   Alias * temp = (Alias *)ptr;
   free(temp->name);
   free(temp->cmd);
   temp->cmd = NULL;
   temp->name =  NULL;
   
   free(temp);
   
}// end cleanType
