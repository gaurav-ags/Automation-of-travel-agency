package customsorting;

import java.util.Comparator;

import sdbms.Student;

public class SortStudentByMarks implements Comparator<Student> {
	@Override
	public int compare(Student x, Student y) {
		return x.getMarks()-(y.getMarks());
	}

}
// x->is the object to be inserted
//y-> is the already existing object

