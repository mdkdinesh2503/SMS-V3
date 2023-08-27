package com.aspiresys.StudentManagementSystem;

import java.util.regex.*;

public class PaymentPage implements inputFieldValidation {
	private String name, email, year;
	private int registerNo, amount;

	public void setValues(String name, String email, String year, int registerNo, int amount) {
		this.name = name;
		this.email = email;
		this.year = year;
		this.registerNo = registerNo;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getYear() {
		return year;
	}

	public int getRegisterNo() {
		return registerNo;
	}

	public int getAmount() {
		return amount;
	}

	PaymentPage(String name, int registerNo, String email, int amount, String year) {
		Header.feesPaymentHeader();
		String labels[] = {"\n\tFull Name \t:  ", "\n\tRegister Number :  ", "\n\tEmail \t\t:  ", "\n\tYear \t\t:  ", "\n\tAmount \t\t:  "};
		String values[] = {name, String.valueOf(registerNo), email, year, String.valueOf(amount)};
		for(int i = 0; i < labels.length; i++) {
			System.out.print(Colour.darkBlue + labels[i] + Colour.reset + values[i]);
		}
		String condition[] = year.split(" ");
		setValues(name, email, condition[0], registerNo, amount);
		performPaymentPage();
	}

	void performPaymentPage() {
		System.out.print(Colour.brown + "\n\n1 - Credit Card, 2 - Debit Card, 3 - UPI");
		String choice = ReusableCode.choiceSelector("Choice");
		String valid[] = {"1", "2", "3"};
		String field[] = {"Credit Card", "Debit Card", "UPI"};
		if(ReusableCode.isValidChoice(valid, choice)) {
			paymentContainer(field[Integer.parseInt(choice)-1]);
		} else {
			System.out.print(Colour.red + "Invalid choice! Please enter 1, 2, or 3..");
			performPaymentPage();
		}
	}

	void paymentContainer(String field) {
		if(field.equals("Credit Card") || field.equals("Debit Card")) {
			System.out.print(Colour.brown + Colour.underline + "\nCard Details :\n" + Colour.reset);
			isValidCheck("Card Number", cardNumberRegex, cardNumberMessage);
			isValidCheck("Month & Year", monthYearRegex, monthYearMessage);
			isValidCheck("CVV", cvvRegex, cvvMessage);
		} else {
			System.out.print(Colour.brown + Colour.underline + "\nUPI Details :\n" + Colour.reset);
			isValidCheck("UPI ID", upiRegex, upiMessage);
		}
		databaseConnection();
	}
	
	String isValidCheck(String field, String regex, String errorMessage) {
		System.out.print(Colour.darkBlue + "\t\tEnter " + field + " to Continue  -  " + Colour.reset);
		String value = ReusableCode.input.nextLine();
		Pattern pattern = Pattern.compile(regex);
		if (value.isEmpty() || !pattern.matcher(value).matches()) {
            System.out.println(Colour.red + (value.isEmpty()? "\t\t" + field + " is required!" : errorMessage));
            return isValidCheck(field, regex, errorMessage);
        }
        System.out.println(Colour.green + "\t\tCorrect\n");
        return value;
	}

	void databaseConnection() {
		String sql = "UPDATE StudentFees SET " + getYear() + "Status = 'Paided', " + getYear() + "Date = '" + DateAndTime.date + "', " + getYear() + "Time = '" + DateAndTime.time + "' WHERE RegisterNumber = '" + getRegisterNo() + "';";
		new JdbcConnect().executeUpdateQuery(sql, "PaymentPage");
	}
}
