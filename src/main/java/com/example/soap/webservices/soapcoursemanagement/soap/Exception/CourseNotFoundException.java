package com.example.soap.webservices.soapcoursemanagement.soap.Exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

//@SoapFault(faultCode = FaultCode.CLIENT)
@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://buildsoapservices.com/courses}_001_Course_NOT_FOUND" )
public class CourseNotFoundException extends RuntimeException {

	public CourseNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}
