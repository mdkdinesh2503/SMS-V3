package com.aspiresys.StudentManagementSystem;

class ManageLoginDetails extends Colour {
	public static String userNameValue, passwordValue, emailValue;

	public static void getUserInput() {
		userNameValue = isValidCheck("Username");
		passwordValue = isValidCheck("Password");
		new JdbcConnect().executeSelectQuery("LoginPage");
	}

	private static String isValidCheck(String field) {
		System.out.print(darkBlue + "\nEnter " + field + " to Continue  -  " + reset);
		String value = ReusableCode.input.nextLine();
		if (value.isEmpty()) {
			System.out.println(red + field + " is not Empty!!");
			return isValidCheck(field);
		} else {
			System.out.println(green + "Correct");
		}
		return value;
	}

	void connectionToDatabase(int registerRow, String registerData) {
		String registerDetails[] = ReusableCode.convertStringArray(registerData);
		int count = 0;
		if (userNameValue.equalsIgnoreCase("admin") && (passwordValue.equals("Admin@123"))) {
			emailValue = "admin@gmail.com";
			databaseConnection("Login-Admin");
		} else {
			for (int i = 0; i < registerDetails.length; i += 4) {
				if (userNameValue.equals(registerDetails[i]) && passwordValue.equals(registerDetails[i + 3])) {
					emailValue = registerDetails[i + 1];
					databaseConnection("Login-User");
				} else {
					count++;
				}
			}
			errorCorrection(registerRow, count);
		}
	}
	
	void errorCorrection(int registerRow, int count) {
		if(registerRow == count || registerRow == 0) {
			System.out.print(red + "\nInvalid Username or Password");
			System.out.print(brown + "\nDo you want to Continue Login? (1 - Yes) or (Redirect to Register Page) : ");
			if(ReusableCode.input.nextLine().equals("1")) getUserInput(); else RegisterPage.main(null);
		}
	}
	
	void databaseConnection(String condition) {
		String sql = "insert into LoginDetails (username, email, password, date, time) values(" + "'" + userNameValue + "', '" + emailValue + "', '" + passwordValue + "', '" + DateAndTime.date + "','" + DateAndTime.time + "')";
		new JdbcConnect().executeUpdateQuery(sql, condition);
	}
}

public class LoginPage extends ManageLoginDetails {
	public static void main(String[] args) {
		Header.loginPageHeader();
		getUserInput();
	}
}