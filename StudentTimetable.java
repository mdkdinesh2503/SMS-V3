package com.aspiresys.StudentManagementSystem;

public class StudentTimetable {

	void connectionToDatabase(int timetableRow, String timetableData) {
		Header.studentTimetableHeader();
		ReusableCode.performTimetable("Student", timetableData, timetableRow);
		StudentDashboard.studentDashboardMenu();
	}

	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("StudentTimetable");
	}
}
