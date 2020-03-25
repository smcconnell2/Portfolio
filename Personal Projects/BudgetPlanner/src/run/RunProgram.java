package run;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import budget.*;
import utils.Prompts;
import utils.Round; 
import utils.ScannerClass;

public class RunProgram {

	private static final int DISPLAY_BUDGET = 1;
	private static final int ADD_MONEY_BUDGET = 2;
	private static final int ADD_MONTH = 3;
	private static final int MANAGE_MONTHS = 4;
	private static final int SAVE_BUDGET = 5;
	private static final int QUIT_BUDGET = 6;
	
	private static final int DISPLAY_MONTH = 1;
	private static final int ADD_MONEY_MONTH = 2;
	private static final int ADD_CATEGORY = 3;
	private static final int MANAGE_CATEGORIES = 4;
	private static final int QUIT_MONTH = 5;
	
	private static final int DISPLAY_CATEGORY = 1;
	private static final int ADD_MONEY_CATEGORY = 2;
	private static final int SPEND_MONEY_CATEGORY = 3;
	private static final int CHANGE_CATEGORY_NAME = 4;
	private static final int CHANGE_CATEGORY_PRIORITY = 5;
	private static final int VIEW_CATEGORIES = 6;
	private static final int CHANGE_CURRENT_CATEGORY = 7;
	private static final int QUIT_CATEGORY = 8;
	
	
	private Budget budget;

	public RunProgram(Budget budget) {
		this.budget = budget;
	}
	
	/**
	 * helpRunProgram 
	 * @param curBudget
	 * @param curMonth
	 * @param curCategory
	 */
	public void helpRunProgram() {
	int choice = 0;
		
		while(choice != 6 ) {
			Prompts.budgetMenu();
			choice = ScannerClass.readInt();// use regex===========================
			yearBudgetSwitch(choice);
		}
	}
	
	/**
	 * getMonth() gets and returns the choice for the month that the user
	 * wishes to budget for.
	 * @return
	 */
	private int getMonth() {
		int choice = 0;

		Prompts.months();
		choice = ScannerClass.readInt(); // use regex ==================
		choice --;
		return choice;
	}
	
	
	private int getCategory(MonthBudget curMonth) {
		int choice = 0;
		boolean valid = false;
		
		while(!valid) {
			System.out.println("Which category do you want to manage:");
			System.out.println(curMonth.displayCategoryNames());
			
			choice = ScannerClass.readInt(); // use regex********************
			choice --;
			
			if(choice < 0 || choice >= curMonth.getNumberOfCategories()) {
				System.out.println("Invalid choice.");
			}
			else{
				valid = true;
			}
		}		
		return choice;
	}
	
	
	/**
	 * This method get's the amount a client would like to budget
	 * for a selected month.
	 * @param curBudget
	 * @return
	 */
	public double getAmount() {
		double amount = 0;
		boolean varified = false;
		
		while(!varified) {
			System.out.println("How much would you like to budget?");
			amount = ScannerClass.readDouble(); // use regex ==========================
			
			if((this.budget.getMoneyNotAllocated() - amount) > 0) {
				varified = true;
			}
			else {
				System.out.println("There isn't enough money in your yearly budget to allocate $" + amount);
			}	
			
		}
		return amount;
	}
	
	/**
	 * addMoney handles adding money 
	 * @param choice
	 */
	public void addMoneyYear() {
		double amount = 0;

		System.out.println("How much money would you like to add to the year budget?");
		amount = ScannerClass.readDouble(); // use regex ======================================
		this.budget.addMoney(amount);
		
		helpRunProgram();			
	}
	
	
	public void addMoneyMonth(MonthBudget curMonth) {
		boolean varified = false;
		double amount = 0;
		
		while(!varified) {
			System.out.println("How much money would you like to add to " + curMonth.getName() + "?");
			amount = ScannerClass.readDouble(); // use regex===================================
			
			if((this.budget.getMoneyNotAllocated() - amount) > 0) {
				curMonth.addMoney(amount);
				System.out.println("$" + amount + " was added to " + curMonth.getName() + ".");
				varified = true;
			}
			else {
				System.out.println("There isn't enough money in your yearly budget to allocate $" + amount + " to " + curMonth.getName() + ".");
				System.out.println("Enter a different amount? y/n");
				char choice2 = ScannerClass.readChar();
				if(choice2 != 'y') { // use regex ===========================================
					varified = true;
					System.out.println("Back to menu.");					
				}
			}	
		}
	}
	
	public void addMoneyCategory(Category curCategory, MonthBudget curMonth) {
		boolean varified = false;
		double amount = 0;
		
		while(!varified) {
			System.out.println("How much money would you like to add to " + curCategory.getName() + " for the month of " + curMonth.getName() + "?");
			amount = ScannerClass.readDouble(); // use regex ========================================
			
			if((curMonth.getMoneyNotAllocated() - amount) > 0) {
				varified = true;
				curCategory.addMoney(amount);
				System.out.println("$" + amount + " was added to " + curCategory.getName());
			}
			else {
				System.out.println("There isn't enought money in your monthly budget to allocate $" + amount + " to " + curCategory.getName() + ".");				
			}
		}
	}

	private void yearBudgetSwitch(int choice) {
		int monthChoice = 0;
		switch(choice) {
			case(DISPLAY_BUDGET):
				System.out.println(this.budget.display());
				break;
			case(ADD_MONEY_BUDGET):
				addMoneyYear();
				break;
			case(ADD_MONTH):
				double amount = 0;
				
				monthChoice = getMonth();
				amount = getAmount();
				
				this.budget.addMonth(monthChoice, amount);
				
				monthChoice = 0;
				break;
			case(MANAGE_MONTHS):
				
				int monthBudgetChoice = 0;
				monthChoice = getMonth();
				
				if(!budget.varifyMonthSpotAvailable(monthChoice)){
					while(monthBudgetChoice != 5) {
						Prompts.monthMenu();
						monthBudgetChoice = ScannerClass.readInt();// use regex and validate
						monthBudgetSwitch(monthBudgetChoice, monthChoice);
					}
				}
				else {
					System.out.println("Cannot manage a month without a budget.");
				}

				monthChoice = 0;
				
				break;
			case(SAVE_BUDGET)://*****************************************************************************************************
				// check to see if this name is repeated ****************************************************************************
				if(budget.getName().equals("")) {
					boolean varified = false;
					String name = "";
						
					while(!varified) {
						System.out.println("Enter your budget name:");
						name = ScannerClass.readString();
						
						budget.setName(name);
						varified = true;
						
						if(!varified) {
							System.out.println("Enter an appropriate budget name:");
						}
					}
					
					
				}
				
				try {
					FileOutputStream file = new FileOutputStream(budget.getName());
					ObjectOutputStream out = new ObjectOutputStream(file);
					
					out.writeObject(this.budget);
					
					out.close();
					file.close();
					
					System.out.println("Budget was saved successfully");
				}
				
				catch(Exception e) {
					System.out.println("Issue Saving Game.");
				}
				break;
			case(QUIT_BUDGET):
				break;
		}
	}
	
	private void monthBudgetSwitch(int choice, int month) {
		switch(choice) {
			case(DISPLAY_MONTH):
				System.out.println(budget.displayMonth(month));
				break;
			case(ADD_MONEY_MONTH):
				addMoneyMonth(budget.yearBudget[month]);
				break;
			case(ADD_CATEGORY):
				budget.yearBudget[month].addCategory();
				break;
			case(MANAGE_CATEGORIES):
				int categoryBudgetChoice = 0;
				int categoryChoice = 0;
				
				if(budget.yearBudget[month].getNumberOfCategories() == 0){
					System.out.println("Please add Categories.");
				}
				else {
					categoryChoice = getCategory(budget.yearBudget[month]);
					
					while(categoryBudgetChoice != 8) {
						Prompts.categoryMenu();
						categoryBudgetChoice = ScannerClass.readInt();// use regex
						categoryChoice = categoryBudgetSwitch(categoryBudgetChoice, categoryChoice, month);
					}
				}
				break;
			case(QUIT_MONTH):
				break;
		}
	}	
	
	private int categoryBudgetSwitch(int choice, int category, int month) {
		int catChoice = category;
		MonthBudget mBudget = budget.yearBudget[month];
		Category curCat = mBudget.categories.get(category);
		switch(choice) {
			case(DISPLAY_CATEGORY):
				System.out.println(curCat.display());
				break;
			case(ADD_MONEY_CATEGORY):
				addMoneyCategory(curCat, mBudget);
				break;
			case(SPEND_MONEY_CATEGORY):
				double amount = 0;

				System.out.println("Amount spending for " + curCat.getName() + ": ");
				amount = ScannerClass.readDouble();
				mBudget.categories.get(category).spendMoney(amount);
				
				System.out.println(curCat.display());
				break;
			case(CHANGE_CATEGORY_NAME):
				String newName = "";
				System.out.println("New Category name for " + curCat.getName() + ":");
				newName = ScannerClass.readString(); // use regex **********************
				curCat.setName(newName);
				break;
			case(CHANGE_CATEGORY_PRIORITY):
				int newPriority = 1;
				System.out.println("New Priority for " + curCat.getName() + ":");
				newPriority = ScannerClass.readInt();
				curCat.setPriority(newPriority);
				break;
			case(VIEW_CATEGORIES):
				System.out.println(budget.yearBudget[month].displayCategoryNames());
				break;
			case(CHANGE_CURRENT_CATEGORY):
				catChoice = getCategory(budget.yearBudget[month]);
				break;
			case(QUIT_CATEGORY):
				break;
		}
		return catChoice;
	}
}
