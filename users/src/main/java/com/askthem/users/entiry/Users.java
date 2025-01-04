package com.askthem.users.entiry;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.askthem.users.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class Users implements UserDetails{
	@Id
	@Column(name="userid", length=10)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;
	
	@Column(name = "username", length = 60)
	private String username;
	
	@Column(name = "useremail", length = 60)
	private String useremail;
	
//	@Transient 
	@Column(name = "userpassword", length = 60)
	private String userpassword;
	
	@Enumerated(EnumType.STRING)
	private Role userrole;
	
	@Column(name = "accesstoken")
	private String accesstoken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	return List.of(new SimpleGrantedAuthority(userrole.name()));
//        return userrole.getAuthorities();
    }

    @Override
    public String getUsername() {
        return username;
    }

	@Override
	public String getPassword() {
		return userpassword;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
