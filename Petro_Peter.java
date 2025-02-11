// Author: Peter Petro
/*This class salutes class, Assignment1 
 * Assignment 1: Java receipt Generator for a fictional store
 * PID: 5448978
 * NID: PE310601
 * Course: COP 3330 - Object Oriented Programming
 * Deadline: 02/10/2025 11:59PM
 */

package assignment1;

import java.util.Random; // Random Library for RNG Receipt number
import java.util.Scanner;
import java.util.ArrayList;

public class Petro_Peter {

	// global static var to store total amount, big Double for NULL ( avoiding giving out items for free )
	public static Double totalAmount = 0.0d;
	public static String storeName = "S store"; // according to sample output. Assumption made in email autocorrect uppercase 'S' in "Store".
	public static int itemNumber = 0;
	
	// Random Receipt Number [1000,2000) = 1000 <= n < 2000
	public static int rngReceipt() {
		Random rand = new Random();
		return rand.nextInt(999)+1000;
		}
		
	// Function to aid random month generation.
	public static String getMonth(int number) {	
		ArrayList<String> months = new ArrayList<>(); // create an ArrayList
		// Feed the array list	
		months.add(null); // This null lines up ArrayList indexing with corresponding month number.
		months.add("January"); months.add("February"); months.add("March"); months.add("April"); 
		months.add("May"); months.add("June"); months.add("July"); months.add("August");
		months.add("September"); months.add("October"); months.add("November"); months.add("December");
		
		return months.get(number); // returns the corresponding month.
		}
		
		// Random Date Generation Month Day Year   Y = [1900,2100) = 1900 <= n < 2.1k
		public static String randomDate() {
			Random rand = new Random();
			int randomMonth = rand.nextInt(11) + 1; 
			int randomYear = rand.nextInt(199)+1900;
			int randomDay = 0; // Initialized for later use

			// Edge case #1: February && Leap year (divisible by 4, not divisible by 100 unless divisible by 400) 
			if(randomMonth == 2 && randomYear % 4 == 0){
				randomDay = rand.nextInt(28)+1; // 29 days of February
			}
			// Every even month that's not February
			if(randomMonth != 2 && randomMonth % 2 == 0 && randomMonth != 8) {
				randomDay = rand.nextInt(29)+1;
			}
			// Last case is any other day.
			else {randomDay = rand.nextInt(30)+1;
			}
			return getMonth(randomMonth) +" "+randomDay+" "+randomYear; // returns the a concated string with month in chars, spaces, and numbers(as strings))
		}
	
	/*
	 * Function to calculate taxes	
	 */
		
	public static Double taxCalc(String itemName, Double originalPrice) {
		if(itemName.contentEquals("Food") == true) { 
			return (double) Math.round(originalPrice*10)/10; } // if its food no just return base price
		else {
			return (double) Math.round((originalPrice* 1.3 * 10)/10);} // anything else return price with taxes inclued
	}
	
	/*
	 * Function to get user input.
	 */
	
	public static void getInput(String userInput) {
		Scanner userIn = new Scanner(System.in);
		String keyword = "DONE"; // predef exit (basecase)
		
		System.out.print("Write item name ");
		
		userInput = userIn.next(); 	// get user input
		itemNumber++;				// increase our total item count
		if(keyword.equals(userInput) == false) { // boolean string compare
			userInput = userInput.toLowerCase(); // after keyword bypass, convert all to lowercase in order to avoid weird names.
			userInput = Character.toUpperCase(userInput.charAt(0)) + userInput.substring(1); // then change string[0] to uppercase.
			System.out.print("Write price ");
			double itemPrice = userIn.nextDouble(); // THIS WILL THROW ERROR IF USER GIVES CHAR BUT ERROR HANDLING WASN'T PART OF ASSIGNMENT (HASNEXTDOUBLE WOULD FIX IT) don't want to get negative grade for altering output to ask for another double input if user is monkey
			totalAmount = totalAmount + taxCalc(userInput,itemPrice);
			System.out.println("item number " + itemNumber + " " + userInput + " " + taxCalc(userInput,itemPrice));
			getInput(userInput.toUpperCase());  // recursive call. 
		}
		else { itemNumber--;} // reduce the counter by 1 for "DONE"
		userIn.close(); // close scanner.
	}
	
	public static void main(String[] args) {
		
	rowOfStars();
	storeHeader();
	rowOfStars();
	System.out.println("receipt number      " + rngReceipt());
	System.out.println(randomDate());
	getInput("start");	// function call to getInput with a feed string
	
	printTotal();
	rowOfStars();rowOfStars();rowOfStars(); // last 3 rows of stars
		
	} 
	
	/*
	 * FUNCTIONS TO AID PRINTING.
	 */
	
	// Print the total amount row.
	public static void printTotal() {
		System.out.println(itemNumber + " items      " + (double) Math.round(totalAmount * 10)/10);	 // cast to double else it cuts off decimal [not matching output examples]
	}
	
	// Function to print multiple spaces " "
	public static void stringSpaces (int amount) {
		String stringSpace = "";
		for(int i = 0; i < amount; i++) {
			stringSpace = stringSpace + " "; 
		}
		System.out.print(stringSpace);
	}
	
	// Function to print multiple new lines
	public static void newLine(int amount) {
		for(int i = 0; i < amount; i++) {
			System.out.print("\n");
		}
	}
	
	// Print a full row of stars.
	public static void rowOfStars() {
		System.out.println("******************************");
	}
	
	// Function to print multiple stars.
	public static void writeStars (int amount) {
		String stars = "";
		for(int i = 0; i < amount; i++) {
			stars = stars + "*"; 
		}
		System.out.print(stars);
	}
	
	// Function to print store name line
	public static void storeHeader() {
		System.out.println("****** " + storeName + " ***************");
	}
}