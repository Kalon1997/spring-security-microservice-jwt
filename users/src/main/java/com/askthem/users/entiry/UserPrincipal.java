//package com.askthem.users.entiry;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class UserPrincipal implements UserDetails{
//	
//	private Users user;
//	
//	public UserPrincipal(Users user) {
//		this.user = user;
//	}
//	
////	@Override
////	public Collection<? extends GrantedAuthority> getAuthorities() {
////		return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
//////		return List.of();
////	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return List.of(new SimpleGrantedAuthority(user.getUserrole().name()));
//	}
//	
//	@Override
//	public String getPassword() {
//		return user.getUserpassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return user.getUsername();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	
//}
