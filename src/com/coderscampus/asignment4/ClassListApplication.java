package com.coderscampus.asignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClassListApplication {
	
	ArrayList<StudentInfo> apmthList = new ArrayList<>();
	ArrayList<StudentInfo> statList = new ArrayList<>();
	ArrayList<StudentInfo> compsciList = new ArrayList<>();

	public static void main(String args[]) throws IOException {

		ClassListApplication list = new ClassListApplication();
		list.readMasterList();
		list.sortClassListsByGrade();

	}
	
	//Read file contents from student-master-list.csv
	public void readMasterList() {
		String fileName = "student-master-list.csv";
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] listEntry = line.split(",");
				Integer grade = 0;
				try {
					grade = Integer.parseInt(listEntry[3]);
					StudentInfo student = new StudentInfo(listEntry[0], listEntry[1], listEntry[2], grade);
					sortStudents(student);
				} catch (NumberFormatException nfe) {
					System.out.println("NumberFormat Exception: invalid input string");
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Sort StudentInfo objects into appropriate lists according to course
	public void sortStudents(StudentInfo student) {
		String course = student.getCourse();
		if (course.startsWith("COMP")) {
			compsciList.add(student);
		} else if (course.startsWith("STA")) {
			statList.add(student);
		} else if (course.startsWith("APM")) {
			apmthList.add(student);
		}

	}

	public void sortClassListsByGrade() throws IOException {

		String compsci = "course1.csv";		
		String apmth = "course2.csv";
		String stat = "course3.csv";

		Collections.sort(compsciList, new Comparator<StudentInfo>() {
			@Override
			public int compare(StudentInfo student1, StudentInfo student2) {

				if (student1.getGrade() == student2.getGrade()) {
					return 0;
				} else if (student1.getGrade() < student2.getGrade()) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		
		writeToFile(compsci, compsciList);

		Collections.sort(apmthList, new Comparator<StudentInfo>() {
			@Override
			public int compare(StudentInfo student1, StudentInfo student2) {

				if (student1.getGrade() == student2.getGrade()) {
					return 0;
				} else if (student1.getGrade() < student2.getGrade()) {
					return 1;
				} else {
					return -1;
				}
			}
		});

		writeToFile(apmth, apmthList);

		Collections.sort(statList, new Comparator<StudentInfo>() {
			@Override
			public int compare(StudentInfo student1, StudentInfo student2) {

				if (student1.getGrade() == student2.getGrade()) {
					return 0;
				} else if (student1.getGrade() < student2.getGrade()) {
					return 1;
				} else {
					return -1;
				}
			}
		});

		writeToFile(stat, statList);

	}

    //write sorted ArrayLists to specified files: course1.csv, course2.csv, and course3.csv
	public void writeToFile(String fileName, ArrayList<StudentInfo> list) throws IOException {

		BufferedWriter writer = null;
		String headers = "Student ID, Student Name, Course, Grade";

		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(headers + "\n");
			for (StudentInfo entry : list) {

				String csvEntry = entry.getID() + "," + entry.getName() + "," + entry.getCourse() + ","
						+ entry.getGrade();
				writer.write(csvEntry + "\n");
			}

		} finally {
			if (writer != null)
				writer.close();
		}

	}

}
