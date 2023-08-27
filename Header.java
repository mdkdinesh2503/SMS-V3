package com.aspiresys.StudentManagementSystem;

public class Header extends Colour{
	
	public static void homePageHeader() {
		System.out.print("   " + Bg_lightBlue + "  \t\t\t\t\t\t\t  " + reset);
		System.out.print("\n   " + Bg_lightBlue + white + bold + "   S t u d e n t   M a n a g e m e n t   S y s t e m\t  " + reset);
		System.out.println("\n   " + Bg_lightBlue + "\t\t\t\t\t\t\t\t  " + reset);
		System.out.print("\n\t\t " + bold + gray + underline + "( Login to view the Page )\n" + reset);
	}
	
	public static void registerPageHeader() {
		System.out.println("\n\t " + purple + borderText + " < ----- R E G I S T E R  P A G E  ----- > " + reset);
	}
	
	public static void loginPageHeader() {
		System.out.println("\n\t   " + purple + borderText + " < ------- L O G I N  P A G E  ------- > " + reset);
	}
	
	public static void studentProfileHeader() {
		System.out.println("\n\t   " + purple + borderText + " < ------ S t u d e n t  P r o f i l e ------ > " + reset);
		System.out.print(brown + underline + "\nRegister Details :\n" + reset);
	}
	
	public static void studentDashboardHeader(int length, String username) {
		System.out.print("\n\t\t" + Bg_lightBlue + "\t\t\t\t\t       " + reset);
		System.out.print("\n\t\t" + bold + white + Bg_lightBlue + "   S t u d e n t   D a s h b o a r d   \n" + reset);
		System.out.print("\t\t\t" + Bg_lightBlue + "\t\t\t\t\t       \n" + reset);
		dashboard();
		
		System.out.println(darkBlue + "\n|    S T U D E N T S    |    T E A C H E R S    |  A T T E N D A N C E  |");
		System.out.println(brown + "|   Number of Students  |   Number of Teachers  |   Number of Marked    |");
		System.out.println(green + "|\t  175\t\t|\t   60 \t\t|" + "\t   " + length + "\t\t|");
		dashboard();

		System.out.println(reset + "\n\nHi, " + purple + username + reset + " - Welcome to Student Dashboard");
	}
	
	private static void dashboard() {
		int count = 0;
		System.out.print(purple + "X");
		while (count != 3) {
			System.out.print("-----------------------");
			count++;
			System.out.print("X");
		}
	}
	
	public static void studentDashboardModule() {
		System.out.println(white + Bg_black + " Modules : - " + reset);
		System.out.println(darkBlue + "\t\tEnter - 1 = > " + reset + " Student Details");
		System.out.println(darkBlue + "\t\tEnter - 2 = > " + reset + " Attendance");
		System.out.println(darkBlue + "\t\tEnter - 3 = > " + reset + " Timetable");
		System.out.println(darkBlue + "\t\tEnter - 4 = > " + reset + " Fees");
		System.out.println(darkBlue + "\t\tEnter - 5 = > " + reset + " Exam");
		System.out.println(darkBlue + "\t\tEnter - 6 = > " + reset + " Result");
		System.out.println(darkBlue + "\t\tEnter - 7 = > " + reset + " Reports");
		System.out.println(darkBlue + "\t\tEnter - 8 = > " + reset + " Profile");
		System.out.println(darkBlue + "\t\tEnter - 0 = > " + reset + " Logout");
	}
	
	public static void studentDetailsHeader() {
		System.out.println("\n\t   " + purple + borderText + " < ------- S t u d e n t  D e t a i l s ------- > " + reset);
		System.out.print(brown + underline + "\nFill the Details :\n" + reset);
	}
	
	public static void studentAttendanceHeader() {
		System.out.println("\n\t   " + purple + borderText + " < ------ S t u d e n t  A t t e n d a n c e ------ > " + reset);
		System.out.print(brown + underline + "\nMark your Attendance :\n" + reset);
	}
	
	public static void studentTimetableHeader() {
		System.out.println("\n\t\t\t\t " + purple + borderText + " < ------ S t u d e n t  T i m e t a b l e ------ > " + reset);
		System.out.println(Bg_lightBlue + white + "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
		System.out.println("  DATE/    09.00 AM to       09.50 AM to      10.55 AM to    11.40 AM to     01.30 PM to     02.20 PM to     03.10 PM to  ");
		System.out.println("  TIME      09.50 AM          10.40 AM         11.40 AM       12.30 PM        02.20 PM        03.10 PM        04.00 PM    ");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + reset);
	}
	
	public static void studentFeesHeader(String firstStatus, String secondStatus, String thirdStatus, String fourthStatus) {
		System.out.println("\n\t" + purple + borderText + " < -------- S t u d e n t   F e e s -------- > " + reset);
		fees();
		
		System.out.println(darkBlue + "\n|   " + borderText + " 1ST YEAR " + reset + darkBlue + "  |   " + borderText + " 2ND YEAR " + reset + darkBlue + "  |   "
				+ borderText + " 3RD YEAR " + reset + darkBlue + "  |   " + borderText + " 4TH YEAR " + reset + darkBlue + "  |");
		System.out.println(brown + "|    52000/y\t|    54000/y\t|    56000/y\t|    58000/y\t|");
		System.out.println(green + "|    " + firstStatus + "\t|    " + secondStatus + "\t|    " + thirdStatus + "\t|    " + fourthStatus + "\t|");
		fees();
	}
	
	private static void fees() {
		int count = 0;
		System.out.print(purple + "X");
		while (count != 4) {
			System.out.print("---------------");
			count++;
			System.out.print("X");
		}
	}
	
	public static void studentExamHeader() {
		System.out.println("\n\t" + purple + borderText + " < ------ S t u d e n t  E X A M ------ > " + reset);
	}
	
	public static void studentResultsHeader() {
		System.out.println("\n\t\t\t" + purple + borderText + " < ------ S t u d e n t  R E S U L T S ------ > " + reset);
	}
	
	public static void studentReportHeader() {
		System.out.println("\n\t" + purple + borderText + " < -------- S t u d e n t  R e p o r t -------- > " + reset);
		System.out.print(brown + underline + "\nFill the Details :\n" + reset);
	}
	
	public static void feesPaymentHeader() {
		System.out.println("\n\t" + purple + borderText + " < --------- F e e s  -  P a y m e n t --------- > " + reset);
		System.out.print(brown + underline + "\nUser Details :" + reset);
	}
	
	public static void adminDashboardHeader() {
		System.out.print("\n\t\t" + Bg_lightBlue + "\t\t\t\t\t       " + reset);
		System.out.print("\n\t\t" + bold + white + Bg_lightBlue + "      A d m i n   D a s h b o a r d    \n" + reset);
		System.out.print("\t\t\t" + Bg_lightBlue + "\t\t\t\t\t       \n" + reset);
		dashboard();
		
		System.out.println(darkBlue + "\n|    S T U D E N T S    |    T E A C H E R S    |      A D M I N        |");
		System.out.println(brown + "|   Number of Students  |   Number of Teachers  |   Number of Admins    |");
		System.out.println(green + "|\t  175\t\t|\t   60 \t\t|" + "\t   01\t\t|");
		dashboard();

		System.out.println(reset + "\n\nHi, " + purple + "Admin" + reset + " - Welcome to the Dashboard");
	}
	
	public static void adminDashboardModule() {
		System.out.println(white + Bg_black + " Modules : - " + reset);
		System.out.println(darkBlue + "\t\tEnter - 1 = > " + reset + " Register List");
		System.out.println(darkBlue + "\t\tEnter - 2 = > " + reset + " Student Details");
		System.out.println(darkBlue + "\t\tEnter - 3 = > " + reset + " Attendance");
		System.out.println(darkBlue + "\t\tEnter - 4 = > " + reset + " Timetable");
		System.out.println(darkBlue + "\t\tEnter - 5 = > " + reset + " Fees");
		System.out.println(darkBlue + "\t\tEnter - 6 = > " + reset + " Exam");
		System.out.println(darkBlue + "\t\tEnter - 7 = > " + reset + " Result");
		System.out.println(darkBlue + "\t\tEnter - 8 = > " + reset + " Reports");
		System.out.println(darkBlue + "\t\tEnter - 0 = > " + reset + " Logout");
	}
	
	public static void adminRegisterListHeader() {
		System.out.println("\n\t" + purple + borderText + " < --------- A d m i n  -  R e g i s t e r - L i s t --------- > " + reset);
		System.out.print(brown + underline + "\nRegister List :\n" + reset);
	}
	
	public static void adminDetailsHeader() {
		System.out.println("\n\t  " + purple + borderText + " < --------- A d m i n  -  D e t a i l s --------- > " + reset);
		System.out.print(brown + underline + "\nStudents Details :\n" + reset);
	}
	
	public static void adminAttendanceHeader() {
		System.out.println("\n\t\t" + purple + borderText + " < --------- A d m i n  -  A t t e n d a n c e --------- > " + reset);
		System.out.print(brown + underline + "\nAttendance List :\n" + reset);
	}
	
	public static void adminTimetableHeader() {
		System.out.println("\n\t\t\t\t " + purple + borderText + " < ------ A d m i n  -  T i m e t a b l e ------ > " + reset);
		System.out.println(Bg_lightBlue + white + "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
		System.out.println("  DATE/    09.00 AM to       09.50 AM to      10.55 AM to    11.40 AM to     01.30 PM to     02.20 PM to     03.10 PM to  ");
		System.out.println("  TIME      09.50 AM          10.40 AM         11.40 AM       12.30 PM        02.20 PM        03.10 PM        04.00 PM    ");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + reset);
	}
	
	public static void adminExamHeader() {
		System.out.println("\n\t" + purple + borderText + " < ------ A d m i n -  E X A M ------ > " + reset);
	}
	
	public static void adminFeesHeader() {
		System.out.println("\n\t\t  " + purple + borderText + " < -------- A d m i n  -  F e e s -------- > " + reset);
		System.out.print("\t");
		fees();
		
		System.out.println(darkBlue + "\n\t|   " + borderText + " 1ST YEAR " + reset + darkBlue + "  |   " + borderText + " 2ND YEAR " + reset + darkBlue + "  |   "
				+ borderText + " 3RD YEAR " + reset + darkBlue + "  |   " + borderText + " 4TH YEAR " + reset + darkBlue + "  |");
		System.out.println(brown + "\t\t|     52000/y   |     54000/y   |     56000/y   |     58000/y   |");
		System.out.print("\t");
		fees();
		
		System.out.print(brown + underline + "\n\nFees List :\n" + reset);
	}
	
	public static void adminResultsHeader() {
		System.out.println("\n\t     " + purple + borderText + " < -------- A d m i n  -  R E S U L T S -------- > " + reset);		
		System.out.print(brown + underline + "\nResults List :\n" + reset);
	}
	
	public static void adminReportsHeader() {
		System.out.println("\n\t     " + purple + borderText + " < -------- A d m i n  -  R E P O R T S -------- > " + reset);		
		System.out.print(brown + underline + "\nReports List :\n" + reset);
	}
	
	public static void logOutHeader() {
		System.out.println(Bg_purple + bold + white + "\nYou are Successfully Logged Out!! - Thank you for Using our Website\t");
		System.out.println("\t\t       We will catch you later!!   \t\t\t");
		System.out.println(Bg_green + bold + brown + "<----------------------- T - H - E -- E - N - D ----------------------->" + reset);
	}

}
