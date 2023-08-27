package com.aspiresys.StudentManagementSystem;

import java.util.regex.*;

class ManageRegisterDetails implements inputFieldValidation {
	static String userNameValue, emailValue, passwordValue, confirmPasswordValue, registerData;
	static int registerRow;

	void connectionToDatabase(int registerRow, String registerData) {
		ManageRegisterDetails.registerData = registerData;
		ManageRegisterDetails.registerRow = registerRow;
		Header.registerPageHeader();
		getUserInput();
	}

	void getUserInput() {
		userNameValue = isValidCheck("Username", userNameRegex, userNameMessage);
		emailValue = isValidCheck("Email", emailRegex, emailMessage);
		passwordValue = isValidCheck("Password", passwordRegex, passwordMessage);
		String regex = "^" + passwordValue + "$";
		confirmPasswordValue = isValidCheck("Confirm Password", regex, confirmPasswordMessage);
		databaseConnection();
	}
	
	String isValidCheck(String field, String regex, String errorMessage) {
		System.out.print(Colour.darkBlue + "\nEnter " + field + " to Continue  -  " + Colour.reset);
		String value = ReusableCode.input.nextLine();
		boolean exists = false;
		String registerDetails[] = ReusableCode.convertStringArray(registerData);
		for (int x = 0; x < registerRow; x += 4) {
			if(value.equals(registerDetails[x]) || value.equals(registerDetails[x + 1])) {
				exists = true;
			}
		}
		Pattern pattern = Pattern.compile(regex);
		if (value.isEmpty() || !pattern.matcher(value).matches()) {
			System.out.println(Colour.red + (value.isEmpty()? field + " is not Empty!!" : errorMessage));
		} 
		else if (exists && registerRow != 0) {
			System.out.println(Colour.red + field + " is Already Exists!!");
		} 
		else if (value.equalsIgnoreCase("admin") && field.equals("Username")) {
			System.out.println(Colour.red + "'Admin' Username is not allowed, Please Try Other Username!!");
		} 
		else {
			System.out.println(Colour.green + "Correct");
			return value;
		}
		return isValidCheck(field, regex, errorMessage);
	}

	void databaseConnection() {
		String sql = "insert into RegisterDetails values(" + "'" + userNameValue + "','" + emailValue + "','" + passwordValue + "','" + confirmPasswordValue + "')";
		new JdbcConnect().executeUpdateQuery(sql, "RegisterPage");
	}
}

public class RegisterPage {
	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("RegisterPage");
	}
}
