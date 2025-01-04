package com.askthem.users.exceptionhandler;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ProblemDetail handleSecurityException(Exception ex) {
		ProblemDetail errorDetail = null;
		
		if(ex instanceof BadCredentialsException) { //ex.getMessage()
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), "BadCredentialsException");
			errorDetail.setProperty("success", false);
			errorDetail.setProperty("timestamp", new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
		}
		
		if(ex instanceof AccessDeniedException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), "AccessDeniedException");
			errorDetail.setProperty("success", false);
			errorDetail.setProperty("timestamp", new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
		}
		
		if(ex instanceof SignatureException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), "SignatureException");
			errorDetail.setProperty("success", false);
			errorDetail.setProperty("timestamp", new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
		}
		
		if(ex instanceof NullPointerException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), "NullPointerException");
			errorDetail.setProperty("success", false);
			errorDetail.setProperty("timestamp", new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
		}
		return errorDetail;
	}
}
