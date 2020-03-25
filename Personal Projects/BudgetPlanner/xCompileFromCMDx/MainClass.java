//package run;

//import utils.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

//import budget.*;

/**
 * This class will prompt user for new budget or manage current.
 * If current bank account the users password must match previous.
 * 
 * @author Steven McConnell
 *
 */
public class MainClass {

	private static Budget budget;
	//private static MonthBudget curMonth;
	//private static Category curCategory;
	private static RunProgram run;
	
	public static void main(String[] args) {
		
		int choice = 0;
		boolean valid = false;
		
		System.out.println("			" + Prompts.title);// working title

		while(!valid) {
			Prompts.initialPrompt();
			choice = ScannerClass.readInt();
			//use regex
			//set valid
			valid = true;// fix.................
		}
		
		
		switch(choice){
			case(1):
				System.out.println("Lets create a new budget...");
				budget = createNewBudget();
				run = new RunProgram(budget);
				run.helpRunProgram();
				
				break;
			case(2):
				boolean validName = false;
				String budgetName = "";
				
				while(!validName) {
					System.out.println("What is the name of the budget you wish to load?");
					budgetName = ScannerClass.readString();// regex********************************
					validName = true;// use regex here to ensure no weirdness
					if(!validName) {
						System.out.println("Please enter a name only, no file path or extension is permitted.");
					}
				}
				
				
					try {
						FileInputStream file = new FileInputStream("./"+ budgetName);
						ObjectInputStream in = new ObjectInputStream(file);
			
						Budget loadedBudget = (Budget) in.readObject();
						
						in.close();
						file.close();
						budget = loadedBudget;
						
						System.out.println("Budget was loaded successfully.");
						
					}
					catch(IOException e) {
						System.out.println("No saved budget exists.");
						System.out.println("Beginning new budget.");
					}
					catch(Exception e) {
						System.out.println("Error while loading occured.");
						System.out.println("Beginning new budget.");
					}
					
					run = new RunProgram(budget);
					run.helpRunProgram();
				break;
		}	
	}
	

	/**
	 * This method initializes a yearly budget with year
	 * and amount for that year then returns the budget
	 * reference.
	 * @param budget
	 * @return
	 */
	public static Budget createNewBudget() {
		double amount = 0;
		String name = "";
		
		System.out.println("What is your yearly budget?");
		amount = ScannerClass.readDouble();// use regex++++++++++++++++++
		amount = Round.roundToHundredth(amount);
		
		budget = new Budget(amount);
		
		System.out.println("Name your Budget:");
		name = ScannerClass.readString(); // use regex here...............................
		budget.setName(name);
		
		return budget;
	}
}
