package budget;
import java.io.Serializable;

import month.*;


/**
 * Budget contains two arrays, one to hold every months budget and one to
 * hold the months enum. A month is only assigned to both when a client creates
 * a budget for that month.
 * The moneyAllocated variable holds money allocated for the entire year.
 * @author steve
 *
 */
/*
 * Constructor takes an initial value to set money allocated in subBudget. 
 */



public class Budget implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name = "";
	private boolean budget = false;// specify what this is for====================
	private Month[] monthTrack = new Month[12]; 
	public MonthBudget[] yearBudget = new MonthBudget[12];
	private double moneyAllocated;
	private double moneySpent;
	private double moneyRemaining; // after money is spent
	private double moneyNotAllocated; // not allocated to any month
	
	public Budget(double moneyAllocated) {
		this.moneyAllocated = moneyAllocated;
		System.out.println("Money allocated for the year: " + this.moneyAllocated);
		System.out.println();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		if(name.equals("")) {
			System.out.println();
			System.out.println("Name has not been set yet.");
		}
		return this.name;
	}
	
	public double getMoneyAllocated() {
		return this.moneyAllocated;
	}
	
	public double getMoneyRemaining() {
		calcMoneyRemaining();
		return this.moneyRemaining;
	}
	
	public double getMoneySpent() {
		calcMoneySpent();
		return this.moneySpent;
	}
	
	public double getMoneyNotAllocated() {
		calcMoneyNotAllocated();
		return this.moneyNotAllocated;
	}
	
	public void addMoney(double amount) {
		this.moneyAllocated += amount;
	}
	
	private void calcMoneyNotAllocated() {
		double totalAllocated4Months = 0;
		
		for(int searchMonths = 0; searchMonths < this.yearBudget.length; searchMonths ++) {
			if(this.yearBudget[searchMonths] != null) {
				totalAllocated4Months += this.yearBudget[searchMonths].getMoneyAllocated();
			}
		}
		
		this.moneyNotAllocated = (getMoneyAllocated() - totalAllocated4Months);
	}
	
	private void calcMoneyRemaining() {
		double totalRemaining = 0;
		
		totalRemaining = getMoneyAllocated() - getMoneySpent();
		
		this.moneyRemaining = totalRemaining;
	}
	
	private void calcMoneySpent() {
		double moneySpent = 0;
		for(int searchMonths = 0; searchMonths < this.yearBudget.length; searchMonths ++) {
			if(this.yearBudget[searchMonths] != null) {
				moneySpent += this.yearBudget[searchMonths].getMoneySpent();
			}
		}
		this.moneySpent = moneySpent;
	}
	
	public boolean varifyMonthSpotAvailable(int month) {
			
			if(this.monthTrack[month] == null) {
				return true;
			}
			else{
				return false;
			}		
	}
	
	
	public void addMonth(int month, double moneyAllocated) {
	
		boolean varify = false;
		
		varify = varifyMonthSpotAvailable(month);

		if(varify) {			
			if(!this.budget) {
				this.budget = true;
			}
			
			addEnum(month);
			this.yearBudget[month] = new MonthBudget(moneyAllocated, this.monthTrack[month].getReadName());
			System.out.println("Budget for " + this.monthTrack[month].getReadName() + " created.");
			System.out.println("$" + moneyAllocated + " allocated.");
						
		}
		else {
			System.out.println("A budget already exists for this month.");
		}
	}
	
	private void addEnum(int month) {
		switch(month) {
		case(0):
			this.monthTrack[month] = Month.JANUARY;
			break;
		case(1):
			this.monthTrack[month] = Month.FEBUARY;
			break;
		case(2):
			this.monthTrack[month] = Month.MARCH;
			break;
		case(3):
			this.monthTrack[month] = Month.APRIL;
			break;
		case(4):
			this.monthTrack[month] = Month.MAY;
			break;
		case(5):
			this.monthTrack[month] = Month.JUNE;
			break;
		case(6):
			this.monthTrack[month] = Month.JULY;
			break;
		case(7):
			this.monthTrack[month] = Month.AUGUST;
			break;
		case(8):
			this.monthTrack[month] = Month.SEPTEMBER;
			break;
		case(9):
			this.monthTrack[month] = Month.OCTOBER;
			break;
		case(10):
			this.monthTrack[month] = Month.NOVEMBER;
			break;
		case(11):
			this.monthTrack[month] = Month.DECEMBER;
			break;
		}
	}
	
	public String display() { 
		String budget = "\n";
		budget += "\n   __";
		budget += "\n _|  |_";
		budget += "\n \\    / ";
		budget += "\n  \\  / ";
		budget += "\n   \\/";
		budget += "\n";
		budget += "\nName:" + getName();
		budget += "\nBudget: $" + getMoneyAllocated();
		budget += "\nMoney Spent: $" + getMoneySpent();
		budget += "\nMoney Remaining: $" + getMoneyRemaining();
		budget += "\nMoney Available to be Allocated: $" + getMoneyNotAllocated();
		budget += "\n";
		budget += displayMonths();
		
		return budget;
	}
	
	public String displayMonths() {
		String months = "";
		if(this.budget) {
			for(int search = 0; search < 12; search ++) {
				months += displayMonth(search);
			}
		}
		else {
			months += "\nThere are no Month Budgets to display.";
		}
		return months;
	}
	
	public String displayMonth(int monthNum) {
		String month = "";
		
		if(this.monthTrack[monthNum] != null) {
			month += yearBudget[monthNum].display();
		}
		else {
			//month += "The month of " + this.monthTrack[monthNum].getReadName() + "has no budget.";
		}
		return month;
	}
}
