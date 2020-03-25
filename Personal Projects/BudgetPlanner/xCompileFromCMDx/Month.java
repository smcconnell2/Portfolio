//package month;

public enum Month {
	
	JANUARY(1, "January", "Jan"), 
	FEBUARY(2, "Febuary", "Feb"),
	MARCH(3, "March", "Mar"),
	APRIL(4, "April", "Apr"),
	MAY(5, "May", "May"),
	JUNE(6, "June", "Jun"),
	JULY(7, "July", "Jul"),
	AUGUST(8, "August", "Aug"),
	SEPTEMBER(9, "September", "Sep"),
	OCTOBER(10, "October", "Oct"),
	NOVEMBER(11, "November", "Nov"),
	DECEMBER(12, "December", "Dec");
	
	
	private int value;
	private String readableName;
	private String abbreviation; 
	
	private Month(int val, String readable, String abrev) {
		this.value = val;
		this.readableName = readable;
		this.abbreviation = abrev;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String getReadName() {
		return this.readableName;
	}
	
	public String getAbbreviation() {
		return this.abbreviation;
	}
	
	
}
