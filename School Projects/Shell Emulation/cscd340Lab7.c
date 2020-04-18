#include "./utils/myUtils.h"
#include "./process/process.h"
#include "./linkedList/linkedList.h"
#include "./linkedList/command.h"
#include "./linkedList/listUtils.h"

int main()
{
	LinkedList * aliasList = linkedList();

	int HISTCOUNT = 10, HISTFILECOUNT = 20;
	int numCommands = 0;

	handlePoshrc(&HISTCOUNT, &HISTFILECOUNT, aliasList);

	LinkedList * history = linkedList();
	FILE * fin = fopen(".posh_history", "r");
	if (fin != NULL) {
		numCommands = countRecords(fin);
		buildList(history,fin, numCommands, buildTypeCommand);
		fclose(fin);
		

		while(numCommands > HISTCOUNT){
			removeFirst(history, cleanTypeCommand);
			numCommands --;
		}// end if
	}	

	char s[MAX];

	printf("command?: ");
	fgets(s, MAX, stdin);
	strip(s);
	
	while(strcmp(s, "exit") != 0)
	{
		int historyCmd = 0;
		if(strcmp(s, "history") == 0)
		{
			printList(history, printTypeCommand);
			historyCmd = 1;
		}// end if
		int error = 0;
		int checkedAlias = checkAndParseAlias(aliasList, s);
	
		if(!checkedAlias && !historyCmd)
		{
			error = cleanCommand(s, history, aliasList);
		}// end if
		
		if(!error){
			
			Command * tempCommand;
			void * tempPnt = getLast(history);
			if(tempPnt!=NULL){
				tempCommand = (Command*) tempPnt;
			}// end if

			if(tempPnt == NULL || strcmp(s,tempCommand->ltrs)!=0 ){ // ensure history doesn't store duplicate data
				
				Node * nn = buildNodeString(s, buildTypeCommandString);
				addLast(history, nn);
				if(history->size > HISTCOUNT){
					removeFirst(history, cleanTypeCommand); // ensure linkedList of stored history doesn't exceed HISTCOUNT
				}// end if

			}// end if
			if(!checkedAlias && !historyCmd)
			{
				handleCommand(s);
			}// end if
		}// end if
		else
		{
			printf("event not found\n");// error message

		}// end else
		
		printf("command?: ");
		fgets(s, MAX, stdin);
		strip(s);

	}// end while

	FILE * fout = fopen(".posh_history", "a");
	printListFile(history, fout, printToFile);

	fclose(fout);

	history = cleanList(history, cleanTypeCommand);
	aliasList = cleanList(aliasList, cleanTypeAlias);

	LinkedList * historyFile = linkedList();
	fin = fopen(".posh_history", "r");
	numCommands = countRecords(fin);
	buildList(historyFile,fin, numCommands, buildTypeCommand);
	
	fclose(fin);

	while(numCommands > HISTFILECOUNT){
		removeFirst(historyFile, cleanTypeCommand);
		numCommands --;
	}// end if

	FILE * fout2 = fopen(".posh_history", "w");
	printListFile(historyFile, fout2, printToFile);

	fclose(fout2);

	historyFile = cleanList(historyFile, cleanTypeCommand);

	
	return 0;
}// end main

