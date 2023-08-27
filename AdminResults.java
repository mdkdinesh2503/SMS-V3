package com.aspiresys.StudentManagementSystem;

import java.util.regex.*;

public class AdminResults implements inputFieldValidation {

	void connectionToDatabase(int resultsRow, String resultsData, int feesRow, String feesData) {
		Header.adminResultsHeader();
		System.out.println(Colour.borderText + Colour.Bg_lightBlue + Colour.white + " S.NO\tREGISTER NO.\tRESULT\t\t" + Colour.reset + Colour.borderText);
		String results[] = ReusableCode.convertStringArray(resultsData);
		String fees[] = ReusableCode.convertStringArray(feesData);
		if (resultsRow != 0) {
			for (int index = 0, number = 1; index < results.length; index += 12, number++) {
				System.out.print("  " + number + "\t  " + results[index] + "\t " + results[index + 3] + "\t\t\n");
			}
			performAdminResults(results, fees);
		} else { redirectToMenu(" \tNo Result Details Available\t", results, fees); }
	}

	static void performAdminResults(String[] results, String[] fees) {
		System.out.print(Colour.reset + Colour.brown + "\n1 - Add, 2 - View, 3 - Delete, 4 - Menu");
		String choice = ReusableCode.choiceSelector("Choice");
		String actions[] = {"View Result Details", "Delete Result Details", "View Data!!", "Delete Data!!"};
		if (choice.equals("1")) { if(fees.length != 1) { addResults(results, fees); } else { redirectToMenu("There is no Registered Students Available to Add Data..", results, fees); } }
		else if (choice.equals("2") || choice.equals("3")) {
			if (results.length != 1) { isValidAdminResuts(actions[Integer.parseInt(choice)-2], results, fees, 0, 12); } 
			else { redirectToMenu(("The Results are not Assigned for Single User to " + actions[Integer.parseInt(choice)]), results, fees); }
		} 		
		else if (choice.equals("4")) { System.out.println(); AdminDashboard.start(); } 
		else { redirectToMenu("Invalid choice! Please enter b/w (1-4)..", results, fees); }
	}

	static void addResults(String[] results, String[] fees) {
		System.out.print(Colour.darkBlue + "\nEnter Register Number to Add Results - " + Colour.reset);
		String add = ReusableCode.input.nextLine();
		boolean available = false, feesPaided = false, exists = false;
		int getUserId = 0, value = 0;
		for (int i = 2; i < fees.length; i += 22) {
			if (add.equals(fees[i])) {
				available = true;
				value = i;
			}
			if (add.equals(fees[i]) && "Paided".equalsIgnoreCase(fees[i + 8]) && "Paided".equalsIgnoreCase(fees[i + 9])
					&& "Paided".equalsIgnoreCase(fees[i + 10]) && "Paided".equalsIgnoreCase(fees[i + 11])) {
				feesPaided = true;
				getUserId = i;
			}
		}
		for (int i = 0; i < results.length; i += 12) {
			if (add.equals(results[i])) {
				exists = true;
			}
		}
		if (feesPaided) {
			if (!exists) {
				String labels[] = {"\t\tFull Name \t:   ", "\t\tRegister Number :   ", "\t\tEmail \t\t:   ", "\t\tProfessional Ethics (PE) : ", "\t\tDatabase Systems (DBS) : ", "\t\tService Oriented Architecture (SOA) : ", "\t\tInternet Of Things (IOT) : ", "\t\tGraphics Multimedia (GM) : ", "\t\tHigh Speed Networks (HSN)/Business Intelligence (BI) : ", "\t\tBig Data (BD)/Cloud Computing (CC)) : ", "\t\tGraphics Multimedia Laboratory (GM LAB) : "};
				String values[] = {"", "", "", "", "", "", "", ""};
				System.out.print(Colour.brown + Colour.underline + "User Details:-" + Colour.reset + "\n");
				for(int i = getUserId - 1, m = 0; i <= getUserId + 1; i++, m++) {
					System.out.print(Colour.darkBlue + labels[m] + Colour.reset + fees[i] + "\n");
				}
				System.out.print(Colour.brown + "\tEnter Mark Obtained Details: -" + Colour.reset + "\n");
				for(int i = 0; i < values.length; i++) {
					values[i] = isValidCheck(labels[i + 3], markRegex, markMessage);
				}
				String res = (ReusableCode.isValidChoice(values, "E")) ? "FAIL" : "PASS";
				String sql = "insert into StudentResults values ( " + fees[getUserId] + ", '" + fees[getUserId - 1] + "', '" + fees[getUserId + 1] + "', '" + res + "', '" + values[0] + "', '" + values[1] + "', '" + values[2] + "', '" + values[3] + "', '" + values[4] + "', '" + values[5] + "', '" + values[6] + "', '" + values[7] + "' );";
				new JdbcConnect().executeUpdateQuery(sql, "AdminResults");
			} else {
				redirectToMenu(("The Result is Already Marked for " + fees[value - 1] + "!!"), results, fees);
			}

		} else if (available) {
			redirectToMenu((fees[value - 1] + " is Not Paided Fees"), results, fees);
		} else {
			errorMessage(2, 22, fees);
			performAdminResults(results, fees);
		}
	}

	static String isValidCheck(String field, String regex, String errorMessage) {
		System.out.print(Colour.darkBlue + field + Colour.reset);
		String value = ReusableCode.input.nextLine();
		Pattern pattern = Pattern.compile(regex);
		if (value.isEmpty() || !pattern.matcher(value).matches()) {
			System.out.print(Colour.red + "\t\t" + (value.isEmpty() ? "This Field is Required!" : errorMessage) + "\n");
		} else {
			int data = Integer.parseInt(value);
			if (data >= 90) {  return "O";
			} else if (data >= 80 && data <= 89) {  return "A";
			} else if (data >= 70 && data <= 79) {  return "B";
			} else if (data >= 60 && data <= 69) {  return "C";
			} else if (data >= 50 && data <= 59) {  return "D";
			} else {  return "E";  }
		}
		return isValidCheck(field, regex, errorMessage);
	}

	static void deleteResults(String[] results, String[] fees, String delete) {
		System.out.print(Colour.brown + "\nDo you want to delete " + delete + " Records? (1 - Yes) : ");
		if (ReusableCode.input.nextLine().equals("1")) {
			new JdbcConnect().executeUpdateQuery(("DELETE FROM StudentResults WHERE RegisterNumber = " + delete + ";"), "AdminResults");
		} else {
			System.out.println(Colour.red + "You have chosen not to Delete " + delete + " Data..");
			performAdminResults(results, fees);
		}
	}

	static void viewResults(String[] results, String[] fees, int getUserId) {
		String space[] = { "\n", "\n", "\n", "\n", " Grade\n", " Grade\n", " Grade\n", " Grade\n", " Grade\n", " Grade\n", " Grade\n", " Grade\n" };
		String labels[] = { "\t\tRegister Number \t  :   ", "\t\tFull Name \t\t  :   ", "\t\tEmail \t\t\t  :   ", "\t\tResult Status \t\t  :   ", "\t\tProfessional Ethics (PE)  :   ", "\t\tDatabase Systems (DBS) \t  :   ", "\t\tService Oriented Architecture (SOA) :   ", "\t\tInternet Of Things (IOT)  :   ", "\t\tGraphics Multimedia (GM)  :   ", "\t\tHigh Speed Networks (HSN)/Business Intelligence (BI) :   ", "\t\tBig Data (BD)/Cloud Computing (CC)) \t:   ", "\t\tGraphics Multimedia Laboratory (GM LAB) :   " };
		String values[] = { results[getUserId], results[getUserId + 1], results[getUserId + 2], results[getUserId + 3], results[getUserId + 4], results[getUserId + 6], results[getUserId + 7], results[getUserId + 8], results[getUserId + 10], results[getUserId + 5], results[getUserId + 9], results[getUserId + 11] };
		System.out.print(Colour.brown + Colour.underline + "\nUser Details:-" + Colour.reset + "\n");
		for (int i = 0; i < space.length; i++) {
			System.out.print(Colour.darkBlue + labels[i] + Colour.reset + ((i != 3) ? (values[i] + space[i]) : (values[i].equalsIgnoreCase("PASS") ? Colour.green : Colour.red) + values[i] + space[i]));
		}
		performAdminResults(results, fees);
	}
	
	static void isValidAdminResuts(String field, String results[], String fees[], int index, int total) {
		int value = 0;
		System.out.print(Colour.darkBlue + "\nEnter Register Number to " + field + " - " + Colour.reset);
		String data = ReusableCode.input.nextLine();
		boolean validData = false;
		for (int i = index; i < results.length; i += total) {
			if(data.equals(results[i])) {
				value = i;
				validData = true;
			}
		}
		if(validData) {
			if(field.equals("View Result Details")) { viewResults(results, fees, value); }
			else { deleteResults(results, fees, data); }
		} else {
			errorMessage(index, total, results);
			performAdminResults(results, fees);
		}
	}
	
	static void errorMessage(int index, int total, String array[]) {
		System.out.print(Colour.red + "Invalid Register Number! ");
		for (int i = index; i < array.length; i += total) {
			System.out.print(array[i] + ", ");
		}
		System.out.print("only Available.. \n");
	}
	
	static void redirectToMenu(String message, String results[], String fees[]) {
		System.out.println(Colour.red + message);
		performAdminResults(results, fees);
	}

	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("AdminResults");
	}
}