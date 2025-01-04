package com.askthem.users.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserLoginException extends RuntimeException{
	public UserLoginException(String ex) {
		super(ex);
	}
}
