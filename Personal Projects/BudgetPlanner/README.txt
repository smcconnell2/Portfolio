ABOUT:
I made this project to venture out on my own and gain experiance
without an assignment as a quideline. 

With user input this program keeps track of your yearly budget. 

The UI walks the user through a series of options to create monthly
budgets based on a yearly income. Within each month the user specifies 
categories and allocates money for each month and category individually.
As money is spent in each category the total money spent is updated,
likewise, when money is allocated to months, total money available to 
allocate is updated. 

You can then save and load your budget.

A file called "Test" is provided as a test budget. Access it from the load
option while the application is running. 


TECHNOLOGIES:
* Trello.com
* Eclipse IDE

STAGE:
This project is complete for its purpose to work as a way for me to gain
personal experiance outside of school.

ISSUE:
* These issues are something I would fix if this application was
intended for use by others and to be a truely reliable way to 
budget money.
* user input is not run against a regex to ensure proper input
* Arithmatic is not safe for use with money. I did not ensure 
that there wouldn't be rounding errors to avoid improper amounts.

To Run from CMD:
Folder xCompileFromCMDx has all source files in one package and all packege
headers have been commented out in order to allow easy compiling from the 
CMD. 

From command prompt 
- cd into xCompileFromCMDx
- Command: javac *.java
- Command: java MainClass

