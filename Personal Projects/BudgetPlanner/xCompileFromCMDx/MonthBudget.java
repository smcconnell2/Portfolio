//package budget;

//import utils.ScannerClass;

import java.io.Serializable;

import java.util.*;

public class MonthBudget implements Serializable{
	
	private static final long serialVersionUID = 2L;
	private String monthName = "";
	private double moneyAllocated;
	private double moneyRemaining;// after money is spent
	private double moneyNotAllocated;// not allocated to any category
	private double moneySpent;

	public ArrayList<Category> categories = new ArrayList<Category>();
	
	public MonthBudget(double money, String monthName) {
		this.monthName = monthName;
		this.moneyAllocated = money;
		this.moneyRemaining = money; 
		this.moneyNotAllocated = money;
	}
	
	public String getName() {
		return this.monthName;
	}
	
	public double getMoneySpent() {
		calcMoneySpent();
		return this.moneySpent;
	}

	public double getMoneyAllocated() {
		return this.moneyAllocated;
	}
	
	public int getNumberOfCategories() {
		int size = 0;
		this.categories.trimToSize();
		size = this.categories.size();
		
		return size;
	}
	
	public double getMoneyRemaining() {
		calcMoneyRemaining();
		return this.moneyRemaining;
	}
	
	public double getMoneyNotAllocated() {
		calcMoneyNotAllocated();
		return this.moneyNotAllocated; 
	}
	
	public void addMoney(double amount) {
		this.moneyAllocated += amount;
		this.moneyRemaining += amount;
	}
	
	public void addCategory() {
		System.out.println("Add a new category to " + this.monthName);
		
		int priority = 0;
		String catName = "";
		double catMoneyAllocated = 0;
		
		catName = promptForCategoryName();
		priority = promptForCategoryPriority();
		catMoneyAllocated = promptForCategoryAmount();
		
		this.categories.add(new Category(priority, catName, catMoneyAllocated));
		
	}
	
	private String promptForCategoryName() {
		String name;
		boolean varified;
		
		do {
			System.out.println("What is the name of the category?");
			name = ScannerClass.readString();
			varified = compareNames(name);
			
			if(!varified) {
				System.out.println("That category already exists.");
				System.out.println("Please enter a different name.");
				System.out.println();
			}
		}while(!varified);
		
		return name;
		
	}
	
	private int promptForCategoryPriority() {
		int priority = 0;
		boolean varified = false;
		
		do {
			System.out.println("What is number representing the category priority?");
			priority = ScannerClass.readInt();
			varified = comparePriority(priority);
			if(!varified) {
				System.out.println("That priority is already designated.");
				System.out.println("Please enter a different priority.");
				System.out.println();
			}
		}while(varified == false);
		
		return priority;
	}
	
	private double promptForCategoryAmount() {
		double amountAllocated = 0;
		boolean amountVerified = false;
		
		while(!amountVerified) {
			System.out.println("How much money do you want to allocate to this category?");
			System.out.println("If you want to deal with this section at another time just enter 0.");
			amountAllocated = ScannerClass.readDouble();
			if((this.moneyRemaining - amountAllocated) > 0) {
				amountVerified = true;
				calcMoneyRemaining();
			}
			else {
				System.out.println("Insufficient funds allocated for the month.");
				System.out.println();
			}
		}
		return amountAllocated;
	}
	
	private void calcMoneySpent() {
		if(this.categories.isEmpty()) {
			
		}
		else {
			this.categories.trimToSize();
			int listSize = this.categories.size();
			double moneySpent = 0;
			
			for(int searchList = 0; searchList != listSize; searchList ++) {
				moneySpent += this.categories.get(searchList).getMoneySpent();
			}
			
			this.moneySpent = moneySpent;
		}
	}
	
	private void calcMoneyRemaining() {
			calcMoneySpent();
			this.moneyRemaining = (this.moneyAllocated - this.moneySpent);
	}
	
	private void calcMoneyNotAllocated() {
		if(!this.categories.isEmpty()) {
			
			int listSize = this.categories.size();
			double moneyAllocated4Categories = 0;
			
			for(int searchList = 0; searchList < listSize; searchList ++) {
				moneyAllocated4Categories += this.categories.get(searchList).getMoneyAllocated();
			}
			
			this.moneyNotAllocated = (this.moneyAllocated - moneyAllocated4Categories);
		}
	}
	
	private boolean compareNames(String name) {
		int listLength = getNumberOfCategories();
		boolean varified = true;
		
		for(int searchList = 0; searchList < listLength; searchList ++) {
			if(this.categories.get(searchList).getName().equals(name)) {
				varified = false;
				break;
			}
		}
		
		return varified;	
	}
	
	private boolean comparePriority(int potentialPriority) { 
		int listLength = getNumberOfCategories();
		boolean verified = true;
		
		for(int searchList = 0; searchList < listLength; searchList ++) {
			if(this.categories.get(searchList).getPriority() == potentialPriority) {
				verified = false;
				break;
			}
		}
		return verified;
	}
	
	public String displayCategoryNames() {
		String names = "";
		int size = getNumberOfCategories();
		int counter = 1;

		if(size == 0) {
			names = "No Categories";
		}
		else {
			for(int search = 0; search < this.categories.size(); search ++, counter ++) {
				names += counter + ") " + this.categories.get(search).getName();
				names += "\n";
			}
		}
		return names;
	}

	private String displayCategories() {
		String categories = "";
		
		this.categories.trimToSize();// is this necessary?.................
		
		if(this.categories.size() == 0 ) {
			categories += "\nNo categories exist for this month.";
		}
		else {
			for(int searchList = 0; searchList < this.categories.size(); searchList ++) {
				categories += this.categories.get(searchList).display();
			}
		}
		return categories;
	}
	
	public String display() {
		String month = "\n";
		month += "			Month of " + this.monthName;
		month += "\n	Budget: $" + getMoneyAllocated();
		month += "\n	Money Spent: $" + getMoneySpent();
		month += "\n	Money Remaining: $" + getMoneyRemaining();
		month += "\n	Money Available to be Allocated: $" + getMoneyNotAllocated();
		month += "\n\n	Categories:";
		month += displayCategories();
		month += "\n";
		
		return month;
	}
}
