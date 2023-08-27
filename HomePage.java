package com.aspiresys.StudentManagementSystem;

abstract class HomePageContents extends Colour {
	protected static final int attempts = 3;
}

public class HomePage extends HomePageContents {

	private static void start(int count) {
		System.out.println(brown + "\n      Enter - 1 => Sign-in ( or ) Enter - 2 =>  Sign-up ");
		String choice = ReusableCode.choiceSelector("Choice");
		if ( choice.equals("1")) { LoginPage.main(null); } 
		else if ( choice.equals("2")) { RegisterPage.main(null); } 
		else {
			if (count == attempts) {
				System.out.println(red + "X---------- Invalid Choice! Try after some times ----------X");
				System.exit(0);
			} else {
				count++;
				System.out.println(red + "Invalid choice! Please enter 1 or 2..");
				start(count);
			}
		}
	}

	public static void main(String[] args) {
		Header.homePageHeader();
		start(0);
	}
}
