package com.askthem.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.askthem.users.dto.UserAddReq;
import com.askthem.users.dto.UserListReq;
import com.askthem.users.dto.UserLoginReq;
import com.askthem.users.entiry.Users;
import com.askthem.users.enums.Role;
import com.askthem.users.exceptionhandler.UserLoginException;
import com.askthem.users.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);

	@Override
	public Users registerUser(UserAddReq userAddReq) {
		System.out.println("======userAddReq======"+userAddReq);
		Long userCount = (long) userRepository.findAll().size();
		Users user = new Users(userCount+1,
				userAddReq.getUsername(), 
				userAddReq.getUseremail(), 
				userAddReq.getUserpassword(), 
				userAddReq.getUserrole(),
				null );
        System.out.println("=======user to be created===="+ user);
		user.setUserpassword(encoder.encode(userAddReq.getUserpassword()));
		userRepository.save(user);
		return user;
	}

	@Transactional
	@Override
	public Users loginUser(UserLoginReq userLoginReq) throws UserLoginException{
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginReq.getUsername(), userLoginReq.getUserpassword()));
		System.out.println("========authentication======"+authentication);
		UserDetails userDetailsAfterAuth = userDetailsService.loadUserByUsername(userLoginReq.getUsername());
		System.out.println("========userDetailsAfterAuth======"+userDetailsAfterAuth);
		if(authentication.isAuthenticated())
		{
			// creating updated user obj
			System.out.println("========userLoginReq.getUsername()======"+userDetailsAfterAuth);
			System.out.println("========jwtService.generateToken(userLoginReq.getUsername())======"+jwtService.generateToken(userDetailsAfterAuth));
			int updatedRow = userRepository.updateUserWithToken(userLoginReq.getUsername(), jwtService.generateToken(userDetailsAfterAuth));
			System.out.println("========updated row======"+updatedRow);
			Users foundUser = userRepository.findByUsername(userLoginReq.getUsername());
			return foundUser;
		}
		return null;
			//return jwtService.generateToken(userLoginReq.getUsername());
//		else
//			return "FAILED";
	}

	@Override
	public List<Users> listUser() {
		List<Users> list = userRepository.findAll();
		return list;
	}

	@Override
	public Optional<Users> fetchUser(long id) {
		Optional<Users> foundUser = userRepository.findById(id);
		return foundUser;
	}

	@Override
	public Users getUserByUsername(String username) {
		Users foundUser = userRepository.findByUsername(username);
		return foundUser;
	}
	
	
	
}
