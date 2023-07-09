package sdbms;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import customexception.InvalidChoiceException;
import customexception.StudentNotFoundException;
import customsorting.SortStudentByAge;
import customsorting.SortStudentById;
import customsorting.SortStudentByMarks;
import customsorting.SortStudentByName;

//implementation class
public class StudentManagementSystemImpl implements StudentManagementSystem {

	Scanner scan=new Scanner(System.in);
	//key->Student Id & value-> Student Object;
	Map<String,Student> db= new LinkedHashMap <String,Student>();

	@Override
	public void addStudent() {
		//accepting age
		System.out.println("Enter name");
		String name=scan.next();
		//accepting name
		System.out.println("Enter age:");
		int age=scan.nextInt();
		//accept marks
		System.out.println("Enter Marks");
		int marks=scan.nextInt();

		//create a student instance
		Student std=new Student(name,age,marks);

		//Adding entry inside db(Map)
		db.put(std.getId(),std);

		System.out.println("Student Record inserted Successfully!");
		System.out.println("Your Student Id is "+std.getId());
	}

	@Override
	public void displayStudent() {
		//Accept Student Id->toUppsecase()
		//convert toUppeCase()->jsp101,Jsp101,JSP101
		//check if id is present or not->containsKey()
		//if-> get the Student Object->get() ->getAge(),getName()...

		System.out.println("Enter the student id:");
		String id=scan.next();
		id=id.toUpperCase();	//String id=scan.next().toUpperCase();
		//checking if the id is present or not ->(id==key)
		if(db.containsKey(id)) {
			System.out.println("Student details are as follows");
			Student std=db.get(id); //getting student object based on id
			System.out.println("Id:"+std.getId());
			System.out.println("Name:"+std.getName());
			System.out.println("Age:"+std.getAge());
			System.out.println("Marks:"+std.getMarks());
			//printing ref var as toString() is overridden
		}
		else {
			try {
				String message="Student with Id " +id+" is not Found!";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e ) {
				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	public void displayAllStudents() {
		if(!db.isEmpty()) { //checking if DB is Not Empty
			System.out.println("Student Records are as follows:");
			System.out.println("===============================");

			Set<String> keys=db.keySet(); //JSP101 JSP102 JSP103
			for(String key :keys) {
				Student std=db.get(key); //getting student object
				System.out.println(std); 	//toString() is overridden
				//System.out.println(db.get(key));
			}
		}
		else {
			try {
				throw new StudentNotFoundException("no student records to display");
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}}
	}

	@Override
	public void removeStudent() {
		//Accept Student Id ->toUpperCase
		System.out.println("Enter the student id:");
		String id=scan.next().toUpperCase();
		//containsKey()
		if(db.containsKey(id)) {
			System.out.println("Students record found");
			System.out.println(db.get(id)); //printing student value
			db.remove(id);		//removing the Entry (key & value)
			System.out.println("Student Record Deleted Successfully");
		}
		//else->SNFE
		else {
			try {
				String message="Student with Id " +id+" is not Found!";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e ) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeAllStudents() {
		if(!db.isEmpty()) {
			System.out.println("No. of Students Records :"+db.size());
			db.clear();
			System.out.println("All Student Records Deleted Successfully ");
		}else {
			try {
				String message="No student records to Delete";
				throw new StudentNotFoundException(message);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	public void updateStudent() {
		System.out.println("Enter student id:");
		String id=scan.next();
		if(db.containsKey(id)) {
			System.out.println("Student Record Found");
			Student std=db.get(id); //getting values(Student object)

			System.out.println("1.Update Age/n2.Update name");
			System.out.println("3.Update marks\n Enter choice:");
			int choice=scan.nextInt();

			switch(choice) {
			case 1:
				System.out.println("ENter Age:");
				int age=scan.nextInt();
				std.setAge(age);  //std.setName(scan.next());
				System.out.println("Age updated Successfully");
				break;

			case 2: 
				System.out.println("Enter the name");
				String name=scan.next();
				std.setName(name);
				System.out.println("Name updated Successfully");
				break;

			case 3:
				System.out.println("Enter Marks");
				int marks=scan.nextInt();
				std.setMarks(marks);
				System.out.println("Marks Updated Successfully");
				break;

			default:
				try {
					String message="Invalid Choice,Enter valid Choice";
					throw new InvalidChoiceException(message);
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
			}

		}
		else {
			try {
				String message="Student with Id "+id+" is not Found!!";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}

		}

	}

	@Override
	public void countStudent() {
		System.out.println("No. of Student Records: "+db.size());
	}

	@Override
	public void sortStudent() {
		/**Documentation comment
		 * we cant sort Map bases on values, therefore we are getting
		 * the values from Map & storing it inside list so that we can sort
		 * list using-> Collections.sort(list, sorting logic object)
		 */

		//Reference of List & object of Arraylist storing Student Objects
		List<Student> list= new ArrayList<Student>();

		//Converting Map into Set, which Stores keys(Id)
		Set <String> keys=db.keySet();

		//Traversing Keys (Id)
		for(String key:keys){
			//getting value (Student Object) & adding it into List
			list.add(db.get(key));
		}
		System.out.println("1.Sort Student by Id\n2.Sort Student by Age");
		System.out.println("3.Sort Student by Name\n4. Sort Student By Marks");
		System.out.println("Enter Choice");
		int choice=scan.nextInt();

		switch(choice) {
		case 1:
			Collections.sort(list,new SortStudentById());
			for(Student s: list) {
				System.out.println(s);
			}
			break;
		case 2:
			Collections.sort(list,new SortStudentByAge());
			for(Student s: list) {
				System.out.println(s);
			}
			break;
		case 3:
			Collections.sort(list,new SortStudentByName());
			for(Student s: list) {
				System.out.println(s);
			}
			break;	
		case 4:
			Collections.sort(list,new SortStudentByMarks());
			for(Student s: list) {
				System.out.println(s);
			}
			break;
		default:
			try {
				String message="Invalid Choice, Enter Valid choice";
				throw new InvalidChoiceException(message);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}

	@Override
	public void findStudentWithHighestMarks() {
		List<Student> list= new ArrayList<Student>();
		Set<String>keys=db.keySet();
		for(String key:keys) {
			list.add(db.get(key)); //adding student object from map into list
		}
		Collections.sort(list,new SortStudentByMarks());
		System.out.println("Student with highest Marks:");
		System.out.println(list.get(list.size()-1));
	}

	@Override
	public void findStudentWithLowestMarks() {
		List<Student> list= new ArrayList<Student>();
		Set<String>keys=db.keySet();
		for(String key:keys) {
			list.add(db.get(key)); //adding student object from map into list
		}
		Collections.sort(list,new SortStudentByMarks());
		System.out.println("Student with lowest Marks:");
		System.out.println(list.get(0));
	}

}
