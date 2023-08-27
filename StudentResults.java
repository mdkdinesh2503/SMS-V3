package com.aspiresys.StudentManagementSystem;

public class StudentResults extends Colour {
	
	void connectionToDatabase(int resultsRow, String resultsData, String loginEmail) {
		String results[] = ReusableCode.convertStringArray(resultsData);
		int array[] = ReusableCode.forLoopStatements(2, 12, results, loginEmail);
		if (resultsRow == array[1]) { System.out.println(red + "Result is not Published for you..\n"); } 
		else {
			Header.studentResultsHeader();
			printStudentResult(results, array[0]);
		}
		StudentDashboard.studentDashboardMenu();
	}
	
	void printStudentResult(String results[], int getValue) {
		String space[] = {"\n\t\t  ", "\t\t", "\n\n\t\t\t\t    ", "\n\t ", "\t\t", "\n\n\t\t\t", "\n\n\t ", "\t\t", "\n\n\t\t      ", "\n\n\t", "\t\t", "\n\t", "\t\t"};
		String labels[] = {"  Register Number : ", "  Email : ", results[getValue - 1] + " : ", "  Professional Ethics (PE) : ", " Database Systems (DBS) : ", "  Service Oriented Architecture (SOA) : ", "  Internet Of Things (IOT) : ", " Graphics Multimedia (GM) : ", "  Graphics Multimedia Laboratory (GM LAB)  : ", " High Speed Networks (HSN)/ : ", "   Big Data (BD) /\t: ", " Business Intelligence (BI) \t      ", "   Cloud Computing (CC) \t   "};
		String values[] = {results[getValue - 2] + " ", results[getValue] + "  ", results[getValue + 1], results[getValue + 2] + " GRADE ", results[getValue + 4] + " GRADE ", results[getValue + 5] + " GRADE ", results[getValue + 6] + " GRADE ", results[getValue + 8] + " GRADE ", results[getValue + 9] + " GRADE ", results[getValue + 3] + " GRADE ", results[getValue + 7] + " GRADE  ", "", ""};
		for(int i = 0; i < labels.length; i++) {
			if(i != 2) { System.out.print(space[i] + Bg_lightBlue + bold + white + labels[i] + values[i] + reset); } 
			else { System.out.print(space[i] + borderText + (values[i].equalsIgnoreCase("PASS")? green + "    " + labels[i] + values[i] + "    " : red + " " + labels[i] + values[i] + " ") + "\n" + reset); }
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {
		new JdbcConnect().executeSelectQuery("StudentResults");
	}
}
