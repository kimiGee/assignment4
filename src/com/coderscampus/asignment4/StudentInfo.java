package com.coderscampus.asignment4;

public class StudentInfo {

	private String id = "";
	private String name = "";
	private String course = "";
	private Integer grade = 0;

	public StudentInfo(String id, String name, String course, Integer grade) {
		this.id = id;
		this.name = name;
		this.course = course;
		this.grade = grade;
	}

	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCourse() {
		return course;
	}

	public Integer getGrade() {
		return grade;
	}

}
