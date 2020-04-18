#include "command.h"
#include "./listUtils.h"


void printTypeCommand(void * passedIn)
{
	Command * temp = (Command *)passedIn;
   	printf("%s\n", temp->ltrs);

}// end printType


void printToFile(FILE * fout, void * passedIn){
    Command * temp = (Command *) passedIn;
    fprintf(fout, "%s\n",temp->ltrs);
}// end printToFile


void * buildTypeCommand(FILE * fin)
{
    char temp[MAX];
    Command * aCommand = (Command *)calloc(1, sizeof(Command));

    fgets(temp, 100, fin);
    strip(temp);
    aCommand->ltrs = (char *)calloc(strlen(temp)+1, sizeof(char));
    strcpy(aCommand->ltrs, temp);
   
    aCommand->len = strlen(temp);

    return aCommand;
}// end buildType

void * buildTypeCommandString(void * pnt){

    char * s = (char*) pnt;

    Command * aCommand = (Command *)calloc(1, sizeof(Command));

    aCommand->ltrs = (char *)calloc(strlen(s)+1, sizeof(char));
    strcpy(aCommand->ltrs, s);
    aCommand->len = strlen(s);

    return aCommand;
}// end buildTypeCommandChar


void * buildTypeCommand_Prompt()
{
    char temp[MAX];
    Command * aCommand = (Command *)calloc(1, sizeof(Command));

    printf("Please enter a command ");
    fgets(temp, 100, stdin);
    strip(temp);
    aCommand->ltrs = (char *)calloc(strlen(temp)+1, sizeof(char));
    strcpy(aCommand->ltrs, temp);

   aCommand->len = strlen(temp);

    return aCommand;
}// end buildType_Prompt


int compareCommands(const void * p1, const void * p2)
{
   int res;
   Command *left = (Command *)p1;
   Command *right = (Command *)p2;
      
   return strcmp(left->ltrs, right->ltrs);
}// end compare

void cleanTypeCommand(void * ptr)
{
   
   Command * temp = (Command *)ptr;
   free(temp->ltrs);
   temp->ltrs =  NULL;
   
   free(temp);
   
}// end cleanType
