package com.example.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.buildsoapservices.courses.CourseDetails;
import com.buildsoapservices.courses.DeleteCourseDetailsRequest;
import com.buildsoapservices.courses.DeleteCourseDetailsResponse;
import com.buildsoapservices.courses.GetAllCourseDetailsRequest;
import com.buildsoapservices.courses.GetAllCourseDetailsResponse;
import com.buildsoapservices.courses.GetCourseDetailsRequest;
import com.buildsoapservices.courses.GetCourseDetailsResponse;
import com.example.soap.webservices.soapcoursemanagement.soap.Exception.CourseNotFoundException;
import com.example.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.example.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import com.example.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService.Status;

@Endpoint
public class CourseDetailsEndpoint {

	@Autowired
	CourseDetailsService service;

	@PayloadRoot(namespace = "http://buildsoapservices.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {

		Course course = service.findById(request.getId());
		
		if(course==null)
		{
			throw new CourseNotFoundException("Invalid Course Id : "+ request.getId());
		}
		return mapCourseDetails(course);
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}

	@PayloadRoot(namespace = "http://buildsoapservices.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(
			@RequestPayload GetAllCourseDetailsRequest request) {

		List<Course> courses = service.findAll();

		return mapAllCourseDetails(courses);
	}

	@PayloadRoot(namespace = "http://buildsoapservices.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		Status status = service.deleteById(request.getId());
		response.setStatus(mapStatus(status));
		return response;
	}

	private com.buildsoapservices.courses.Status mapStatus(Status status) {
		// TODO Auto-generated method stub
		if (status == Status.FAILURE)
			return com.buildsoapservices.courses.Status.FAILURE;
		return com.buildsoapservices.courses.Status.SUCCESS;
	}
}
