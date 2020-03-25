//package utils;


/**
 * This class handles all user prompts. 
 * May have new menus according to menu choice. 
 * May implement a switch statement to organize. 
 * @author steve
 *
 */
public class Prompts {
	public static String title = "------------Budget Planner------------";
	
	public static void initialPrompt() {
		System.out.println("1) Create a budget");
		System.out.println("2) Load Budget");
	}
	
	public static void budgetMenu() {
		System.out.println();
		System.out.println("			Year Budget Menu");
		System.out.println("1) Display Budget");
		System.out.println("2) Add Money to Yearly Budget");
		System.out.println("3) Add Month");// brings you to monthMenu
		System.out.println("4) Manage Month Budget");
		System.out.println("5) Save Budget");
		System.out.println("6) Quit");
	}
	
	public static void monthMenu() {
		System.out.println();
		System.out.println("			Month Menu");
		System.out.println("1) Display Month");
		System.out.println("2) Add Money");
		System.out.println("3) Add a Category");// brings you to categoryMenu
		System.out.println("4) Manage Categories");
		System.out.println("5) Quit Month Menu");
	}
	
	public static void categoryMenu() {
		System.out.println();
		System.out.println("			Category Menu");
		System.out.println("1) Display Category");
		System.out.println("2) Add Money");
		System.out.println("3) Spend Money");
		System.out.println("4) Change Category Name");
		System.out.println("5) Change Category Priority");
		System.out.println("6) View Categories");
		System.out.println("7) Change Current Category");
		System.out.println("8) Quit Category Menu");
	}
	
	public static void months() {
		System.out.println();
		System.out.println("1) January");
		System.out.println("2) Febuary");
		System.out.println("3) March");
		System.out.println("4) April");
		System.out.println("5) May");
		System.out.println("6) June");
		System.out.println("7) July");
		System.out.println("8) August");
		System.out.println("9) September");
		System.out.println("10) October");
		System.out.println("11) November");
		System.out.println("12) December");
	}
	

	public static void newBudget() {
		System.out.println("Make a new budget? y/n");
	}
	
	public static void newMonth() {
		System.out.println("Which month are you budgeting for?");
	}
	
	public static void newMonthManage() {
		System.out.println("Which month do you want to manage?");
	}

	public static void allocateForMonth() {
		System.out.println("How much money do you want to budget for this month");
	}
}
