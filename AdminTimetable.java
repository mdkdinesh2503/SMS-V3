package com.aspiresys.StudentManagementSystem;

abstract class ManageAdminTimetable extends Colour{
	protected static final String WEEK[] = { "MON", "TUE", "WED", "THU", "FRI"};
	protected static final String PERIODS[] = { "FirstPeriod", "SecondPeriod", "ThirdPeriod", "FourthPeriod", "FifthPeriod", "SixthPeriod", "SeventhPeriod" };
	protected static final String SUBJECTS[] = { "PE", "HSN/BI", "DBS", "SOA", "IOT", "BD/CC", "GM", "GM LAB", "LIB" };
	protected static final String sqlQueries[] = {"insert into StudentTimetable (Day, FirstPeriod, SecondPeriod, ThirdPeriod, FourthPeriod, FifthPeriod, SixthPeriod, SeventhPeriod) values('MON', 'HSN/BI', 'GM', 'BD/CC', 'PE', 'IOT', 'LIB', 'DBS');", 
				"insert into StudentTimetable (Day, FirstPeriod, SecondPeriod, ThirdPeriod, FourthPeriod, FifthPeriod, SixthPeriod, SeventhPeriod) values('TUE', 'IOT', 'PE', 'DBS', 'GM', 'HSN/BI', 'SOA', 'BD/CC');",
				"insert into StudentTimetable (Day, FirstPeriod, SecondPeriod, ThirdPeriod, FourthPeriod, FifthPeriod, SixthPeriod, SeventhPeriod) values('WED', 'GM', 'HSN/BI', 'SOA', 'BD/CC', 'IOT', 'PE', 'LIB');",
				"insert into StudentTimetable (Day, FirstPeriod, SecondPeriod, ThirdPeriod, FourthPeriod, FifthPeriod, SixthPeriod, SeventhPeriod) values('THU', 'PE', 'DBS', 'BD/CC', 'SOA', 'GM LAB', 'GM LAB', 'HSN/BI');",
				"insert into StudentTimetable (Day, FirstPeriod, SecondPeriod, ThirdPeriod, FourthPeriod, FifthPeriod, SixthPeriod, SeventhPeriod) values('FRI', 'BD/CC', 'IOT', 'HSN/BI', 'LIB', 'PE', 'GM', 'DBS');" };
}

public class AdminTimetable extends ManageAdminTimetable {

	void connectionToDatabase(int timetableRow, String timetableData) {
		Header.adminTimetableHeader();
		ReusableCode.performTimetable("Admin", timetableData, timetableRow);
		addAdminTimetable();
	}
	
	void addAdminTimetable() {
		System.out.print(brown + "1 - Add, 2 - Menu");
		String choice = ReusableCode.choiceSelector("Choice");
		if(choice.equals("1")) {
			String sql = sqlQueries[0] + " : " + sqlQueries[1] + " : " + sqlQueries[2] + " : " + sqlQueries[3] + " : " + sqlQueries[4];
			new JdbcConnect().executeUpdateQuery(sql, "AdminTimetable-AddRow");
		} else if(choice.equals("2")) {
			System.out.println();
			AdminDashboard.start();
		} else {
			System.out.print(red + "Invalid choice! Please enter 1, or 2..\n\n" + reset); 
			addAdminTimetable();
		}
	}
	
	public static void performAdminTimetable() {
		System.out.print(brown + "\n1 - Update, 2 - Delete, 3 - Menu");
		String choice = ReusableCode.choiceSelector("Choice");
		switch(choice) {
			case "1" -> performWeekUpdate();
			case "2" -> performDeleteOperation();
			case "3" -> { System.out.println(); AdminDashboard.start(); }
			default -> {
				System.out.print(red + "Invalid choice! Please enter 1, 2, or 3..\n" + reset); 
				performAdminTimetable();
			}
		}
	}
	
	private static void performWeekUpdate() {
		System.out.print(brown + "\n1 - MONDAY, 2 - TUESDAY, 3 - WEDNESDAY, 4 - THURSDAY, 5 - FRIDAY");
		String choice = ReusableCode.choiceSelector("Choice");
		String valid[] = {"1", "2", "3", "4", "5"};
		if(ReusableCode.isValidChoice(valid, choice)) {
			performPeriodUpdate(WEEK[Integer.parseInt(choice)-1]);
		} else {
			System.out.print(red + "Enter a valid day number (1-5)..\n" + reset); 
			performWeekUpdate();
		}
	}
	
	private static void performPeriodUpdate(String value) {
		System.out.print(brown + "\n1 - First Period, 2 - Second Period, 3 - Third Period, 4 - Fourth Period, 5 - Fifth Period, 6 - Sixth Period, 7 - Seventh Period");
		String choice = ReusableCode.choiceSelector("Choice");
		String valid[] = {"1", "2", "3", "4", "5", "6", "7"};
		if(ReusableCode.isValidChoice(valid, choice)) {
			databaseConnection(value, PERIODS[Integer.parseInt(choice)-1]);
		} else {
			System.out.print(red + "Enter a valid period number (1-7)..\n" + reset); 
			performPeriodUpdate(value);
		}
	}

	private static boolean isValidSubject(String subject) {
        for (String validSubject : SUBJECTS) {
            if (validSubject.equalsIgnoreCase(subject)) { return true; }
        }
        return false;
    }
	
	private static void databaseConnection(String week, String period) {
		System.out.print(brown + "\nDo you want to Update Table? (1 - Yes) : " + reset);
		if(ReusableCode.input.nextLine().equals("1")) {
			System.out.println(brown + "SUBJECTS : - \n\t* PE - Professional Ethics \n\t* HSN/BI - High Speed Network / Business Intelligence \n\t* DBS - Database System \n\t* SOA - Service Oriented Architecture \n\t* IOT - Internet of Things \n\t* BD/CC - Big Data / Cloud Computing \n\t* GM - Graphics Multimedia \n\t* GM LAB - GM Laboratory \n\t* LIB - Library");
			String value = ReusableCode.choiceSelector("Update Value").toUpperCase();
			if(isValidSubject(value)) {
				String sql = "UPDATE StudentTimetable SET " + period + "= '" + value + "' WHERE Day = '" + week + "';";
				new JdbcConnect().executeUpdateQuery(sql, "AdminTimetable");
			} else {
				System.out.println(red + "Enter a Valid Subject name!!");
				databaseConnection(week, period);
			}
		} else {
			System.out.println(red + "You have chosen not to Update the timetable..");
			performAdminTimetable();
		}
	}
	
	private static void performDeleteOperation() {
		System.out.print(brown + "\nDo you want to Delete the timetable? (1 - Yes) : " + reset);
		if (ReusableCode.input.nextLine().equals("1")) {
			String sql = "DROP TABLE StudentTimetable;";
			String query = "CREATE TABLE StudentTimetable (id int auto_increment primary key, Day varchar(20) not null unique, FirstPeriod varchar(20) not null, SecondPeriod varchar(20) not null, ThirdPeriod varchar(20) not null, FourthPeriod varchar(20) not null, FifthPeriod varchar(20) not null, SixthPeriod varchar(20) not null, SeventhPeriod varchar(20) not null);";
			String overallSql = sql + " : " + query;
			new JdbcConnect().executeUpdateQuery(overallSql, "AdminTimetable-DeleteRow");
		} else {
			System.out.println(red + "You have chosen not to delete the timetable..");
			performAdminTimetable();
		}
	}
	
	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("AdminTimetable");
	}
}