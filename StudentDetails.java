package com.aspiresys.StudentManagementSystem;

import java.time.*;
import java.util.regex.*;

class ManageStudentDetails implements inputFieldValidation {
	static String name, dob, emailData, gender, tenthMark, twelthMark, detailsData;
	static long mobile;
	static int age, registerNumber, detailsRow;

	void connectionToDatabase(int detailsRow, String detailsData, String loginEmail) {
		ManageStudentDetails.detailsRow = detailsRow;
		ManageStudentDetails.detailsData = detailsData;
		Header.studentDetailsHeader();
		emailData = loginEmail;
		System.out.print(Colour.bold + Colour.gray + "\nPersonal Details :-\n" + Colour.reset);
		getFullName();
	}

	String isValidCheck(String value, String field, String regex, String errorMessage) {
		boolean exists = false;
		String studentDetails[] = ReusableCode.convertStringArray(detailsData);
		for (int x = 3; x < studentDetails.length; x += 12) {
			if (value.equals(studentDetails[x]) || value.equals(studentDetails[x + 3])) {
				exists = true;
			}
		}
		Pattern pattern = Pattern.compile(regex);
		if (value.isEmpty() || !pattern.matcher(value).matches()) {
			System.out.print(Colour.red + "\t\t" + (value.isEmpty()? field + " is not Empty!" : errorMessage) + "\n");
			invalidValueRedirect(field);
		} else if (exists && detailsRow != 0) {
			System.out.print(Colour.red + "\t\t" + field + " is Already Exists!!\n");
			invalidValueRedirect(field);
		}
		return value;
	}
	
	void invalidValueRedirect(String field) {
		if (field.equals("Full Name")) getFullName(); else if (field.equals("Date Of Birth")) getDob();
		else if (field.equals("Mobile Number")) getMobile(); else if (field.equals("Gender")) getGender();
		else if (field.equals("Register Number")) getRegisterNumber(); else if (field.equals("10th Mark")) getTenthMark();
		else getTwelthMark();
	}

	void getFullName() {
		System.out.print(Colour.darkBlue + "\t\tFull Name \t:   " + Colour.reset);
		ManageStudentDetails.name = isValidCheck(ReusableCode.input.nextLine(), "Full Name", fullNameRegex, fullNameMessage);
		getDob();
	}

	void getDob() {
		System.out.print(Colour.darkBlue + "\t\tDate of Birth \t:   " + Colour.reset);
		ManageStudentDetails.dob = isValidCheck(ReusableCode.input.nextLine(), "Date Of Birth", dobRegex, dobMessage);
		System.out.print(Colour.darkBlue + "\t\tEmail \t\t:   " + Colour.reset + emailData + "\n");
		getMobile();
	}

	void getMobile() {
		System.out.print(Colour.darkBlue + "\t\tMobile Number \t:   " + Colour.reset);
		ManageStudentDetails.mobile = Long.parseLong(isValidCheck(ReusableCode.input.nextLine(), "Mobile Number", mobileRegex, mobileMessage));
		getGender();
	}

	void getGender() {
		System.out.print(Colour.darkBlue + "\t\tGender \t\t:   " + Colour.reset);
		ManageStudentDetails.gender = isValidCheck(ReusableCode.input.nextLine(), "Gender", genderRegex, genderMessage);
		getAge();
	}

	void getAge() {
		String date[] = dob.split("-");
		String birthDate = "" + date[2] + "-" + date[1] + "-" + date[0];
		int age = Period.between(LocalDate.parse(birthDate), LocalDate.now()).getYears();
		System.out.print(Colour.darkBlue + "\t\tAge \t\t:   " + Colour.reset + age + "\n");
		ManageStudentDetails.age = age;
		System.out.print(Colour.bold + Colour.gray + "\nEducational Details :-\n" + Colour.reset);
		getRegisterNumber();
	}

	void getRegisterNumber() {
		System.out.print(Colour.darkBlue + "\t\tRegister Number :   " + Colour.reset);
		ManageStudentDetails.registerNumber = Integer.parseInt(isValidCheck(ReusableCode.input.nextLine(), "Register Number", registerNumberRegex, registerNumberMessage));
		System.out.print(Colour.darkBlue + "\t\tDegree \t\t:   " + Colour.reset + "BE\n");
		System.out.print(Colour.darkBlue + "\t\tDepartment \t:   " + Colour.reset + "CSE\n");
		System.out.print(Colour.darkBlue + "\t\tYear of Passing :   " + Colour.reset + "2023\n");
		getTenthMark();
	}

	void getTenthMark() {
		System.out.print(Colour.darkBlue + "\t\t10th Mark (% out of 100) : " + Colour.reset);
		ManageStudentDetails.tenthMark = isValidCheck(ReusableCode.input.nextLine(), "10th Mark", markPercentageRegex, markPercentageMessage);
		getTwelthMark();
	}

	void getTwelthMark() {
		System.out.print(Colour.darkBlue + "\t\t12th Mark (% out of 100) : " + Colour.reset);
		ManageStudentDetails.twelthMark = isValidCheck(ReusableCode.input.nextLine(), "12th Mark", markPercentageRegex, markPercentageMessage);
		databaseConnecton();
	}

	void databaseConnecton() {
		String sql = "insert into StudentDetails values(" + "'" + name + "', '" + dob + "', '" + emailData + "', " + mobile + ", '" + gender + "', " + age + ", " + registerNumber + ", 'BE', 'CSE', 2023, '" + tenthMark + "', '" + twelthMark + "');";
		String query = "insert into StudentFees (FullName, RegisterNumber, Email, Degree, Department, FirstAmount, SecondAmount, ThirdAmount, FourthAmount, FirstStatus, SecondStatus, ThirdStatus, FourthStatus) values(" + "'" + name + "', " + registerNumber + ", '" + emailData + "', 'BE', 'CSE', 52000, 54000, 56000, 58000, 'Pending', 'Pending', 'Pending', 'Pending');";
		String overallSql = sql + " : " + query;
		new JdbcConnect().executeUpdateQuery(overallSql, "StudentDetails-StudentFees");
	}
}

public class StudentDetails {
	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("StudentDetails");
	}
}
