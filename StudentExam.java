package com.aspiresys.StudentManagementSystem;

public class StudentExam {

	void connectionToDatabase(String hallAllocationData, String examTimetableData) {
		String hall[] = ReusableCode.convertStringArray(hallAllocationData);
		String timetable[] = ReusableCode.convertStringArray(examTimetableData);
		Header.studentExamHeader();
		ReusableCode.performExam(hall, timetable, "Student");
	}

	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("StudentExam");
	}
}
