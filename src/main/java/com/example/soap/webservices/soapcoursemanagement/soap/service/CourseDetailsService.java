package com.example.soap.webservices.soapcoursemanagement.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.soap.webservices.soapcoursemanagement.soap.bean.Course;

@Component
public class CourseDetailsService {

	private static List<Course> courses = new ArrayList<Course>();
	static {
		Course course1 = new Course(1, "Java", "Learn Java");
		courses.add(course1);

		Course course2 = new Course(2, "Spring", "Learn Spring");
		courses.add(course2);

		Course course3 = new Course(3, "Spring Boot", "Learn Spring Boot");
		courses.add(course3);

		Course course4 = new Course(1, "Spring MVC", "Learn Spring MVC");
		courses.add(course4);

	}

	public Course findById(int id) {
		for (Course course : courses) {
			if (course.getId() == id)
				return course;
		}

		return null;
	}

	public List<Course> findAll() {
		return courses;
	}

	public int deleteById(int id) {
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			Course course = iterator.next();
			if (course.getId() == id) {
				iterator.remove();
				return 1;
			}
		}
		return 0;
	}
}
