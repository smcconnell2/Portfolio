//package utils;
/*
import budget.*;
import month.*;
import utils.*;*/
/*
* This class is used to test other classes functionality.
* It is temporary
*
*/

public class Tester {
	public static void main(String[] args) {
		
		int choice = 0;
		
		
		System.out.println("Which test would you like to conduct?");
		System.out.println("1. Enum");
		System.out.println("2. Category");
		System.out.println("3. Month Budget");
		System.out.println("4. Year Budget");
		System.out.println("5. Test rounding");
		System.out.println("9. Exit");
		
		choice = ScannerClass.readInt();
		
		switch(choice) {
			case(1):
				enumTest();
				break;
			case(2):
				categoryTest();
				break;
			case(3):
				monthBudgetTest();
				break;
			case(4):
				yearBudgetTest();
				break;
			case(5):
				testRounding();
				break;
			default:
				System.out.println("Done Testing");
				break;
		}
		
	}
	
	public static void testRounding() {
		double amount = 55.55;
		System.out.println(amount + " rounded is: " + Round.roundToHundredth(amount));
		amount = 44.444;
		System.out.println(amount + " rounded is: " + Round.roundToHundredth(amount));
		amount = 75.11111;
		System.out.println(amount + " rounded is: " + Round.roundToHundredth(amount));
		while(amount != 1) {
			System.out.println("Enter an amount to round: ");
			amount = ScannerClass.readDouble();
			System.out.println(amount + " rounded is: " + Round.roundToHundredth(amount));
		}
		System.out.println("Done...");
	}
	
	public static void enumTest() {
		System.out.println("Month enum test.");
		
		Month newMonth = Month.JANUARY;
		System.out.println(newMonth.getAbbreviation());
		System.out.println(newMonth.getReadName());
	}
	
	public static void categoryTest() {
		int amount = 0;
		System.out.println("Category Test.");
		
		
		System.out.println("Amount Allocated? (More than $1)");
		amount = ScannerClass.readInt();
		Category one = new Category(1, "Sample Category", amount);
		one.display();
		
		System.out.println();
		System.out.println("Test deduction too large:");
		one.spendMoney(amount+1);
		
		System.out.println();
		System.out.println("Test deduction: $1");
		one.spendMoney(1);
		one.display();
		
	}
	
	public static void monthBudgetTest() {
		System.out.println("Month Budget Test.");
		System.out.println();
		
		MonthBudget monthBudget = new MonthBudget(50, Month.APRIL.getReadName());
		monthBudget.display();

		int choice = 0;
		
		while(choice != 5) {
			System.out.println("1. Add a category.");
			System.out.println("2. Display categories.");
			System.out.println("3. Add money to this month.");
			System.out.println("5. Quit");
			choice = ScannerClass.readInt();
			switch(choice) {
				case(1): 
					monthBudget.addCategory();
					break;
				case(2):
					monthBudget.display();
					break;
				case(3):
					System.out.println("How much money would you like to add?");
					double amount = ScannerClass.readDouble();
					monthBudget.addMoney(amount);
				case(5):
					System.out.println("Quitting...");
					break;
			}
		
		}
		
	}
	
	public static void yearBudgetTest() {
		System.out.println("Year budget test:");
		System.out.println();
		
		Budget budget = new Budget(500);
		budget.display();
		
		int choice = 0;
		
		while(choice != 5) {
			System.out.println("1. Add a month.");
			System.out.println("2. Display categories.");
			System.out.println("3. Add money.");
			System.out.println("5. Quit");
			choice = ScannerClass.readInt();
			/*switch(choice) {
				case(1): 
					budget.addMonth(month, moneyAllocated);
					break;
				case(2):
					monthBudget.display();
					break;
				case(3):
					System.out.println("How much money would you like to add?");
					double amount = ScannerClass.readDouble();
					monthBudget.addMoney(amount);
				case(5):
					System.out.println("Quitting...");
					break;
			}*/
			
		
		}
	}
	

}
