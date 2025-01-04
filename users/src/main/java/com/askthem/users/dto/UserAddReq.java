package com.askthem.users.dto;

import com.askthem.users.enums.Role;

//import com.askthem.users.entiry.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddReq {
	@NotBlank(message="username can not be empty")
	private String username;
	@Email(message="useremail must be valid")
	@Pattern(regexp = "[a-z][A-Z][0-9]*@+.+$", message = "invalid useremail")
	private String useremail;
	@NotBlank(message ="userpassword mus be valid")
	private String userpassword;
	@NotBlank(message = "plz provide role")
	private Role userrole;
}
