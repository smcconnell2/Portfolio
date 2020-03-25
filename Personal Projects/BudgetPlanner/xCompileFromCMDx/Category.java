//package budget;

import java.io.Serializable;

/**
 * This class is a category that a monthly budget will have access to.
 * It is responsible for it's priority among other categories within that monthly budget.
 * Will not allow access to money that exceeds moneyAllocated.
 * @author steve
 *
 */
public class Category implements Serializable{
	
	private static final long serialVersionUID = 3L;
	private int defaultPriority = 1;
	private String name = "";
	private double moneyAllocated = 0;
	private double moneyRemaining = 0;
	private double moneySpent = 0;
	
	public Category(int priority, String name, double moneyAllocated) {
		this.defaultPriority = priority;
		this.name = name;
		this.moneyAllocated = moneyAllocated;
		this.moneyRemaining = moneyAllocated;
	}
	
	public int getPriority() {
		return this.defaultPriority;
	}
	
	public void setPriority(int newPriority) {
		this.defaultPriority = newPriority;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public double getMoneyAllocated() {
		return this.moneyAllocated;
	}
	
	public double getMoneyRemaining() {
		return this.moneyRemaining;
	}
	
	public double getMoneySpent() {
		this.moneySpent = calcMoneySpent();
		return this.moneySpent;
	}
	
	private double calcMoneySpent() {
		return (getMoneyAllocated() - getMoneyRemaining());
	}
	
	public void setAmount(double amount) {
		if(this.moneyAllocated == 0) {
			this.moneyAllocated = amount;
			this.moneyRemaining = amount;
		}
		else {
			addMoney(amount);
		}
	}
	
	public void addMoney(double amount) {
		this.moneyAllocated += amount;
		this.moneyRemaining += amount;
	}
	
	public void spendMoney(double amount) {
		if(amount < this.moneyRemaining) {
			this.moneyRemaining -= amount;
		}
		
		else {
			System.out.println("Insufficient funds.");
			System.out.println("Remaining funds for " + this.name + ": $" + this.moneyRemaining);
			System.out.println();
		}
	}
	
	public String display() {
		String category = "";
		category += "\n				" + this.name;
		category += "\n		Priority: " +this.defaultPriority;
		category += "\n		Budget: $" + getMoneyAllocated();
		category += "\n		Spent: $" + getMoneySpent();
		category += "\n		Money Remaining: $" + getMoneyRemaining();
		
		return category;
	}
	
}
