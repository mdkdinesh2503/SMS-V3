package com.aspiresys.StudentManagementSystem;

public class StudentDashboard extends Colour {
	public static String username = ManageLoginDetails.userNameValue;
	public static String email = ManageLoginDetails.emailValue;
	public static boolean studentDetailsAccess = true, attendenceAccess = false;
	public static int AttendanceMarkedCount, StudentDetailsLength, getValue;

	public static void start() {
		int length = (AttendanceMarkedCount == 0) ? 0 : AttendanceMarkedCount; 
		Header.studentDashboardHeader(length, username);
		studentDashboardMenu();
	}

	public static void studentDashboardMenu() {
		Header.studentDashboardModule();
		footer();
	}
	
	private static boolean isValidStudentDetails(String message) {
		if (StudentDetailsLength != 0) {
		} else {
			System.out.println(red + "Please Fill the Student details to " + message + "..");
			footer();
		}
		return true;
	}
	
	private static void isValidAttendanceAccess() {
		if (attendenceAccess) {
			StudentAttendance.main(null);
		} else {
			System.out.println(red + "You have Already Marked the Attendance, Today..\n");
			studentDashboardMenu();
		}
	}
	
	private static void isValidStudentDetailsAccess() {
		if (!studentDetailsAccess) {
			StudentDetails.main(null);
		} else {
			System.out.println(red + "Student Details is Already Filled..\n");
			studentDashboardMenu();
		}
	}

	private static void footer() {
		new JdbcConnect().executeSelectQuery("StudentDashboard");
		String choice = ReusableCode.choiceSelector("Choice");
		switch (choice) {
			case "1" -> isValidStudentDetailsAccess();
			case "2" -> { if (isValidStudentDetails("Mark Attendance")) { isValidAttendanceAccess(); } }
			case "3" -> StudentTimetable.main(null);
			case "4" -> { if (isValidStudentDetails("View Fees Details")) { StudentFees.main(null); } }
			case "5" -> StudentExam.main(null);
			case "6" -> StudentResults.main(null);
			case "7" -> { if (isValidStudentDetails("Send Report")) { StudentReports.main(null); } }
			case "8" -> StudentProfile.main(null);
			case "0" -> { Header.logOutHeader(); System.exit(0); }
			default -> {
				System.out.print(red + "Invalid choice! Please try again..\n" + reset);
				footer();
			}
		}
	}

	void connectionToDatabase(int detailsRow, String detailsData, int attendanceRow, String attendanceData) {
		AttendanceMarkedCount = attendanceRow;
		StudentDetailsLength = detailsRow;
		String details[] = ReusableCode.convertStringArray(detailsData);
		String attendance[] = ReusableCode.convertStringArray(attendanceData);
		String date = DateAndTime.date;
		int decision = 0, increment = 0;
		for (int i = 2; i < details.length; i += 12) {
			if (!email.equals(details[i])) { decision++; } 
		}
		for (int i = 3; i < attendance.length; i += 7) {
			if (email.equals(attendance[i]) && date.equals(attendance[i + 2])) { increment++; }
		}
		attendenceAccess = (increment == 0);
		studentDetailsAccess = (detailsRow != decision);
	}

	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("StudentDashboard");
		start();
	}
}
