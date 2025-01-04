package com.askthem.users.service;

import java.util.List;
import java.util.Optional;

import com.askthem.users.dto.UserAddReq;
import com.askthem.users.dto.UserListReq;
import com.askthem.users.dto.UserLoginReq;
import com.askthem.users.entiry.Users;
import com.askthem.users.exceptionhandler.UserLoginException;

public interface UserService{
	Users registerUser(UserAddReq userAddReq);
	Users loginUser(UserLoginReq userLoginReq) throws UserLoginException;
	List<Users> listUser();
	Optional<Users> fetchUser(long id);
	Users getUserByUsername(String username);
}
