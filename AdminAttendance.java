package com.aspiresys.StudentManagementSystem;

public class AdminAttendance extends Colour {
	private static int nameLength;

	void connectionToDatabase(int attendanceRow, String attendanceData) {
		if(attendanceRow != 0) {
			String attendance[] = ReusableCode.convertStringArray(attendanceData);
			int nameLength[] = ReusableCode.lengthIdentify(attendanceRow, attendance, 1, 7);
			AdminAttendance.nameLength = nameLength[nameLength.length - 1];
			displayAttendanceDetails(attendance, "total", "Empty");
		} else {
			System.out.println(borderText + Bg_lightBlue + white + " S.NO\t  REGISTER NO.\t     NAME\t\tATTENDANCE FIELD       DATE   \t   TIME\t\t" + reset + borderText);
			System.out.println(red + "  \t\t\t\t\t  No Attendance Marked \t\t\t\t\t\n");
			AdminDashboard.start();
		}		
	}
	
	void displayAttendanceDetails(String attendance[], String field, String data) {
		System.out.println(borderText + Bg_lightBlue + white + " S.NO\t  REGISTER NO.\t     NAME\t\tATTENDANCE FIELD       DATE   \t   TIME\t\t" + reset + borderText);
		for (int index = 0, number = 1; index < attendance.length; index += 7, number++) {
			if(field.equals("total") || (attendance[index + 2].equals(field) && data.equals("Register Number")) || (attendance[index + 4].equalsIgnoreCase(field) && data.equals("Attendance Field")) || (attendance[index + 5].equals(field) && data.equals("Date")) ) {
				System.out.print("  " + number + "\t    " + attendance[index + 2] + "\t  " + attendance[index + 1]);
				ReusableCode.spaceAllocation(attendance[index + 1].length(), nameLength);
				System.out.print("    " + attendance[index + 4] + "\t    ");
				System.out.print(attendance[index + 5] + "   " + attendance[index + 6] + "    \n");
			}
		}
		performAdminAttendance(attendance);
	}

	void performAdminAttendance(String attendance[]) {
		System.out.print(reset + brown + "\n1 - Search, 2 - Menu ");
		String choice = ReusableCode.choiceSelector("Choice");
		if (choice.equals("1")) { searchUser(attendance); } 
		else if (choice.equals("2")) { System.out.println(); AdminDashboard.start(); } 
		else {
			System.out.println(red + "Invalid choice! Please enter 1 or 2..");
			performAdminAttendance(attendance);
		}
	}
	
	void searchUser(String attendance[]) {
		String actions[] = {"Register Number", "Attendance Field", "Date", "2", "4", "5"};
		String valid[] = {"1", "2", "3"};
		System.out.println(purple + "\nSearch Values According to :-");
		System.out.print(brown + "\t\t1 - REGISTER NO., 2 - ATTENDANCE FIELD, 3 - DATE");
		String choice = ReusableCode.choiceSelector("Choice");
		if(ReusableCode.isValidChoice(valid, choice)) {
			isValidDataCheck(attendance, actions[Integer.parseInt(choice)-1], Integer.parseInt(actions[Integer.parseInt(choice)+2]));
		} else {
			System.out.println(red + "Invalid choice! Please enter 1, 2, or 3..");
			searchUser(attendance);
		}		
	}
	
	void isValidDataCheck(String attendance[], String field, int index) {
		System.out.print(darkBlue + "\nEnter " + field + " to View Data - " + reset);
		String view = ReusableCode.input.nextLine();
		boolean validData = false;
		for (int i = index; i < attendance.length; i += 7) {
			if(view.equalsIgnoreCase(attendance[i])) { validData = true; }
		}
		if(validData) { displayAttendanceDetails(attendance, view, field); }
		else {
			System.out.println(red + "Invalid Data! Please Try again..");
			searchUser(attendance);
		}
	}
	
	public static void main(String[] args) {
		Header.adminAttendanceHeader();
		new JdbcConnect().executeSelectQuery("AdminAttendance");
	}
}
