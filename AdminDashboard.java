package com.aspiresys.StudentManagementSystem;

public class AdminDashboard extends Colour {
	
	public static void start() {
		Header.adminDashboardModule();
		adminDashboardMenu();
	}

	private static void adminDashboardMenu() {
		String choice = ReusableCode.choiceSelector("Choice");
		switch(choice) {
			case "1" -> AdminRegisterList.main(null); 
			case "2" -> AdminDetails.main(null);
			case "3" -> AdminAttendance.main(null); 
			case "4" -> AdminTimetable.main(null);
			case "5" -> AdminFees.main(null);
			case "6" -> AdminExam.main(null);
			case "7" -> AdminResults.main(null);
			case "8" -> AdminReports.main(null);
			case "0" -> { 
				Header.logOutHeader(); 
				System.exit(0); 
			}
			default -> {
				System.out.print(red + "Invalid choice! Please Try again..\n" + reset);
				adminDashboardMenu();
			}
		}
	}

	public static void main(String[] args) {
		Header.adminDashboardHeader();
		start();
	}
}
