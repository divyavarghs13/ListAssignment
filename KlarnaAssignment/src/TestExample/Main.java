package TestExample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static final String CSV_PATH = "D:\\Divya\\MyTransactions.csv";
	public static final int creditLimit = 300;
	public static boolean append = true;
	public static ArrayList<String> aList = new ArrayList<String>();
	public static List<String> rejectedList = new ArrayList<String>();

	public static void main(String[] args) throws IOException {

		readAllLinesFromFile(CSV_PATH);
		System.out.println("Unsorted:\n");
		for (String aStudentString : aList) {
			System.out.println(aStudentString + "\n");
		}

		ArrayList<Student> students = convertToStudents(aList);
		System.out.println("SORTED:\n");
		List<String> keylist = new ArrayList<String>();
		List<Integer> amountlist = new ArrayList<Integer>();
		List<String> trIDlist = new ArrayList<String>();

		for (Student student : students) {
			keylist.add(student.getKey());
			amountlist.add(student.getMark());
			trIDlist.add(student.getGrade());
			// System.out.println(student.getKey()+" : "+student.getMark()+" :
			// "+student.getGrade());
		}

		if(creditLimit>0) {
		for (int i = 0; i < (keylist.size() - 1); i++) {
			if (keylist.get(i).equals(keylist.get(i + 1))) {
				if (amountlist.get(i) > creditLimit) {
					// rejectedList.add(amountlist.get(i).toString());
					rejectedList.add(trIDlist.get(i).toString());
				} else if (amountlist.get(i) + amountlist.get(i + 1) > creditLimit) {
					// rejectedList.add(amountlist.get(i+1).toString());
					rejectedList.add(trIDlist.get(i + 1).toString());
				}
			} else {
				if (amountlist.get(i) > creditLimit) {
					// rejectedList.add(amountlist.get(i).toString());
					rejectedList.add(trIDlist.get(i).toString());
				}
			}
		}
		}
		System.out.println("rejected Transactions: "+rejectedList);
	}

	public static ArrayList<String> readAllLinesFromFile(String path) throws IOException {

		FileReader fileReader = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			aList.add(line);
		}
		bufferedReader.close();

		return aList;

	}

	public static ArrayList<Student> convertToStudents(ArrayList<String> studentsStrings) {
		ArrayList<Student> students = new ArrayList<>();
		studentsStrings.remove(0);
		for (String studentString : studentsStrings) {
			String[] parts = studentString.split(",");
			String fName = parts[0];
			String lName = parts[1];
			String email = parts[2];
			String key = fName + lName + email;
			int mark = Integer.valueOf(parts[3]);
			String grade = parts[4];
			students.add(new Student(key, mark, grade));
		}
		Collections.sort(students, Student.NameComparator);
		// Collections.sort(students, Student.AmountComparator);
		return students;
	}

}