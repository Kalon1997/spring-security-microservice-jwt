package com.askthem.users.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.askthem.users.constants.UserConstants.Login;
import com.askthem.users.dto.UserAddReq;
import com.askthem.users.dto.UserListReq;
import com.askthem.users.dto.UserLoginReq;
import com.askthem.users.entiry.Users;
import com.askthem.users.exceptionhandler.UserLoginException;
import com.askthem.users.service.JWTService;
import com.askthem.users.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTService jwtService;

	@GetMapping("/user/csrf-token")
	public CsrfToken fetchCsrf(HttpServletRequest req) {
		return (CsrfToken) req.getAttribute("_csrf");
	}
	
	@GetMapping("/user/profile")
	public Users fetchProfile(@RequestHeader(name = "Authorization") String token) {
		String jwtToken = token.substring(7);
//		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = jwtService.extractUserName(jwtToken);
		Users foundUser = userService.getUserByUsername(username);
		return foundUser;
	}
	
	@PostMapping("/registeruser")
//	@ResponseStatus(code = HttpStatus.CREATED)
	public Users registerUser(@RequestBody UserAddReq userAddReq){
		System.out.println("===userAddReq========"+userAddReq);
		return userService.registerUser(userAddReq);
	}
	
	@PostMapping("/loginuser")
	public ResponseEntity<?> loginUser(@RequestBody UserLoginReq userLoginReq) throws UserLoginException {
		Users foundUser = userService.loginUser(userLoginReq);
		if(foundUser != null) {
			return new ResponseEntity<>(foundUser, HttpStatus.OK);
		}else {
			 throw new UserLoginException("Login.msg +  with  + Login.code");
		}
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin/listuser")
	public List<Users> listUsers(){
		List<Users> list = userService.listUser();
		return list;
	}
	
	@GetMapping("/user/{id}")
	public Optional<Users> getUserById(@PathVariable long id) {
		Optional<Users> foundUser = userService.fetchUser(id);
		return foundUser;
	}
	
//	For Feign or REST template, validate user and validate admin
	@GetMapping("/admin/validateadmin")
	public Boolean validateAdmin() {
		return true;
	}
	
	@GetMapping("/user/validateuser")
	public Boolean validateUser() {
		return true;
	}
	
	@ExceptionHandler(UserLoginException.class)
	public ResponseEntity<Object> handleUserLoginException(UserLoginException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
	}
}
