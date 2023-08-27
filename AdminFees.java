package com.aspiresys.StudentManagementSystem;

public class AdminFees extends Colour{

	void connectionToDatabase(int feesRow, String feesData) {
		System.out.println(borderText + Bg_lightBlue + white + " S.NO\t  REGISTER NO.\t  1ST STATUS\t  2ND STATUS\t3RD STATUS\t4TH STATUS   " + reset + borderText);
		if(feesRow != 0) {
			String fees[] = ReusableCode.convertStringArray(feesData);
			for (int index = 0, number = 1; index < fees.length; index += 22, number++) {
				System.out.print("  " + number + "\t    " + fees[index + 2] + "\t");
				for(int i = 10; i <= 13 ; i++) {
					System.out.print((fees[index + i].length()==7)? "    " + fees[index + i] + "    " : "    " + fees[index + i] + "     ");
				}
				System.out.print(" \n");
			}
			performAdminFees(fees);
		} else {
			System.out.println(red + " \t\t\t\tNo Fees Details Available\t\t\t     \n");
			AdminDashboard.start();
		} 	
	}
	
	private static void performAdminFees(String fees[]) {
		System.out.print(reset + brown + "\n1 - Search, 2 - Menu ");
		String choice = ReusableCode.choiceSelector("Choice");
		if (choice.equals("1")) {
			ReusableCode.performAdminStatements("Search Fees Details", fees, 2, 22);
		} else if (choice.equals("2")) {
			System.out.println();
			AdminDashboard.start();
		} else {
			System.out.println(red + "Invalid choice! Please enter 1, or 2..");
			performAdminFees(fees);
		}
	}
	
	public static void searchUser(String fees[], String data) {
		String actions[] = {"\n\tFull Name \t\t- ", "\n\tRegister Number \t- ", "\n\tEmail \t\t\t- ", "\n\tDegree \t\t\t- ", "\n\tDepartment \t\t- ", "\n\tFirst Year Status \t- ",
		"\n\tSecond Year Status \t- ", "\n\tThird Year Status \t- ", "\n\tFourth Year Status \t- ", "\n\tFirst Year Date \t- ", "\n\tSecond Year Date \t- ", "\n\tThird Year Date \t- ",
		"\n\tFourth Year Date \t- ", "\n\tFirst Year Time \t- ", "\n\tSecond Year Time \t- ", "\n\tThird Year Time \t- ", "\n\tFourth Year Time \t- "};
		for (int index = 0; index < fees.length; index += 22) {
			if(fees[index + 2].equals(data)) {
				for(int i = 0; i < actions.length; i++) {
					System.out.print(darkBlue + actions[i] + reset);
					System.out.print((i>=0 && i<=4)? fees[index + i + 1] : fees[index + i + 5]);					
				}
				System.out.println();
			}
		}
		performAdminFees(fees);
	}

	public static void main(String[] args) {
		Header.adminFeesHeader();	
		new JdbcConnect().executeSelectQuery("AdminFees");
	}	
}
