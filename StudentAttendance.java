package com.aspiresys.StudentManagementSystem;

public class StudentAttendance extends Colour {
	public static String nameData, emailData, field;
	public static int registerNumberData;

	void connectionToDatabase(int detailsRow, String detailsData, String loginEmail) {
		String studentDetails[] = ReusableCode.convertStringArray(detailsData);
		int array[] = ReusableCode.forLoopStatements(2, 12, studentDetails, loginEmail);
		if (detailsRow == array[1]) {
			System.out.println(red + "Please Fill the Student details to Mark Attendance..\n");
			StudentDashboard.studentDashboardMenu();
		} else {
			nameData = studentDetails[array[0] - 2];
			emailData = studentDetails[array[0]];
			registerNumberData = Integer.parseInt(studentDetails[array[0] + 4]);
			Header.studentAttendanceHeader();
			performStudentAttendance();
		}
	}

	void performStudentAttendance() {
		String getTime = DateAndTime.time;
		String field = "";
		int minute = Integer.parseInt(getTime.substring(3, 5));
		if (getTime.startsWith("09") && getTime.endsWith("am") && minute >= 0 && minute <= 15) { field = "Present"; } 
		else if (getTime.startsWith("09") && getTime.endsWith("am") && minute > 15 && minute <= 30) { field = "Excuse"; } 
		else { field = "Absent"; }
		StudentAttendance.field = field;
		displayAttendanceDetails();
	}

	void displayAttendanceDetails() {
		String labels[] = {"\n\tFull Name \t : ", "\n\tRegister Number  : ", "\n\tEmail \t\t : ", "\n\tAttendance Field : ", "\n\tDate \t\t : ", "\n\tTime \t\t : "};
		String values[] = {nameData, String.valueOf(registerNumberData), emailData, field, DateAndTime.date, DateAndTime.time};
		for(int i = 0; i < labels.length; i++) {
			System.out.print(darkBlue + labels[i] + reset + values[i]);
		}
		databaseConnection();
	}

	void databaseConnection() {
		System.out.print(brown + "\n\nDo you want to Submit your Attendance? (1 - Yes) : " + reset);
		if (ReusableCode.input.nextLine().equals("1")) {
			System.out.print(green + "\nYou have Marked Attendence Successfully!!" + reset);
			String sql = "insert into StudentAttendance (FullName, RegisterNumber, Email, AttendanceField, date, time) values(" + "'" + nameData + "', " + registerNumberData + ", '" + emailData + "', '" + field + "', '" + DateAndTime.date + "','" + DateAndTime.time + "')";
			new JdbcConnect().executeUpdateQuery(sql, "InsertStudentValues");
		} else {
			System.out.println(red + "You have chosen not to Mark Attendence..\n");
			StudentDashboard.studentDashboardMenu();
		}
	}

	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("StudentAttendance");
	}
}
