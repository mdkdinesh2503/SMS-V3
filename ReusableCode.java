package com.aspiresys.StudentManagementSystem;

import java.text.*;
import java.util.*;

class Colour {
	public static final String reset = "\u001B[0m";
	public static final String red = "\u001B[31m";
	public static final String green = "\u001B[32m";
	public static final String yellow = "\u001B[33m";
	public static final String darkBlue = "\u001B[34m";
	public static final String purple = "\u001B[35m";
	public static final String lightBlue = "\u001B[36m";
	public static final String white = "\u001B[97m";
	public static final String gray = "\u001B[90m";
	public static final String brown = "\u001B[38;2;128;0;0m";

	public static final String Bg_black = "\u001B[40m";
	public static final String Bg_red = "\u001B[41m";
	public static final String Bg_green = "\u001B[42m";
	public static final String Bg_yellow = "\u001B[43m";
	public static final String Bg_darkBlue = "\u001B[44m";
	public static final String Bg_purple = "\u001B[45m";
	public static final String Bg_lightBlue = "\u001B[46m";

	public static final String bold = "\u001B[1m";
	public static final String italic = "\u001B[3m";
	public static final String underline = "\u001B[4m";
	public static final String strikeText = "\u001B[9m";
	public static final String hiddenText = "\u001B[8m";
	public static final String borderText = "\u001B[51m";
}

class DateAndTime {
	public static Date calender = new Date();
	public static String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date(calender.getTime()));
	public static String time = new SimpleDateFormat("hh:mm:ss a").format(new Date(calender.getTime()));
}

interface inputFieldValidation {
	String userNameRegex = "^(?!.*(.).*\\1\\1)[a-zA-Z]{3,20}$";
	String emailRegex = "^[0-9a-z]+[._]{0,1}[0-9a-z]+[@][a-z]+[.][a-z]{2,3}([.][a-z]{2,3}){0,1}$";
	String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{6,12}$";

	String userNameMessage = "Enter Valid Username : \n\t* Username only contains Alphabets, \n\t* Minimum of 3 digits, \n\t* 2 similar consecutive characters only allowed, \n\t* No Spaces allowed";
	String emailMessage = "Enter Valid Email : \n\t* email only in mdk@gmail.com this format, \n\t* No Chapital Letters Allowed, \n\t* No Spaces allowed";
	String passwordMessage = "Enter Valid password : \nPassword must Contains :- \n\t* Minimum of 6 Digits, \n\t* Minimum 1 Alphabets, \n\t* Minimum 1 Numbers, \n\t* Minimum 1 Special Characters, \n\t* No Spaces allowed";
	String confirmPasswordMessage = "Confirm password must same as Password";

	String fullNameRegex = "^(?!.*(.).*\\1\\1)[a-zA-Z ]{3,20}$";
	String dobRegex = "^(0?[1-9]|[12]\\d|3[01])-(0?[1-9]|1[0-2])-(20(?:00|0[0-4]))$";
	String mobileRegex = "^(?:0)?[6789]\\d{9}$";
	String genderRegex = "^Male|male|Female|female$";
	String registerNumberRegex = "^19130(?:0[1-9]|[123]\\d|40)$";
	String markPercentageRegex = "^(100|[0-9]{1,2})%$";
	String markRegex = "^(100|[0-9]{1,2})$";

	String fullNameMessage = "Enter Valid Name : \n\t\t* Name only contains Alphabets, \n\t\t* 2 similar consecutive characters only allowed, \n\t\t* Minimum of 3 Digits";
	String dobMessage = "Enter Valid Date Of Birth : \n\t\t* DOB only contains Numbers, \n\t\t* DOB is must be in this format 'dd-MM-yyyy', \n\t\t* Date has a limit(01-31), \n\t\t* Month has a limit(01-12), \n\t\t* Year has a limit(2000-2004)";
	String mobileMessage = "Enter Valid Mobile Number : \n\t\t* Mobile Number only contains Numbers,\n\t\t* Mobile Number Must be 10 Digits, \n\t\t* First Digit must Starts with [6,7,8,9]";
	String genderMessage = "Enter Valid Gender - Gender must be either 'male' or 'female'";
	String registerNumberMessage = "Enter Valid Register Number : \n\t\t* Register Number Starts with '19130', \n\t\t* Register Number Only Contains Numbers, \n\t\t* It Contains 7 Digits, \n\t\t* Register Number Starts with '01-40'";
	String markPercentageMessage = "Enter Valid Percentage : \n\t\t* Value must ends with '%', \n\t\t* Mark should between [0-100]";
	String markMessage = "Enter Valid Mark : Mark should between [0-100]";
	
	String cardNumberRegex = "^(?:4\\d{12}(?:\\d{3})?|5[1-5]\\d{14})$";
	String monthYearRegex = "^(0[1-9]|1[0-2])/((\\d{2})|(\\d{4}))$";
	String cvvRegex = "^\\d{3}$";
	String upiRegex = "^[a-zA-Z0-9._]+@[a-zA-Z0-9._]+$";
	
	String cardNumberMessage = "\t\tEnter Valid Card Number : \n\t\t* Card Number Contains 16 Digits, \n\t\t* Starts with '4'";
	String monthYearMessage = "\t\tEnter Valid Month and Year : \n\t\t* 'month/year' is the Format, \n\t\t* Month Should be between [01-12], \n\t\t* Year Should be in 'xx' or 'xxxx' this Format";
	String cvvMessage = "\t\tEnter Valid CVV - It only contain Numbers - Allow only 3 digits";
	String upiMessage = "\t\tEnter Valid UPI";
}

public class ReusableCode extends Colour {
	public static Scanner input = new Scanner(System.in);
	
	public static void spaceAllocation(int k, int m) {
		System.out.print(" ".repeat(m - k) + "\t");
	}
	
	public static String choiceSelector(String value) {
		System.out.print(darkBlue + "\nEnter your " + value + " - " + reset);
		return input.nextLine();
	}
	
	public static String convertStringArray(String arrayString)[] {
		return arrayString.substring(1, arrayString.length() - 1).split(", ");
	}
	
	public static void performTimetable(String field, String timetableData, int timetableRow) {
		String timetable[] = convertStringArray(timetableData);
		if (timetableRow == 5) {	
			for(int i = 1; i <= 44; i += 9) {
				String actions[] = {"2", "11", "20", "29", "38"};
				System.out.print(Bg_lightBlue + white + "   " + timetable[i] + "\t " + reset + "    ");
				for (int index = i + 1; index <= i + 7; index++) {
					System.out.print(bold + gray + timetable[index]);
					spaceAllocation(timetable[index].length(), 9);
					if(isValidChoice(actions, String.valueOf(index))) {
						System.out.print("\t");
					}
				}
				System.out.println(Bg_lightBlue + white + "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + reset);
				
			}
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + reset);
			if(field.equalsIgnoreCase("Admin")) { AdminTimetable.performAdminTimetable(); }
		} else {
			System.out.println(reset + borderText + red + "\t\t\t\t\t\t\tNo Timetable Details Available\t\t\t\t\t\t  \n" + reset);
		}
	}
	
	public static void performExam(String hall[], String timetable[], String field) {
		System.out.print(bold + gray + "\n1 - Hall Allocation, 2 - Timetable, 3 - Menu" + reset);
		String choice = choiceSelector("Choice");
		switch(choice) {
		case "1" -> isperformExamValid("Hall Allocation", hall, timetable, field, hall.length == 5);
		case "2" -> isperformExamValid("Timetable", hall, timetable, field, timetable.length == 19);
		case "3" -> {
			System.out.println(); 
			if(field.equalsIgnoreCase("Admin")) { AdminDashboard.start(); }
			else { StudentDashboard.studentDashboardMenu(); }
		}
		default -> {
			System.out.print(red + "Invalid choice! Please enter 1, 2, or 3..\n" + reset); 
			performExam(hall, timetable, field);
		}
		}
	}
	
	private static void isperformExamValid(String choiceName, String[] hall, String[] timetable, String field, boolean isValid) {
		if(isValid) { displayExamDetails(choiceName, hall, timetable, field); } 
		else { errorMessageExam(choiceName, hall, timetable, field); }
	}
	
	private static void errorMessageExam(String field, String hall[], String timetable[], String user) {
		System.out.print(red + "Exam " + field + " is not done yet!!\n" + reset);
		if(user.equalsIgnoreCase("Admin")) {
			AdminExam.addExamDetails(hall, timetable, field);
		} else {
			performExam(hall, timetable, field);
		}
	}
	
	public static void displayExamDetails(String field, String[] hall, String[] timetable, String user) {
		if(field.equalsIgnoreCase("Hall Allocation")) {
			System.out.print("\n" + borderText + Bg_lightBlue + white + "\t   YEAR     DEPARTMENT   NO.OF STUDENTS\t  HALL NO.");
			spaceAllocation(7, hall[4].length());
			System.out.print(reset + "\n" + borderText + "  " + hall[1] + "\t" + hall[2] + "\t\t" + hall[3] + "\t\t  " + hall[4]);
			spaceAllocation(9, 11);
			if(user.equalsIgnoreCase("Admin")) {
				System.out.println(reset);
				AdminExam.performUpdateHallAllocations(hall, timetable);
			}	
		} else {
			System.out.println("\n" + Bg_lightBlue + white + "\t\t\t\t\t\t\t");
			System.out.println("\t Timing : " + timetable[1] + " to " + timetable[2] + "\t\t");
			System.out.println("\t\t\t\t\t\t");
			String action[] = {"\t\tPE\t\t", "\t\tHSN / BI\t", "\t\tDBS\t\t", "\t\tSOA\t\t", "\t\tIOT\t\t", "\t\tBD / CC\t\t", "\t\tGM\t\t", "\t\tGM LAB\t\t"};
			for(int i = 0; i < action.length; i++) {
				System.out.print("\t    " + timetable[i + 3] + action[i]);
				System.out.println("\n\t    " + timetable[i + 11] + "\t\t\t\t\n\t\t\t\t\t\t");
			}
			if(user.equalsIgnoreCase("Admin")) {
				System.out.print(reset);
				AdminExam.performUpdateTimetable(hall, timetable);
			}
		}
		System.out.println(reset);
		performExam(hall, timetable, "Student");
	}
	
	public static int[] forLoopStatements(int index, int length, String array[], String email) {
		int getValue = 0, decision = 0;
		for (int n = index; n < array.length; n += length) {
			if (email.equals(array[n])) getValue = n; else decision++;
		}
		int arr[] = new int[2];
		arr[0] = getValue;
		arr[1] = decision;
		return arr;
	}
	
	public static void performAdminStatements(String field, String array[], int index, int total) {
		int value = 0;
		System.out.print(darkBlue);
		System.out.print(field.equalsIgnoreCase("Delete Register Details")? "\nEnter Username to " : "\nEnter Register Number to ");
		System.out.print(field + " - " + reset);
		String data = input.nextLine();
		boolean validData = false;
		for (int i = index; i < array.length; i += total) {
			if(data.equals(array[i])) {
				value = i;
				validData = true;
			}
		}
		if(validData) {
			if(field.equalsIgnoreCase("View User Details")) { AdminDetails.viewUser(array, value); }
			else if (field.equalsIgnoreCase("Delete User Details")) { AdminDetails.deleteUser(array, data); }
			else if (field.equalsIgnoreCase("Search Fees Details")) { AdminFees.searchUser(array, data); }
			else if (field.equalsIgnoreCase("Delete Register Details")) { AdminRegisterList.deleteUser(array, data); }
			else if (field.equalsIgnoreCase("View Register Details")) { AdminReports.viewUser(array, value); }
		}else {
			System.out.println(red + "Invalid Data! Please Try again..");
			performAdminStatements(field, array, index, total);
		}
	}
	
	public static int[] lengthIdentify(int row, String array[], int index, int length) {
		int data[] = new int[row];
		for (int i = index, m = 0; i < array.length; i += length, m++) {
			data[m] = array[i].length();
		}
		Arrays.sort(data);
		return data;
	}
	
	public static boolean isValidChoice(String array[], String choice) {
		return Arrays.asList(array).contains(choice);
	}
}
