package com.aspiresys.StudentManagementSystem;

import java.sql.*;
import java.util.*;

interface JdbcConnectCredentials {
	String url = "jdbc:mysql://127.0.0.1:3306/studentmanagementsystems";
	String name = "root";
	String password = "Dinesh@123";
}

interface SelectQueries extends JdbcConnectCredentials {
	String register = "SELECT * FROM RegisterDetails;";
	String login = "SELECT * FROM LoginDetails;";
	String studentDetails = "SELECT * FROM StudentDetails;";
	String studentAttendance = "SELECT * FROM StudentAttendance;";
	String studentFees = "SELECT * FROM StudentFees;";
	String studentTimetable = "SELECT * FROM StudentTimetable;";
	String studentReports = "SELECT * FROM StudentReports;";
	String studentResults = "SELECT * FROM StudentResults;";
	String studentExamHallAllocation = "SELECT * FROM HallAllocation;";
	String studentExamTimetable = "SELECT * FROM ExamTimetable;";
}

public class JdbcConnect implements SelectQueries {
	private static Connection connection = null;
	String value, loginEmail;
	String detailsData, attendanceData, feesData, registerData, timetableData, reportsData, resultsData, hallAllocationData, examTimetableData;
	int detailsRow, attendanceRow, feesRow, registerRow, timetableRow, reportsRow, resultsRow;

	// -------------- O P E N - J D B C - C O N N E C T --------------
	JdbcConnect() {
		try {
			System.out.print(Colour.hiddenText);
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connection = DriverManager.getConnection(url, name, password);
			System.out.print(Colour.reset);
		} catch (Exception e) {
			System.err.println("Error Occured while Opening - Jdbc Connection : " + e);
		}
	}

	// -------------- C L O S E - J D B C - C O N N E C T --------------
	private static void closeJdbcConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			System.err.println("Error Occured while Closing - Jdbc Connection : " + e);
		}
	}

	// -------------- C R U D - O P E R A T I O N --------------
	void performCrudOperation(String sql, String decision) {
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			switch (decision) {
			case "insert":
				statement.executeUpdate();
				System.out.println(Colour.yellow + "Values Inserted Successfully: " + sql);
				closeJdbcConnection();
				CrudOperation.main(null);
				break;
			case "create":
				statement.executeUpdate();
				System.out.println(Colour.yellow + "Table Created Successfully: " + sql);
				closeJdbcConnection();
				CrudOperation.main(null);
				break;
			case "delete":
				statement.executeUpdate();
				System.out.println(Colour.yellow + "Table Deleted Successfully: " + sql);
				closeJdbcConnection();
				CrudOperation.main(null);
				break;
			case "update":
				statement.executeUpdate();
				System.out.println(Colour.yellow + "Table Updated Successfully: " + sql);
				closeJdbcConnection();
				CrudOperation.main(null);
				break;
			case "display":
				ResultSet result = statement.executeQuery(sql);
				ResultSetMetaData metaData = result.getMetaData();
				int columnCount = metaData.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(metaData.getColumnName(i) + "  \t\t");
				}
				System.out.println("\n");
				while (result.next()) {
					for (int i = 1; i <= columnCount; i++) {
						System.out.print(result.getString(i) + "    \t");
					}
					System.out.println();
				}
				result.close();
				closeJdbcConnection();
				CrudOperation.main(null);
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			System.err.println("\nError Occured while Executing - Crud Operation : " + e);
			CrudOperation.main(null);
		}
	}

	// -------------- E X E C U T E - Q U E R I E S --------------
	private String executeQuery(String sql) {
		try (Statement statement = connection.createStatement()) {
			int rowCount = 0;
			ResultSet rowResult = statement.executeQuery(sql);
			while (rowResult.next()) {
				rowCount++;
			}
			rowResult.close();

			ResultSet result = statement.executeQuery(sql);
			ResultSetMetaData metaData = result.getMetaData();
			int columnCount = metaData.getColumnCount();
			String data[] = new String[rowCount * columnCount];

			int k = 0;
			while (result.next()) {
				for (int i = 1; i <= columnCount; i++) {
					data[k] = (String) result.getString(i);
					k++;
				}
			}
			String value = rowCount + " : " + Arrays.toString(data);
			this.value = value;
		} catch (Exception e) {
			System.err.println("Error Occured in executing - Select Queries : " + e);
		}
		return this.value;
	}

	// -------------- S E L E C T - L O G I N --------------
	private void executeLoginQuery() {
		try (Statement statement = connection.createStatement()) {
			ResultSet result = statement.executeQuery(login);
			String emailValue = "";
			while (result.next()) {
				emailValue = result.getString(3);
			}
			this.loginEmail = emailValue;
			result.close();
		} catch (Exception e) {
			System.err.println("Error Occured while Executing - Login Queries : " + e);
		}
	}

	// -------------- S E L E C T - R E G I S T E R --------------
	private void executeRegisterQuery() {
		String value = executeQuery(register);

		String array[] = value.split(" : ");
		this.registerData = array[1];
		this.registerRow = Integer.parseInt(array[0]);
	}

	// -------------- S E L E C T - D E T A I L S --------------
	private void executeStudentDetailsQuery() {
		String value = executeQuery(studentDetails);

		String array[] = value.split(" : ");
		this.detailsData = array[1];
		this.detailsRow = Integer.parseInt(array[0]);

	}

	// -------------- S E L E C T - A T T E N D A N C E --------------
	private void executeStudentAttendanceQuery() {
		String value = executeQuery(studentAttendance);

		String array[] = value.split(" : ");
		this.attendanceData = array[1];
		this.attendanceRow = Integer.parseInt(array[0]);
	}

	// -------------- S E L E C T - T I M E T A B L E --------------
	private void executeTimetableQuery() {
		String value = executeQuery(studentTimetable);

		String array[] = value.split(" : ");
		this.timetableData = array[1];
		this.timetableRow = Integer.parseInt(array[0]);
	}
	
	// -------------- S E L E C T - H A L L - A L L O C A T I O N --------------
	private void executeHallAllocationQuery() {
		String value = executeQuery(studentExamHallAllocation);

		String array[] = value.split(" : ");
		this.hallAllocationData = array[1];
	}
	
	// -------------- S E L E C T - E X A M - T I M E T A B L E --------------
	private void executeExamTimetableQuery() {
		String value = executeQuery(studentExamTimetable);

		String array[] = value.split(" : ");
		this.examTimetableData = array[1];
	}
		
	// -------------- S E L E C T - F E E S --------------
	private void executeStudentFeesQuery() {
		String value = executeQuery(studentFees);

		String array[] = value.split(" : ");
		this.feesData = array[1];
		this.feesRow = Integer.parseInt(array[0]);
	}
	
	// -------------- S E L E C T - R E S U L T S --------------
	private void executeStudentResultsQuery() {
		String value = executeQuery(studentResults);

		String array[] = value.split(" : ");
		this.resultsData = array[1];
		this.resultsRow = Integer.parseInt(array[0]);
	}
	
	// -------------- S E L E C T - R E P O R T S --------------
	private void executeStudentReportsQuery() {
		String value = executeQuery(studentReports);

		String array[] = value.split(" : ");
		this.reportsData = array[1];
		this.reportsRow = Integer.parseInt(array[0]);
	}

	// -------------- E X E C U T E - S E L E C T - Q U E R I E S --------------
	public void executeSelectQuery(String decision) {

		executeStudentDetailsQuery();
		executeLoginQuery();
		executeRegisterQuery();
		executeStudentAttendanceQuery();
		executeStudentFeesQuery();
		executeTimetableQuery();
		executeStudentReportsQuery();
		executeStudentResultsQuery();
		executeHallAllocationQuery();
		executeExamTimetableQuery();

		closeJdbcConnection();

		// ------------- S E N D - R E G I S T E R -------------
		if (decision.equals("RegisterPage")) {
			ManageRegisterDetails manageRegisterDetails = new ManageRegisterDetails();
			manageRegisterDetails.connectionToDatabase(registerRow, registerData);
		}

		// ------------- S E N D - R E G I S T E R -------------
		else if (decision.equals("LoginPage")) {
			ManageLoginDetails manageLoginDetails = new ManageLoginDetails();
			manageLoginDetails.connectionToDatabase(registerRow, registerData);
		}

		// ------------- S E N D - D E T A I L S - A T T E N D A N C E -------------
		else if (decision.equals("StudentDashboard")) {
			StudentDashboard studentDashboard = new StudentDashboard();
			studentDashboard.connectionToDatabase(detailsRow, detailsData, attendanceRow, attendanceData);
		}

		// ------------- S E N D - R E G I S T E R - L O G I N -------------
		else if (decision.equals("StudentProfile")) {
			StudentProfile studentProfile = new StudentProfile();
			studentProfile.connectionToDatabase(registerRow, registerData, loginEmail);
		}

		// ------------- S E N D - D E T A I L S - L O G I N -------------
		else if (decision.equals("StudentDetails")) {
			ManageStudentDetails manageStudentDetails = new ManageStudentDetails();
			manageStudentDetails.connectionToDatabase(detailsRow, detailsData, loginEmail);
		}

		// ------------- S E N D - D E T A I L S - L O G I N -------------
		else if (decision.equals("StudentAttendance")) {
			StudentAttendance studentAttendance = new StudentAttendance();
			studentAttendance.connectionToDatabase(detailsRow, detailsData, loginEmail);
		}
		
		// ------------- S E N D - T I M E T A B L E -------------
		else if (decision.equals("StudentTimetable")) {
			StudentTimetable studentTimetable = new StudentTimetable();
			studentTimetable.connectionToDatabase(timetableRow, timetableData);
		}
		
		// ------------- S E N D - E X A M - L O G I N -------------
		else if (decision.equals("StudentExam")) {
			StudentExam studentExam = new StudentExam();
			studentExam.connectionToDatabase(hallAllocationData, examTimetableData);
		}

		// ------------- S E N D - F E E S - L O G I N -------------
		else if (decision.equals("StudentFees")) {
			ManageStudentFeesDetails manageStudentFeesDetails = new ManageStudentFeesDetails();
			manageStudentFeesDetails.connectionToDatabase(feesRow, feesData, loginEmail);
		}

		// ------------- S E N D - R E S U L T S - L O G I N -------------
		else if (decision.equals("StudentResults")) {
			StudentResults studentResults = new StudentResults();
			studentResults.connectionToDatabase(resultsRow, resultsData, loginEmail);
		}

		// ------------- S E N D - D E T A I L S - L O G I N -------------
		else if (decision.equals("StudentReports")) {
			StudentReports studentReports = new StudentReports();
			studentReports.connectionToDatabase(detailsRow, detailsData, loginEmail);
		}
		
		// ------------- S E N D - R E G I S T E R -------------
		else if (decision.equals("AdminRegisterList")) {
			AdminRegisterList adminRegisterList = new AdminRegisterList();
			adminRegisterList.connectionToDatabase(registerRow, registerData);
		}
		
		// ------------- S E N D - D E T A I L S -------------
		else if (decision.equals("AdminDetails")) {
			AdminDetails adminDetails = new AdminDetails();
			adminDetails.connectionToDatabase(detailsRow, detailsData);
		}
		
		// ------------- S E N D - A T T E N D A N C E -------------
		else if (decision.equals("AdminAttendance")) {
			AdminAttendance adminAttendance = new AdminAttendance();
			adminAttendance.connectionToDatabase(attendanceRow, attendanceData);
		}
		
		// ------------- S E N D - T I M E T A B L E -------------
		else if (decision.equals("AdminTimetable")) {
			AdminTimetable adminTimetable = new AdminTimetable();
			adminTimetable.connectionToDatabase(timetableRow, timetableData);
		}
		
		// ------------- S E N D - E X A M -------------
		else if (decision.equals("AdminExam")) {
			AdminExam adminExam = new AdminExam();
			adminExam.connectionToDatabase(hallAllocationData, examTimetableData);
		}
		
		// ------------- S E N D - F E E S -------------
		else if (decision.equals("AdminFees")) {
			AdminFees adminFees = new AdminFees();
			adminFees.connectionToDatabase(feesRow, feesData);
		}

		// ------------- S E N D - R E S U L T S -------------
		else if (decision.equals("AdminResults")) {
			AdminResults adminResults = new AdminResults();
			adminResults.connectionToDatabase(resultsRow, resultsData, feesRow, feesData);
		}
		
		// ------------- S E N D - R E P O R T S -------------
		else if (decision.equals("AdminReports")) {
			AdminReports adminReports = new AdminReports();
			adminReports.connectionToDatabase(reportsRow, reportsData);
		}

	}

	// -------------- E X E C U T E - U P D A T E - Q U E R I E S --------------
	public void executeUpdateQuery(String sql, String decision) {
		try (PreparedStatement statement = connection.prepareStatement(sql)) {

			// ------------- I N S E R T - R E G I S T E R -------------
			if (decision.equals("RegisterPage")) {
				statement.executeUpdate(sql);
				System.out.print(Colour.green + "\nYou are Successfully Registered!!");
				System.out.print(Colour.yellow + "\nValues inserted successfully : " + sql + Colour.reset + "\n");
				closeJdbcConnection();
				LoginPage.main(null);
			}

			// ------------- I N S E R T - L O G I N -------------
			else if (decision.equals("Login-Admin") || decision.equals("Login-User")) {
				statement.executeUpdate(sql);
				if (decision.equals("Login-Admin")) {
					System.out.println(Colour.borderText + Colour.green + "\n Hello, " + Colour.brown + " "
							+ ManageLoginDetails.userNameValue + " " + Colour.green + " - You are Successfully logged in! " + Colour.reset);
					closeJdbcConnection();
					AdminDashboard.main(null);
				} else {
					System.out.println(Colour.borderText + Colour.green + "\n Hello, " + Colour.brown + " "
							+ ManageLoginDetails.userNameValue + " " + Colour.green + " - You are Successfully Logged in! " + Colour.reset);
					closeJdbcConnection();
					StudentDashboard.main(null);
				}
			}

			// ------------- U P D A T E - R E G I S T E R -------------
			else if (decision.equals("StudentProfile")) {
				String array[] = sql.split(":");
				statement.executeUpdate(array[0]);
				statement.executeUpdate(array[1]);
				System.out.print(Colour.green + "\nYou are Successfully Updated the Password!!");
				System.out.println(Colour.yellow + "\nValues Updated Successfully : " + sql + Colour.reset + "\n");
				executeSelectQuery("StudentProfile");
			}

			// ------------- I N S E R T - D E T A I L S - F E E S -------------
			else if (decision.equals("StudentDetails-StudentFees")) {
				String array[] = sql.split(":");
				statement.executeUpdate(array[0]);
				statement.executeUpdate(array[1]);
				System.out.print(Colour.green + "\nDetails Inserted Successfully!!");
				System.out.println(Colour.yellow + "\nValues Inserted successfully : " + sql + Colour.reset + "\n");
				closeJdbcConnection();
				StudentDashboard.studentDashboardMenu();
			}

			// ------------- I N S E R T - S T U D E N T S - M O D U L E -------------
			else if (decision.equals("InsertStudentValues")) {
				statement.executeUpdate(sql);
				System.out.println(Colour.yellow + "\nValues Inserted successfully : " + sql + Colour.reset + "\n");
				closeJdbcConnection();
				StudentDashboard.studentDashboardMenu();
			}

			// ------------- U P D A T E - F E E S -------------
			else if (decision.equals("PaymentPage")) {
				statement.executeUpdate(sql);
				System.out.print(Colour.green + "\nPayment Send Successfully!!");
				System.out.println(Colour.yellow + "\nValues Updated successfully : " + sql + Colour.reset + "\n");
				closeJdbcConnection();
				StudentDashboard.studentDashboardMenu();
			}
			
			// ------------- D E L E T E - R E G I S T E R -------------
			else if (decision.equals("AdminRegisterList")) {
				statement.executeUpdate(sql);
				System.out.print(Colour.green + "\nRegister Details Deleted Successfully!!");
				System.out.println(Colour.yellow + "\nValues Deleted successfully : " + sql + Colour.reset + "\n");
				executeSelectQuery("AdminRegisterList");
			}
			
			// ------------- D E L E T E - D E T A I L S -------------
			else if (decision.equals("AdminDetails")) {
				String array[] = sql.split(":");
				statement.executeUpdate(array[0]);
				statement.executeUpdate(array[1]);
				System.out.print(Colour.green + "\nStudent Details Deleted Successfully!!");
				System.out.println(Colour.yellow + "\nValues Deleted successfully : " + sql + Colour.reset + "\n");
				executeSelectQuery("AdminDetails");
			}

			// ------------- U P D A T E - T I M E T A B L E -------------
			else if (decision.equals("AdminTimetable")) {
				statement.executeUpdate(sql);
				System.out.print(Colour.green + "\nTimetable Updated Successfully!!");
				System.out.println(Colour.yellow + "\nValues Updated successfully : " + sql + Colour.reset + "\n");
				executeSelectQuery("AdminTimetable");
			}

			// ------------- D E L E T E - T I M E T A B L E -------------
			else if (decision.equals("AdminTimetable-DeleteRow")) {
				String array[] = sql.split(":");
				statement.executeUpdate(array[0]);
				statement.executeUpdate(array[1]);
				System.out.print(Colour.green + "\nTable Deleted Successfully!!");
				System.out.println(Colour.yellow + "\nTable Deleted successfully : " + sql + Colour.reset + "\n");
				executeSelectQuery("AdminTimetable");
			}

			// ------------- I N S E R T - T I M E T A B L E -------------
			else if (decision.equals("AdminTimetable-AddRow")) {
				String array[] = sql.split(":");
				statement.executeUpdate(array[0]);
				statement.executeUpdate(array[1]);
				statement.executeUpdate(array[2]);
				statement.executeUpdate(array[3]);
				statement.executeUpdate(array[4]);
				System.out.print(Colour.green + "\nTable Added Successfully!!");
				System.out.println(Colour.yellow + "\nTable Added successfully : " + sql + Colour.reset + "\n");
				executeSelectQuery("AdminTimetable");
			}

			// ------------- I N S E R T - E X A M -------------
			else if (decision.equals("AdminExam")) {
				statement.executeUpdate(sql);
				System.out.print(Colour.green + "\nExam Details Updated Successfully!!");
				System.out.println(Colour.yellow + "\nValues Updated successfully : " + sql + Colour.reset + "\n");
				executeSelectQuery("AdminExam");
			}

			// ------------- I N S E R T - R E S U L T S -------------
			else if (decision.equals("AdminResults")) {
				statement.executeUpdate(sql);
				System.out.print(Colour.green + "\nResults Updated Successfully!!");
				System.out.println(Colour.yellow + "\nValues Updated successfully : " + sql + Colour.reset + "\n");
				executeSelectQuery("AdminResults");
			}
			
		} catch (SQLException e) {
			System.err.println("Error Occured while Executing - Insert & Update Queries : " + e);
		}
	}
}
