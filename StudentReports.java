package com.aspiresys.StudentManagementSystem;

public class StudentReports extends Colour {
	public static String nameData, emailData;
	public static int registerNumberData;

	void connectionToDatabase(int detailsRow, String detailsData, String loginEmail) {
		String studentDetails[] = ReusableCode.convertStringArray(detailsData);
		int array[] = ReusableCode.forLoopStatements(2, 12, studentDetails, loginEmail);
		if (detailsRow == array[1]) {
			System.out.println(red + "Please Fill the Student details to Send Report..\n");
			StudentDashboard.studentDashboardMenu();
		} else {
			Header.studentReportHeader();
			nameData = studentDetails[array[0] - 2];
			emailData = studentDetails[array[0]];
			registerNumberData = Integer.parseInt(studentDetails[array[0] + 4]);
			performStudentReports();
		}
	}

	void performStudentReports() {
		String message = ReusableCode.choiceSelector("Query");
		if(message.isEmpty()) {
			System.out.println(red +"Message is not Empty!!");
			performStudentReports();
		}else {
			String labels[] = {"\n\tFull Name \t: ", "\n\tRegister Number : ", "\n\tEmail \t\t: ", "\n\tDate \t\t: ", "\n\tTime \t\t: ", "\n\tMessage \t: "};
			String values[] = {nameData, String.valueOf(registerNumberData), emailData, DateAndTime.date, DateAndTime.time, message};
			for(int i = 0; i < labels.length; i++) {
				System.out.print(darkBlue + labels[i] + reset + values[i]);
			}
			databaseConnection(message);
		}
	}
	
	void databaseConnection(String message) {
		System.out.print(brown + "\n\nDo you want to Submit Report? (1 - Yes) : " + reset);
		if (ReusableCode.input.nextLine().equals("1")) {
			System.out.print(green + "\nReport Sended Successfully!!");
			String sql = "insert into StudentReports (FullName, RegisterNumber, Email, date, time, Message) values(" + "'" + nameData + "', " + registerNumberData + ", '" + emailData + "', '" + DateAndTime.date + "', '" + DateAndTime.time + "', '" + message + "');";
			new JdbcConnect().executeUpdateQuery(sql, "InsertStudentValues");
		} else {
			System.out.println(red + "You have chosen not to Send Report..\n");
			StudentDashboard.studentDashboardMenu();
		}
	}

	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("StudentReports");
	}
}
