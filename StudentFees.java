package com.aspiresys.StudentManagementSystem;

class ManageStudentFeesDetails extends Colour {
	public static String nameData, emailData, firstStatus, secondStatus, thirdStatus, fourthStatus;
	public static int registerNumberData;

	void connectionToDatabase(int feesRow, String feesData, String loginEmail) {
		String feesDetails[] = ReusableCode.convertStringArray(feesData);
		int array[] = ReusableCode.forLoopStatements(3, 22, feesDetails, loginEmail);
		if (feesRow == array[1]) {
			System.out.println(red + "Please Fill the Student details to View Fees Details..\n");
			StudentDashboard.studentDashboardMenu();
		} else {
			nameData = feesDetails[array[0] - 2];
			emailData = feesDetails[array[0]];
			firstStatus = feesDetails[array[0] + 7];
			secondStatus = feesDetails[array[0] + 8];
			thirdStatus = feesDetails[array[0] + 9];
			fourthStatus = feesDetails[array[0] + 10];
			registerNumberData = Integer.parseInt(feesDetails[array[0] - 1]);
			Header.studentFeesHeader(firstStatus, secondStatus, thirdStatus, fourthStatus);
			performStudentFees();
		}
	}

	void performStudentFees() {
		System.out.print(bold + gray + "\n\n 1 - First Year, 2 - Second Year, 3 - Third Year, 4 - Fourth Year, 5 - Menu" + reset);
		String choice = ReusableCode.choiceSelector("Choice");
		String valid[] = {"1", "2", "3", "4"};
		if (ReusableCode.isValidChoice(valid, choice)) {
			displayFeesDetails(choice);
		} else if (choice.equals("5")) {
			System.out.println();
			StudentDashboard.studentDashboardMenu();
		} else {
			System.out.print(red + "Invalid Choice! Please Try again..");
			performStudentFees();
		}
	}

	void displayFeesDetails(String year) {
		String years[] = {"First Year", "Second Year", "Third Year", "Fourth Year"};
		String status[] = {firstStatus, secondStatus, thirdStatus, fourthStatus};
        int fees[] = {52000, 54000, 56000, 58000};
        int index = Integer.parseInt(year) - 1;
        String labels[] = {"\n\tFull Name \t- ", "\n\tRegister Number - ", "\n\tEmail \t\t- ", "\n\tDegree \t\t- ", "\n\tDepartment \t- ", "\n\tYear \t\t- ", "\n\tStatus \t\t- ", "\n\tAmount \t\t- "};
        String values[] = {nameData, String.valueOf(registerNumberData), emailData, "BE", "CSE", years[index], status[index], String.valueOf(fees[index])};
		System.out.print(brown + underline + "\nDetails :" + reset);
		for(int i = 0; i < labels.length; i++) {
			System.out.print(darkBlue + labels[i] + reset + values[i]);
		}
		payCollegeFees(years[index], status[index], fees[index]);
	}

	void payCollegeFees(String year, String status, int amount) {
		if (status.equals("Pending")) {
			System.out.print(brown + "\n\nDo you want to Pay Fees? (1 - Yes) : " + reset);
			if (ReusableCode.input.nextLine().equals("1")) {
				new PaymentPage(nameData, registerNumberData, emailData, amount, year);
			} else {
				System.out.print(red + "You have chosen not to Pay College Fees..");
			}
		}
		performStudentFees();
	}
}

public class StudentFees {
	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("StudentFees");
	}
}
