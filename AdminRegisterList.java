package com.aspiresys.StudentManagementSystem;

public class AdminRegisterList extends Colour{

	void connectionToDatabase(int registerRow, String registerData) {
		System.out.println(borderText + Bg_lightBlue + white + " S.NO\t  USERNAME\t     EMAIL\t\t PASSWORD    CONFIRM PASSWORD   " + reset + borderText);
		if(registerRow != 0) {
			String registerList[] = ReusableCode.convertStringArray(registerData);
			int usernameLength[] = ReusableCode.lengthIdentify(registerRow, registerList, 0, 4);
			int emailLength[] = ReusableCode.lengthIdentify(registerRow, registerList, 1, 4);
			int passwordLength[] = ReusableCode.lengthIdentify(registerRow, registerList, 2, 4);
			for (int index = 0, number = 1; index < registerList.length; index += 4, number++) {
				System.out.print("  " + number + "\t  " + registerList[index]);
				ReusableCode.spaceAllocation(registerList[index].length(), usernameLength[usernameLength.length - 1]);
				System.out.print(registerList[index + 1]);
				ReusableCode.spaceAllocation(registerList[index + 1].length(), emailLength[emailLength.length - 1]);
				System.out.print(registerList[index + 2]);
				ReusableCode.spaceAllocation(registerList[index + 2].length(), passwordLength[passwordLength.length - 1]);
				System.out.print(registerList[index + 3]);
				ReusableCode.spaceAllocation(registerList[index + 3].length(), passwordLength[passwordLength.length - 1]);
				System.out.println();
			}
			performAdminRegisterList(registerList);
		} else {
			System.out.println(red + "\t\t\t\t   No Registered Users Inserted   \t\t\t\n");
			AdminDashboard.start();
		}
	}

	private static void performAdminRegisterList(String registerList[]) {
		System.out.print(reset + brown + "\n1 - Delete, 2 -  Menu ");
		String choice = ReusableCode.choiceSelector("Choice");
		if (choice.equals("1")) {
			ReusableCode.performAdminStatements("Delete Register Details", registerList, 0, 4);
		} else if (choice.equals("2")) {
			System.out.println();
			AdminDashboard.start();
		} else {
			System.out.println(red + "Invalid choice! Please enter 1 or 2..");
			performAdminRegisterList(registerList);
		}
	}

	public static void deleteUser(String registerList[], String username) {
		System.out.print(brown + "\nDo you want to delete "+ username + " Records? (1 - Yes) : ");
		if(ReusableCode.input.nextLine().equals("1")) {
			String sql = "DELETE FROM RegisterDetails WHERE username= '" + username + "';";
			new JdbcConnect().executeUpdateQuery(sql, "AdminRegisterList");
		}else {
			System.out.println(red + "You have chosen not to delete the Data..");
			performAdminRegisterList(registerList);
		}
	}

	public static void main(String[] args) {
		Header.adminRegisterListHeader();
		new JdbcConnect().executeSelectQuery("AdminRegisterList");
	}
}
