package com.aspiresys.StudentManagementSystem;

public class AdminReports extends Colour{

	void connectionToDatabase(int reportsRow, String reportsData) {
		Header.adminReportsHeader();
		System.out.print(borderText + Bg_lightBlue + white + " S.NO\t  REGISTER NO.\t  NAME \t\t  DATE\t\t   TIME\t\t" + reset + borderText + "\n");
		if(reportsRow != 0) {
			String reports[] = ReusableCode.convertStringArray(reportsData);
			int nameLength[] = ReusableCode.lengthIdentify(reportsRow, reports, 1, 7);
			for (int index = 0, number = 1; index < reports.length; index += 7, number++) {
				System.out.print("  " + number + "\t    " + reports[index + 2] + "\t" + reports[index + 1]);
				ReusableCode.spaceAllocation(reports[index + 1].length(), nameLength[nameLength.length - 1]);
				System.out.print(reports[index + 4] + "\t" + reports[index + 5] + "\t\n");
			}
			performAdminReports(reports);
		} else {
			System.out.println(red + " \t\t\tNo Reports Available\t\t\t\t\n");
			AdminDashboard.start();
		}
	}
	
	private static void performAdminReports(String reports[]) {
		System.out.print(reset + brown + "\n1 - View, 2 - Menu");
		String choice = ReusableCode.choiceSelector("Choice");
		if (choice.equals("1")) {
			ReusableCode.performAdminStatements("View Register Details", reports, 2, 7);
		} else if (choice.equals("2")) {
			System.out.println();
			AdminDashboard.start();
		} else {
			System.out.println(red + "Invalid choice! Please enter 1 or 2..");
			performAdminReports(reports);
		}
	}

	public static void viewUser(String[] reports, int value) {
		String labels[] = {"\t\tFull Name \t:   ", "\t\tRegister Number :   ", "\t\tEmail \t\t:   ", "\t\tDate \t\t:   ", "\t\tTime \t\t:   ", "\t\tMessage \t:   "};
		for(int i = value - 1, j = 0; i <= value + 4; i++, j++) {
			System.out.print(darkBlue + labels[j] + reset + reports[i] + "\n");
		}
		performAdminReports(reports);	
	}

	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("AdminReports");		
	}	
}
