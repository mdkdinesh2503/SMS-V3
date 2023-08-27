package com.aspiresys.StudentManagementSystem;

public class AdminDetails extends Colour{

	void connectionToDatabase(int detailsRow, String detailsData) {
		System.out.println(borderText + Bg_lightBlue + white + " S.NO\t  REGISTER NO.\t     NAME\t\tMOBILE NO.       DOB   \t  " + reset + borderText);
		if(detailsRow != 0) {
			String details[] = ReusableCode.convertStringArray(detailsData);
			int nameLength[] = ReusableCode.lengthIdentify(detailsRow, details, 0, 12);
			for (int index = 0, number = 1; index < details.length; index += 12, number++) {
				System.out.print("  " + number + "\t    " + details[index + 6] + "\t  " + details[index]);
				ReusableCode.spaceAllocation(details[index].length(), nameLength[nameLength.length - 1]);
				System.out.print(details[index + 3] + "    " + details[index + 1] + "  \n");
			}
			performAdminDetails(details);
		} else {
			System.out.println(red + " \t\t\t No Student Details Inserted \t\t\t  \n");
			AdminDashboard.start();
		}
	}
	
	private static void performAdminDetails(String details[]) {
		System.out.print(reset + brown + "\n1 - Delete, 2 - View, 3 - Menu");
		String choice = ReusableCode.choiceSelector("Choice");
        String[] actions = {"Delete User Details", "View User Details"};
		if (choice.equals("1") || choice.equals("2")) {
			ReusableCode.performAdminStatements(actions[Integer.parseInt(choice)-1], details, 0, 6);
		} else if (choice.equals("3")) {
			System.out.println();
			AdminDashboard.start();
		} else {
			System.out.println(red + "Invalid choice! Please enter 1, 2, or 3..");
			performAdminDetails(details);
		}
	}

	public static void viewUser(String details[], int value) {
		String labels[] = {"\t\tFull Name \t:   ", "\t\tDate of Birth \t:   ", "\t\tEmail \t\t:   ", "\t\tMobile Number \t:   ", "\t\tGender \t\t:   ", "\t\tAge \t\t:   ", "\t\tRegister Number :   ", "\t\tDegree \t\t:   ", "\t\tDepartment \t:   ", "\t\tYear of Passing :   ", "\t\t10th Mark (% out of 100) : ", "\t\t12th Mark (% out of 100) : " };
		for (int i = value - 6, j = 0; i <= value + 5; i++, j++) {
            System.out.print(darkBlue + labels[j] + reset + details[i] + "\n");
        }
		performAdminDetails(details);
	}

	public static void deleteUser(String details[], String registerNumber) {
		System.out.print(brown + "\nDo you want to delete "+ registerNumber + " Records? (1 - Yes) : ");
		if(ReusableCode.input.nextLine().equals("1")) {
			String sql = "DELETE FROM StudentDetails WHERE registerNumber = " + registerNumber + ";";
			String query = "DELETE FROM StudentFees WHERE registerNumber = " + registerNumber + ";";
			String overallSql = sql + " : " + query;
			new JdbcConnect().executeUpdateQuery(overallSql, "AdminDetails");
		}else {
			System.out.println(red + "You have chosen not to delete Data..");
			performAdminDetails(details);
		}
	}
	
	public static void main(String[] args) {
		Header.adminDetailsHeader();
		new JdbcConnect().executeSelectQuery("AdminDetails");
	}
}
