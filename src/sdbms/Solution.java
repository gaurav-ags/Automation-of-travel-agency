package sdbms;
import java.util.Scanner;

import customexception.InvalidChoiceException;
public class Solution {
	public static void main(String[] args) {
		System.out.println("Welcome to Student Database Project");
		System.out.println("-----------------------------------");
		Scanner ip=new Scanner(System.in);
		StudentManagementSystem sms= new StudentManagementSystemImpl();
		while(true) {
			System.out.println("1. Add Student\n"+
					"2. Display Student\n"+
					"3. Display All Students\n"+
					"4. Remove Student\n"+
					"5. Remove All Students\n"+
					"6. Update Student\n"+
					"7. Count Student\n"+
					"8. Sort Student\n"+
					"9. Find Student With Highest Marks\n"+
					"10.Find Student With Lowest Marks\n"+
					"11.Exit\n" );

			int choice= ip.nextInt();
			switch(choice) {
			case 1: sms.addStudent();
			break;
			case 2:sms.displayStudent();
			break;
			case 3: sms.displayAllStudents();
			break;
			case 4: sms.removeStudent();
			break;
			case 5: sms.removeAllStudents();
			break;
			case 6:sms.updateStudent();
			break;
			case 7: sms.countStudent();
			break;
			case 8: sms.sortStudent();
			break;
			case 9: sms.findStudentWithHighestMarks();
			break;
			case 10: sms.findStudentWithLowestMarks();
			break;
			case 11: System.out.println("Closing database.....Thank you");
			System.exit(0);
			break;
			default: 
				try {
					String message="Invalid Choice,Enter Valid Choice";
					throw new InvalidChoiceException(message);
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}

				System.out.println("Invalid choice");
				System.out.println("---------------");
			}
		}


	}

}
