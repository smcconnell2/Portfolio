#include "pipes.h"

int containsPipe(char *s)
{
	int size = strlen(s);
	int count = 0, x;

	for(x = 0; x < size; x ++){
		if(s[x] == '|'){
			count ++;
		}// end if
	}// end for
	
	return count;

}// end containsPipe


char ** parsePrePipe(char *s, int * preCount)
{
	*preCount = 0;

	char ** args;
	int amount, x;

	char * temp = (char*)calloc(strlen(s) + 1, sizeof(char));
	strcpy(temp, s);
	amount = makeargs(temp, &args);
	free(temp);
	temp = NULL;

	char ** retArgs; 

	for(x = 0; x < amount && args[x][0] != '|'; x++){
		(*preCount)++;
	}// end for
	
	retArgs = (char **)calloc((*preCount)+1, sizeof(char*));
	
	for(x = 0; x < (*preCount); x ++){
		
		retArgs[x] =(char*)calloc(strlen(args[x])+1, sizeof(char));
		
		strcpy(retArgs[x], args[x]);
	}// end for

	clean(amount, args);
	retArgs[x] = NULL;

	return retArgs;
}// end parsePrePipe


char ** parsePostPipe(char *s, int * postCount)
{
	*postCount = 0;

	char ** args;
	int amount, x;

	char * temp = (char*)calloc(strlen(s) + 1, sizeof(char));
	strcpy(temp, s);
	amount = makeargs(temp, &args);
	free(temp);
	temp = NULL;

	char ** retArgs; 
	int pipe = 0, pipe2 = 0, indexStart = 0;

	for(x = 0; x < amount; x++){
		if(!pipe2){
			if(pipe && args[x][0] == '|'){
				pipe2 = 1;
			}// end if
			if(pipe && args[x][0] != '|'){
				(*postCount)++;
			}// end if
			if(!pipe && args[x][0] == '|'){
				pipe = 1;
				indexStart = x+1;
			}// end if
		}// end if
	}// end for

	retArgs = (char **)calloc((*postCount)+1, sizeof(char*));

	int y;
	for(x = indexStart, y = 0; y < (*postCount); x ++, y ++){
		retArgs[y] = (char*)calloc(strlen(args[x])+1, sizeof(char));
		strcpy(retArgs[y], args[x]);
	}// end for

	retArgs[y] = NULL;

	clean(amount, args);

	return retArgs;
}// end parsePostPipe

char ** parsePostPipe2(char *s, int *postCount2){

	*postCount2 = 0;

	char ** args;
	int amount, x;

	char * temp =(char*)calloc(strlen(s)+1,sizeof(char));

	strcpy(temp,s);
	amount = makeargs(temp, &args);
	free(temp);
	temp = NULL;

	char ** retArgs;
	int pipe1 = 0, pipe2 = 0, indexStart = 0;

	for(x = 0; x < amount; x ++){
		if(pipe1 && pipe2){
			(*postCount2)++;
		}// end if
		if(args[x][0] == '|' && pipe1){
			pipe2 = 1;
			indexStart = x+1;
		}// end if
		if(args[x][0] == '|' && !pipe1){
			pipe1 = 1;
		}// end if
	}// end for

	retArgs =(char**)calloc((*postCount2)+1,sizeof(char*));

	int y; 
	for(y = 0, x = indexStart; y < (*postCount2); x++, y++){
		retArgs[y] =(char*)calloc(strlen(args[x])+1,sizeof(char));
		strcpy(retArgs[y], args[x]);
	}//end for

	retArgs[y] = NULL;
	clean(amount, args);

	return retArgs;
}// end parsePostPipe2


void pipeIt(char ** prePipe, char ** postPipe)
{
	pid_t pid;
	int fd[2], res, status;

	pid = fork();

	if(pid != 0)
	{
   		waitpid(pid,&status,0);
	}// end if AKA parent
	else
	{
		int fd [2];
		res = pipe(fd);

		if(res < 0)
		{
			printf("Pipe Failure\n");
			exit(-1);
		}// end if

		if(fork() == 0){
		    close(fd[0]);
		    dup2(fd[1], 1);
		    close(fd[1]);
		    execvp(prePipe[0], prePipe);
		}// end if
		else{
		    close(fd[1]);
		    dup2(fd[0],0);
		    close(fd[0]);
		    execvp(postPipe[0], postPipe);
		}// end else

	}// end else AKA child
}// end pipeIt


void pipeItTwice(char ** prePipe, char ** postPipe, char ** postPipe2 )// for a two pipe argument
{
	pid_t pid;
	int res, status;

	pid = fork();

	if(pid != 0)
	{
   		waitpid(pid,&status,0);
	}// end if AKA parent
	else
	{
		int fd1 [2], fd2 [2];
		int input = 0, output = 1;
		int forkChild = 0; 

		res = pipe(fd1);

		if(res < 0)
		{
			printf("Pipe Failure\n");
			exit(-1);
		}// end if

		if(fork() == forkChild){
		    dup2(fd1[output], output);
			close(fd1[input]);
		    close(fd1[output]);
		    execvp(prePipe[0], prePipe);
		}// end if

		res = pipe(fd2);
		if(res < 0)
		{
			printf("Pipe Failure\n");
			exit(-1);
		}// end if

		if(fork() == forkChild){
			dup2(fd1[input], input);
			dup2(fd2[output], output);
			close(fd1[input]);
			close(fd1[output]);
			close(fd2[output]);
			close(fd2[input]);
			execvp(postPipe[0], postPipe);
		}// end if

		dup2(fd2[input], input);
		close(fd2[input]);
		close(fd2[output]);
		close(fd1[input]);
		close(fd1[output]);
		execvp(postPipe2[0], postPipe2);

	}// end else AKA child
}// end pipeIt
