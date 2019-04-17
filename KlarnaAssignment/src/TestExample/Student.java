package TestExample;

import java.util.Comparator;

public class Student implements Comparable<Student>{

public int mark;
public String key;
public String grade;

public Student(String key, int mark, String grade) {
    super();
    this.key = key;
    this.mark = mark;
    this.grade = grade;
}

public String getKey() {
	return key;
}

public int getMark() {
    return mark;
}

public String getGrade() {
    return grade;
}

@Override
public int compareTo(Student s) {
	 return this.getGrade().compareTo(s.getGrade());
}

public static Comparator<Student> NameComparator = new Comparator<Student>() {
@Override
public int compare(Student s1, Student s2) {
	int keyname = s1.getKey().compareTo(s2.getKey());
	return keyname == 0 ? s1.getGrade().compareTo(s2.getGrade()) : keyname;	
}
};

@Override
public String toString() {
	return "Student [mark=" + mark + ", key=" + key + ", grade=" + grade + "]";
}


}