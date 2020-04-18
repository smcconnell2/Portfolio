all:	lab7

lab7:	cscd340Lab7.c ./tokenize/makeArgs.o ./utils/myUtils.o ./process/process.o ./pipes/pipes.o ./linkedList/linkedList.o ./linkedList/command.o ./linkedList/listUtils.o ./linkedList/alias.o
	gcc -g cscd340Lab7.c ./tokenize/makeArgs.o ./utils/myUtils.o ./process/process.o ./pipes/pipes.o ./linkedList/linkedList.o ./linkedList/command.o ./linkedList/listUtils.o ./linkedList/alias.o -o lab7

makeArgs.o:	./tokenize/makeArgs.c ./tokenize/makeArgs.h
	gcc -g -c ./tokenize/makeArgs.c

myUtils.o:	./utils/myUtils.o ./utils/myUtils.h
	gcc -g -c ./utils/myUtils.c

process.o:	./process/process.c ./process/process.h
	gcc -g -c ./process/process.c	

pipes.o:	./pipes/pipes.c ./pipes/pipes.h
	gcc -g -c ./pipes/pipes.c

linkedList.o: ./linkedList/linkedList.c ./linkedList/linkedList.h
	gcc -g -c ./linkedList/linkedList.c

listUtils.o: ./linkedList/listUtils.c ./linkedList/listUtils.h
	gcc -g -c ./linkedList/listUtils.c

command.o: ./linkedList/command.c ./linkedList/command.h
	gcc -g -c ./linkedList/command.c

alias.o: ./linkedList/alias.c ./linkedList/alias.h
	gcc -g -c ./linkedList/alias.c


clean:
	rm ./linkedList/linkedList.o
	rm ./linkedList/alias.o
	rm ./linkedList/command.o
	rm ./pipes/pipes.o	
	rm ./utils/myUtils.o
	rm ./process/process.o
	rm ./tokenize/makeArgs.o
	rm lab7

