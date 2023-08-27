package com.aspiresys.StudentManagementSystem;

public class AdminExam extends Colour {

	void connectionToDatabase(String hallAllocationData, String examTimetableData) {
		String hall[] = ReusableCode.convertStringArray(hallAllocationData);
		String timetable[] = ReusableCode.convertStringArray(examTimetableData);
		Header.adminExamHeader();
		ReusableCode.performExam(hall, timetable, "Admin");		
	}
	
	public static void addExamDetails(String[] hall, String[] timetable, String field) {
		String sql = "";
		System.out.print(bold + gray + "\n1 - Add, 2 - Menu" + reset);
		String choice = ReusableCode.choiceSelector("Choice");
		if(choice.equals("1")) {
			sql = (field.equalsIgnoreCase("Hall Allocation"))? 
			"INSERT INTO HallAllocation values (1, 'Ist Year', 'CSE', '40', 'F201 & F202 & F203');" :
			"INSERT INTO ExamTimetable values (1, '01:30 PM', '04:30 PM', '03-07-2023', '05-07-2023', '07-07-2023', '10-07-2023', '12-07-2023', '14-07-2023', '17-07-2023', '19-07-2023', 'Monday', 'Wednesday', 'Friday', 'Monday', 'Wednesday', 'Friday', 'Monday', 'Wednesday');";
			new JdbcConnect().executeUpdateQuery(sql, "AdminExam");
		} else if(choice.equals("2")) { 
			ReusableCode.performExam(hall, timetable, "Admin"); 
		} else {
			System.out.print(red + "Invalid choice! Please enter 1 or 2..\n" + reset); 
			addExamDetails(hall, timetable, field);
		}
	}
	
	private static void deleteExamDetails(String[] hall, String[] timetable, String field) {
		String sql = "";
		System.out.print(brown + "\nDo you want to Delete Table? (1 - Yes) : " + reset);
		if(ReusableCode.input.nextLine().equals("1")) {
			sql = (field.equalsIgnoreCase("Hall Allocation"))? "DELETE FROM HallAllocation where id = 1;" : "DELETE FROM ExamTimetable where id = 1;";
			new JdbcConnect().executeUpdateQuery(sql, "AdminExam");
		}else {
			if(field.equalsIgnoreCase("Hall Allocation")) {
				System.out.print(red + "You have chosen not to delete Hall Allocation Table..\n" + reset); 
				performUpdateHallAllocations(hall, timetable);
			} else {
				System.out.print(red + "You have chosen not to delete Exam Timetable..\n" + reset); 
				performUpdateTimetable(hall, timetable);
			}
		}
	}
	
	public static void performUpdateHallAllocations(String[] hall, String[] timetable) {
		String sql = "", value = "";
		System.out.print(bold + gray + "\n1 - Update, 2 - Delete, 3 - Menu" + reset);
		String choice = ReusableCode.choiceSelector("Choice");
		if(choice.equals("1")) {
			System.out.print(bold + gray + "\n1 - Number of Students, 2 - Hall Number, 3 - Menu" + reset);
			String data = ReusableCode.choiceSelector("Choice");
			if(data.equals("1") || data.equals("2")) {
				value = ReusableCode.choiceSelector("Updated Value");
				sql = "UPDATE HallAllocation SET " + (data.equals("1")? "students = '" : "hallNumber = '") + value + "' where id = 1;";
				if(!value.isEmpty()) {
					new JdbcConnect().executeUpdateQuery(sql, "AdminExam");
				} else {
					System.out.print(red + "Field is not Empty\n"); 
					performUpdateHallAllocations(hall, timetable);
				}
			} else {
				System.out.print(red + "Invalid choice! Please try again..\n" + reset);
				ReusableCode.performExam(hall, timetable, "Admin");
			}
		} else if(choice.equals("2")) {
			deleteExamDetails(hall, timetable, "Hall Allocation");
		} else if(choice.equals("3")) {
			ReusableCode.performExam(hall, timetable, "Admin");
		} else {
			System.out.print(red + "Invalid choice! Please enter 1, 2, or 3..\n" + reset); 
			performUpdateHallAllocations(hall, timetable);
		}
	}
	
	public static void performUpdateTimetable(String[] hall, String[] timetable) {
		System.out.print(bold + gray + "\n1 - Delete, 2 - Menu" + reset);
		String choice = ReusableCode.choiceSelector("Choice");
		if(choice.equals("1")) {
			deleteExamDetails(hall, timetable, "Timetable");
		} else if (choice.equals("2")) {
			ReusableCode.performExam(hall, timetable, "Admin");
		} else {
			System.out.print(red + "Invalid choice! Please enter 1 or 2..\n" + reset); 
			performUpdateTimetable(hall, timetable);
		}
	}
	
	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("AdminExam");
	}
}
