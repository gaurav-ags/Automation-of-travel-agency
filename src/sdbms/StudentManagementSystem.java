package sdbms;
//interface
public interface StudentManagementSystem {
	void addStudent();
	void displayStudent();
	void displayAllStudents();
	void removeStudent();
	void removeAllStudents();
	void updateStudent();
	void countStudent();
	void sortStudent();
	void findStudentWithHighestMarks();
	void findStudentWithLowestMarks();
}

// All the methods inside interface are automatically public & abstract
