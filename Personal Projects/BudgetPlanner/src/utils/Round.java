package utils;

public class Round {
	
	public static double roundToHundredth(double number) {
		number = ((Math.round(number*100.0))/100.0);
		return number;
	}

}
