//package com.askthem.users.service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.askthem.users.entiry.UserPrincipal;
//import com.askthem.users.entiry.Users;
//import com.askthem.users.repository.UserRepository;
//
//import lombok.Data;
//
//@Service
//@Data
//public class MyUserDetailsService implements UserDetailsService{
//
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		Users user = userRepository.findByUsername(username);
//		if(user == null) {
//			System.out.println("user not found");
//			throw new UsernameNotFoundException("User NOT FOUND!!");
//		}
////		Set<String> s = new HashSet<>();
////		s.put(user.getUserrole());
////		Set<GrantedAuthority> authorities = 
////                ((Object) s).map((role) -> new SimpleGrantedAuthority(role))
////                .collect(Collectors.toSet());
//		return new UserPrincipal(user);
////		List<User> userList = userRepository.findByUserName(username);
////		System.out.println("======userList======"+userList);
//
//	}
//	
//}
