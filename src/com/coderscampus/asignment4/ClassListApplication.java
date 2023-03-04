package com.coderscampus.asignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ClassListApplication {

	StudentInfo[] allStudents = new StudentInfo[105];
	StudentInfo[] compsci = new StudentInfo[35];
	StudentInfo[] stat = new StudentInfo[35];
	StudentInfo[] math = new StudentInfo[35];

	public static void main(String args[]) throws IOException {

		ClassListApplication list = new ClassListApplication();
		list.readMasterList();
		list.sortClassListsByGrade();

	}

	// Read file contents from student-master-list.csv, create StudentInfo object
	// and add to allStudents array
	public void readMasterList() {
		String fileName = "student-master-list.csv";
		BufferedReader reader = null;
		Integer counter = 0;

		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] listEntry = line.split(",");
				Integer grade = 0;
				try {
					grade = Integer.parseInt(listEntry[3]);
					StudentInfo student = new StudentInfo(listEntry[0], listEntry[1], listEntry[2], grade);
					allStudents[counter] = student;
					counter++;
				} catch (NumberFormatException nfe) {
					System.out.println("NumberFormat Exception: invalid input string");
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.sortStudents();
	}

	// Sort StudentInfo objects from allStudents array into appropriate arrays
	// according to course
	public void sortStudents() {

		Integer compCounter = 0;
		Integer statCounter = 0;
		Integer mathCounter = 0;

		for (StudentInfo entry : allStudents) {
			try {
				String course = entry.getCourse();

				if (course.startsWith("COMP")) {
					compsci[compCounter] = entry;
					compCounter++;
				} else if (course.startsWith("STA")) {
					stat[statCounter] = entry;
					statCounter++;
				} else if (course.startsWith("APM")) {
					math[mathCounter] = entry;
					mathCounter++;
				}
			} catch (NullPointerException e) {
				System.out.println("Null pointer exception in allStudents");
			}
		}

	}

	// sort each array according to grade
	public void sortClassListsByGrade() throws IOException {

		String compsciFile = "course1.csv";
		String mathFile = "course2.csv";
		String statFile = "course3.csv";

		Arrays.sort(compsci, new Comparator<StudentInfo>() {
			@Override
			public int compare(StudentInfo student1, StudentInfo student2) {

				if ((student1 == null) || (student2 == null)) {
					return 0;
				}
				if (student1.getGrade() == student2.getGrade()) {
					return 0;
				} else if (student1.getGrade() < student2.getGrade()) {
					return 1;
				} else {
					return -1;
				}
			}
		});

		writeToFile(compsciFile, compsci);

		Arrays.sort(math, new Comparator<StudentInfo>() {
			@Override
			public int compare(StudentInfo student1, StudentInfo student2) {

				if ((student1 == null) || (student2 == null)) {
					return 0;
				}
				if (student1.getGrade() == student2.getGrade()) {
					return 0;
				} else if (student1.getGrade() < student2.getGrade()) {
					return 1;
				} else {
					return -1;
				}
			}
		});

		writeToFile(mathFile, math);

		Arrays.sort(stat, new Comparator<StudentInfo>() {
			@Override
			public int compare(StudentInfo student1, StudentInfo student2) {

				if ((student1 == null) || (student2 == null)) {
					return 0;
				}
				if (student1.getGrade() == student2.getGrade()) {
					return 0;
				} else if (student1.getGrade() < student2.getGrade()) {
					return 1;
				} else {
					return -1;
				}
			}
		});

		writeToFile(statFile, stat);

	}

	// write sorted array to specified files: course1.csv, course2.csv, and
	// course3.csv
	public void writeToFile(String fileName, StudentInfo[] list) throws IOException {

		BufferedWriter writer = null;
		String headers = "Student ID, Student Name, Course, Grade";

		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(headers + "\n");
			for (StudentInfo entry : list) {
				try {
					String csvEntry = entry.getID() + "," + entry.getName() + "," + entry.getCourse() + ","
							+ entry.getGrade();
					writer.write(csvEntry + "\n");
				} catch (NullPointerException e) {
					System.out.println("Null Pointer exception");
				}
			}

		} finally {
			if (writer != null)
				writer.close();
		}

	}

}
