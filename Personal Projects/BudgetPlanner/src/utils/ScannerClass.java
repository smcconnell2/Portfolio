package utils;

import java.util.Scanner;

public class ScannerClass {
	public static Scanner scan;
	
	private static Scanner getScanner() {
		if(scan == null) {
			scan = new Scanner(System.in);
		}
		return scan;
	}
	
	public static String readString() {
		String reading = getScanner().nextLine();
		
		while(reading.isEmpty()) {
			errorMsg("none");
			errorMsg(" ");
			reading = getScanner().nextLine();
		}
		
		return reading;
	}
	
	public static char readChar() { // test this method
		char letter = readString().charAt(0);
		return letter;
	}
	
	public static int readInt() {
		String temp = readString();
		int value = 0;
		try {
			value = Integer.parseInt(temp);
			return value;
		}
		catch(NumberFormatException e) {
			errorMsg("format");
			errorMsg(" integer ");
			value = readInt();
		}
		catch(Exception e) {
			errorMsg(" integer ");
			errorMsg(" ");
			value = readInt();
		}
		
		return value;
	}
	
	public static double readDouble() {
		String temp = readString();
		Double value = 0.0;
		try {
			value = Double.parseDouble(temp);
			return value;
			
		}
		catch(NumberFormatException e) {
			errorMsg("format");
			errorMsg(" double ");
			value = readDouble();
		}
		catch(Exception e) {
			errorMsg(" double ");
			errorMsg(" ");
			value = readDouble();
		}
		
		return value;
		
	}
	
	public static void readFile() {
		
	}
	
	private static void errorMsg(String value) {
		if(value.equals("format")) {
			System.out.println("You entered an incorrect formatted value.");
		}
		else if(value.equals("none")) {
			System.out.println("There wasn't a value entered.");
		}
		else {
			System.out.println("Please enter an appropriate" + value + "vlaue.");
		}
	}
	
	
}
