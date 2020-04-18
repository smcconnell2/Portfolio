#include "process.h"

void handlePoshrc(int * HC, int * HFC, LinkedList * aliasList){

	int records, search;
	FILE * fin;
	fin = fopen(".poshrc", "r");

	if (fin != NULL) {

		records = countRecords(fin);
		
		char command[MAX];

		for(search = 0; search < records; search ++)
		{
			fgets(command, MAX, fin);
			strip(command);

			if(!checkAndParseAlias(aliasList, command))
			{
				if(hasEquals(command))
				{
					setVars(command, HC, HFC);//has equals and is HC / HFC
				}// end if
				else
				{
					handleCommand(command);
				}// end else
			}// end if
		}// end for

		fclose(fin);
	}

}// end 

int checkAndParseAlias(LinkedList * aliasList, char * command)
{
	if(command[0]=='a' && hasEquals(command))
	{
		parseCreateAlias(command, aliasList);
		return 1;
	}// end if
	if(strlen(command)>=8 && command[0]=='u' && command[7]==' ')
	{
		char temp[100];
		int search = 0;

		while(command[search]!=' ')
		{
			search ++;
		}// end while
		search ++;
		
		int searchTemp = 0;

		while(command[search]!='\0')
		{
			temp[searchTemp] = command[search];
			searchTemp ++;
			search ++;
		}/// end while

		temp[searchTemp] = '\0';
		
		findAndRemove(aliasList, temp, equals, cleanTypeAlias);
		return 1;
	}// end if
	return 0;
}// end checkAndParseAlias

void parseCreateAlias(char * s, LinkedList * aliasList)
{
	char tempName[100];
	char tempCmd[100];
	int search = 0;
	int nmSearch = 0, cmdSearch = 0;

	while(s[search] != ' ')// get passed "alias"
	{
		search ++;
	}// end while

	search++;// get passed 'space'
	
	while(s[search]!= '=')
	{
		tempName[nmSearch] = s[search];
		nmSearch ++;
		search ++;
	}// end while

	tempName[nmSearch] = '\0';

	search += 2;// get passed '=' and '"'

	while(s[search]!= '"' )
	{
		tempCmd[cmdSearch] = s[search];
		cmdSearch ++;
		search ++;
	}// end while

	tempCmd[cmdSearch] = '\0';

	Alias * foundAlias = (Alias*)find(aliasList, tempName, equals);

	if(foundAlias == NULL)
	{
		Node * aliasNode;
		aliasNode = buildNodeAlias(tempName, tempCmd, buildTypeAliasString);
		addFirst(aliasList, aliasNode);
	}// end if 
	else
	{
		setData(aliasList, tempName, tempCmd, set, equals); 

	}// end else
	
}// end parseAlias

int hasEquals(char * s){

	int hasEqual = 0, x;
	int length = strlen(s);

	for(x = 0; x < length && !hasEqual; x ++){
		if(s[x] == '='){
			hasEqual = 1;
		}// end if
	}// end for

	return hasEqual;
}// end hasEquals

void setVars(char * s, int * HC, int * HFC){

	int x;

	char * saveptr = NULL;
	char * tempCommand = strtok_r(s, "=", &saveptr);
	char * tempNum = strtok_r(NULL, "=", &saveptr);

	if(strcmp(tempCommand, "HISTCOUNT") == 0){
		*HC = atoi(tempNum);
	}// end if

	if(strcmp(tempCommand, "HISTFILECOUNT") == 0){
		*HFC = atoi(tempNum);
	}// end if

}// end setVars


void handleCommand(char * s)
{

	int argc, pipeCount;	
	char **argv = NULL;
	int preCount = 0, postCount = 0, postCount2 = 0;
	char ** prePipe = NULL, ** postPipe = NULL, ** postPipe2 = NULL;

	char temp [100];
	strcpy(temp, s);
	int newFile = -1;
	int saveStdOut;

	int searchTemp = 0, searchS = 0, endingIndex = 0;
	int found = 0;

	while(s[searchS]!='\0')
	{
		if(s[searchS]=='>')
		{
			endingIndex = searchS-1;
			searchS+=2;
			found = 1;
			break;
		}// end if
		searchS++;
	}// end while

	if(found)
	{
		while(s[searchS]!='\0')
		{
			temp[searchTemp] = s[searchS];
			searchTemp ++;
			searchS ++;
		}// end while
		temp[searchTemp] = '\0';
		s[endingIndex] = '\0';
		
		newFile = open(temp, O_WRONLY | O_CREAT, 0777);
		saveStdOut = dup(1);
		dup2(newFile, 1);// sending output to newFile

	}// end if

	pipeCount = containsPipe(s);
   	if(pipeCount > 0)
   	{
		   if(pipeCount == 2)
		   {
			   prePipe = parsePrePipe(s, &preCount);
			   postPipe = parsePostPipe(s, &postCount);
			   postPipe2 = parsePostPipe2(s, &postCount2);
			   pipeItTwice(prePipe, postPipe, postPipe2);
			   clean(preCount, prePipe);
			   clean(postCount, postPipe);
			   clean(postCount2, postPipe2);
			  
			}// end if
			else{
   				prePipe = parsePrePipe(s, &preCount);
   				postPipe = parsePostPipe(s, &postCount);
   				pipeIt(prePipe, postPipe);
   				clean(preCount, prePipe);
        		clean(postCount, postPipe);
			}// end else
   	}// end if pipeCount	  
   	else
   	{
   		argc = makeargs(s, &argv);
   	  	if(argc != -1)
   	  		forkIt(argv);
   	  
   	  	clean(argc, argv);
   	  	argv = NULL;
   	}

	if(found)
	{
		dup2(saveStdOut, 1);
		close(newFile);
	}// end if

}// end handleComand

int evaluate(char * tempTok, LinkedList * history, LinkedList * aliasList, char * temp)
{
	int x = 0, y = 0;
	char tempNum[100];
	int length = strlen(tempTok);

	if(tempTok[x] == '!')
	{
		x++;
		if(tempTok[x] == '!')
		{
			Command * tempCommand;
			void * tempPnt = getLast(history);

			if(tempPnt==NULL)
			{
				printf("event not found\n");
				return 0;
			}// end if

			else
			{
				tempCommand = (Command*) tempPnt;
				char * tempWord = tempCommand->ltrs;
				int search = 0;
				while(tempWord[search]!='\0'){
					temp[y] = tempWord[search];
					y++;
					search++;
				}// end while
				temp[y] = '\0';
				return 1;
			}// end else

		}// end if
		else if(tempTok[x]=='\0')
		{
			return 0;
		}// end else if
		else
		{
			y = 0;
			while(x < length){
				if(tempTok[x]> '9' || tempTok[x] < '0'){
					return 0;
				}	
				tempNum[y] = tempTok[x];
				y ++;
				x ++;
			}// end while

			tempNum[y] = '\0';
			int num = atoi(tempNum);
			void * tempPnt = getIndexOpposite(history, num);

			if(tempPnt == NULL)
			{
				return 0;
			}// end if
			
			
			Command * tempCmd = (Command*) tempPnt;
			char * tempWord = tempCmd->ltrs;
			int search = 0;
			while(tempWord[search]!='\0'){
				temp[search] = tempWord[search];
				search++;
			}// end while
	
			temp[search] = '\0';
			return 1;
		}// end else
	}// end if
	else
	{
		Alias * aliasPnt = (Alias*)find(aliasList,tempTok, equals);
		if(aliasPnt!=NULL)
		{
			strcpy(temp, aliasPnt->cmd);
		}// end if
		else
		{
			strcpy(temp, tempTok);
		}
		return 1;
	}//end else
}// end evaluate

int cleanCommand(char * s, LinkedList * history, LinkedList * aliasList)
{
	char temp[100];
	strcpy(temp, s);

	int x = 0, y = 0, z = 0;
	int length = strlen(temp);
	char tempTok[100];

	while(x < length)
	{
		while(temp[x] != ' ' && temp[x] !='\0')
		{	
			tempTok[z] = temp[x];
			z++;
			x++;
		}// end while

		tempTok[z] = '\0';
		z = 0;

		char replacement[100];
		int evalResult = evaluate(tempTok, history, aliasList, replacement);

		if(!evalResult)
		{
			return 1;
		}// end if

		int search;
		int tokLength = strlen(replacement);

		for(search = 0; search < tokLength; search ++)
		{
			s[y] = replacement[search];
			y++;
		}// end for
		
		if(temp[x] != '\0')
		{
			s[y] = ' ';
			y++;
		}// end if
		
		x++;

	}// end while
	
	s[y] = '\0';
	return 0;
}// end cleanCommand


void forkIt(char ** argv)
{
	
	int length;
	for(length = 0; argv[length] != NULL; length ++){}// get length
	length = length -1;

	
	if((strcmp(argv[length], "&")) == 0){
		
		char ** temp = (char**)calloc(length+1, sizeof(char*));
		int x;
		for(x = 0; x < length; x ++){
			
			int y = strlen(argv[x]);
			temp[x] = (char*)calloc(y+1,sizeof(char));
			strcpy(temp[x], argv[x]);
		}// end for

		temp[x] = NULL;

		int status;
		pid_t pid = fork();
		/*Parent doesn't wait because of the & in the command*/

		if(pid == 0){
			execvp(temp[0], temp);
			exit(-1);
		}// end if

		clean(length, temp);

	}// end if 

	else {

		int status;
		pid_t pid = fork();

		if(pid != 0)
			waitpid(pid, &status, 0);

		else
		{
			execvp(argv[0], argv);
			exit(-1);
		}	// end else
	}// end else

}// end forkIt

