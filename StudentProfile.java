package com.aspiresys.StudentManagementSystem;

public class StudentProfile implements inputFieldValidation {
	String loginEmailValue;
	int value;

	void connectionToDatabase(int registerRow, String registerData, String loginEmail) {
		String register[] = ReusableCode.convertStringArray(registerData);
		loginEmailValue = loginEmail;
		int array[] = ReusableCode.forLoopStatements(1, 4, register, loginEmail);
		value = array[0];
		Header.studentProfileHeader();
		System.out.print("\n\t" + Colour.borderText + Colour.bold + Colour.lightBlue + "  Username : " + register[value - 1] + "  " + Colour.reset);
		System.out.print(Colour.borderText + Colour.bold + Colour.lightBlue + "  Email : " + register[value] + "  " + Colour.reset);
		System.out.print(Colour.borderText + Colour.bold + Colour.lightBlue + "  Password : " + register[value + 1]+ "  " + Colour.reset + "\n");
		performStudentProfile(register);
	}

	void performStudentProfile(String register[]) {
		System.out.print(Colour.brown + "\n1 - Edit Password, 2 - Menu" + Colour.reset);
		String choice = ReusableCode.choiceSelector("Choice");
		if (choice.equalsIgnoreCase("1")) {
			editUser(register);
		} else if (choice.equalsIgnoreCase("2")) {
			System.out.println();
			StudentDashboard.studentDashboardMenu();
		} else {
			System.out.print(Colour.red + "Invalid choice! Please enter 1, or 2..\n");
			performStudentProfile(register);
		}
	}

	void editUser(String register[]) {
		String password = ReusableCode.choiceSelector("Updated Password");
		if (password.isEmpty()) {
			System.out.println(Colour.red + "Password is not Empty!!");
		} else if (password.equalsIgnoreCase(register[value + 1])) {
			System.out.println(Colour.red + "Password is same as old Password, Try new Password!!");
		} else if(password.contains(" ")) {
			System.out.println(Colour.red + "Password does not allow Space!!");
		} else {
			System.out.print(Colour.brown + "\nDo you want to Update Password? (1 - Yes) : ");
			if (ReusableCode.input.nextLine().equalsIgnoreCase("1")) {
				String sql = "Update RegisterDetails set password = '" + password + "' where email = '" + loginEmailValue + "';";
				String query = "Update RegisterDetails set confirmpassword = '" + password + "' where email = '" + loginEmailValue + "';";
				String overallSql = sql + " : " + query;
				new JdbcConnect().executeUpdateQuery(overallSql, "StudentProfile");
			} else {
				System.out.print(Colour.red + "You have chosen not to Update Password..\n");
				performStudentProfile(register);
			}
		}	
		editUser(register);
	}

	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("StudentProfile");
	}
}
