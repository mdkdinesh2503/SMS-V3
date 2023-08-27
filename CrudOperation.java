package com.aspiresys.StudentManagementSystem;

abstract class SqlQueries extends Colour {
	public static String createTableQueries[] = {
			"CREATE TABLE LoginDetails (id int auto_increment primary key, username varchar(50) not null, email varchar(50) not null, password varchar(50) not null, date varchar(10) not null, time varchar(11) not null);",
			"CREATE TABLE RegisterDetails (username varchar(50) primary key, email varchar(50) unique, password varchar(50), confirmpassword varchar(50));",
			"CREATE TABLE StudentDetails (FullName varchar(50) not null, dob varchar(10) not null, email varchar(50) unique not null, mobile numeric(10) unique not null, gender varchar(6) not null, age numeric(2) not null, registerNumber numeric(7) primary key not null, degree varchar(7) not null, department varchar(50) not null, yop numeric(4) not null, tenthMark varchar(5) not null, twelthMark varchar(5) not null);",
			"CREATE TABLE StudentAttendance (id int auto_increment primary key, FullName varchar(50) not null, RegisterNumber numeric(7) not null, Email varchar(50) not null, AttendanceField varchar(20) not null, date varchar(10) not null, time varchar(11) not null);",
			"CREATE TABLE StudentReports (id int auto_increment primary key, FullName varchar(50) not null, RegisterNumber numeric(7) not null, Email varchar(50) not null, date varchar(10) not null, time varchar(11) not null, message varchar(100) not null);",
			"CREATE TABLE StudentFees (id int auto_increment primary key, FullName varchar(50) not null, RegisterNumber numeric(7) not null unique, Email varchar(50) not null, Degree varchar(2) not null, Department varchar(3) not null, FirstAmount numeric(7) not null, SecondAmount numeric(7) not null, ThirdAmount numeric(7) not null, FourthAmount numeric(7) not null, FirstStatus varchar(10) not null, SecondStatus varchar(10) not null, ThirdStatus varchar(10) not null, FourthStatus varchar(10) not null, FirstDate varchar(10), SecondDate varchar(10), ThirdDate varchar(10), FourthDate varchar(10), FirstTime varchar(11), SecondTime varchar(11), ThirdTime varchar(11), FourthTime varchar(11));",
			"CREATE TABLE StudentTimetable (id int auto_increment primary key, Day varchar(20) not null unique, FirstPeriod varchar(20) not null, SecondPeriod varchar(20) not null, ThirdPeriod varchar(20) not null, FourthPeriod varchar(20) not null, FifthPeriod varchar(20) not null, SixthPeriod varchar(20) not null, SeventhPeriod varchar(20) not null);",
			"CREATE TABLE StudentResults (RegisterNumber numeric(7) not null primary key, FullName varchar(50) not null unique, Email varchar(50) not null unique, Result varchar(4) not null, PE varchar(10) not null, HSNBI varchar(10) not null, DBS varchar(10) not null, SOA varchar(10) not null, IOT varchar(10) not null, BDCC varchar(10) not null, GM varchar(10) not null, GMLAB varchar(10) not null);",
			"CREATE TABLE HallAllocation (id int primary key, year varchar(50) not null, department varchar(50) not null, students varchar(3) not null, hallNumber varchar(50) not null);",
			"CREATE TABLE ExamTimetable (id int primary key, TimingFrom varchar(20) not null, TimingTo varchar(20) not null, pe varchar(20) not null, hsnBi varchar(20) not null, dbs varchar(20) not null, soa varchar(20) not null, iot varchar(20) not null, bdCc varchar(20) not null, gm varchar(20) not null, gml varchar(20) not null, FirstDay varchar(20) not null, SecondDay varchar(20) not null, ThirdDay varchar(20) not null, FourthDay varchar(20) not null, FifthDay varchar(20) not null, SixthDay varchar(20) not null, SeventhDay varchar(20) not null, EightDay varchar(20) not null);"};

	public static String displayTableQueries[] = { "SELECT * FROM LoginDetails;", "SELECT * FROM RegisterDetails;", "SELECT * FROM StudentDetails;",
			"SELECT * FROM StudentAttendance;", "SELECT * FROM StudentReports;", "SELECT * FROM StudentFees;", "SELECT * FROM StudentTimetable;",
			"SELECT * FROM StudentResults;", "SELECT * FROM HallAllocation;", "SELECT * FROM ExamTimetable;" };

	public static String deleteTableQueries[] = { "DROP TABLE LoginDetails;", "DROP TABLE RegisterDetails;", "DROP TABLE StudentDetails;",
			"DROP TABLE StudentAttendance;", "DROP TABLE StudentReports;", "DROP TABLE StudentFees;", "DROP TABLE StudentTimetable;",
			"DROP TABLE StudentResults;", "DROP TABLE HallAllocation;", "DROP TABLE ExamTimetable;" };

	public static String deleteRowQueries[] = { "DELETE FROM LoginDetails WHERE ", "DELETE FROM RegisterDetails WHERE ", "DELETE FROM StudentDetails WHERE ", 
			"DELETE FROM StudentAttendance WHERE ", "DELETE FROM StudentReports WHERE ", "DELETE FROM StudentFees WHERE ", 
			"DELETE FROM StudentTimetable WHERE ", "DELETE FROM StudentResults WHERE ", "DELETE FROM HallAllocation WHERE ",
			"DELETE FROM ExamTimetable WHERE "};

	public static String updateTableQueries[] = { "UPDATE LoginDetails SET ", "UPDATE RegisterDetails SET ",
			"UPDATE StudentDetails SET ", "UPDATE StudentAttendance SET ", "UPDATE StudentReports SET ","UPDATE StudentFees SET ",
			"UPDATE StudentTimetable SET ", "UPDATE StudentResults SET ", "UPDATE HallAllocation SET ","UPDATE ExamTimetable SET " };
}

public class CrudOperation extends SqlQueries {

	private static void body(String choice) {
		System.out.print(brown + "\n1 - Login Page, 2 - Register Page, 3 - Student Details, 4 - Student Attendance, \n5 - Student Reports, 6 - Student Fees, 7 - Student Timetable, 8 - Student Results, \n9 - Hall Allocation, 10 - Exam Timetable");
		String decision = ReusableCode.choiceSelector("choice");
		String valid[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		if (ReusableCode.isValidChoice(valid, decision)) {
			int index = Integer.parseInt(decision) - 1;
			JdbcConnect connectDatabase = new JdbcConnect();
			switch (choice) {
				case "1" -> connectDatabase.performCrudOperation(createTableQueries[index], "create");
				case "2" -> connectDatabase.performCrudOperation(deleteTableQueries[index], "delete");
				case "3" -> connectDatabase.performCrudOperation(displayTableQueries[index], "display");
				case "4" -> {
					String changeValue = ReusableCode.choiceSelector("Change Value");
					String condition = ReusableCode.choiceSelector("Condition");
					connectDatabase.performCrudOperation( updateTableQueries[index] + changeValue + " WHERE " + condition + ";", "update");
				}
				case "5" -> {
					String value = ReusableCode.choiceSelector("Condition");
					connectDatabase.performCrudOperation(createTableQueries[index] + " WHERE " + value + ";", "delete");
				}
			}
		} else {
			System.out.print(red + "Invalid choice! Please Try again..\n");
			CrudOperation.main(null);
		}
	}

	public static void main(String[] args) {
		System.out.print(purple + "\n1 - CREATE TABLE, 2 - DELETE TABLE, 3 - DISPLAY TABLE, 4 - UPDATE TABLE, 5 - DELETE ROW");
		String choice = ReusableCode.choiceSelector("choice");
		String valid[] = {"1", "2", "3", "4", "5"};
		if (ReusableCode.isValidChoice(valid, choice)) {
			body(choice);
		} else {
			System.out.print(red + "Invalid choice! Please Try again..\n");
			CrudOperation.main(null);
		}
	}
}
