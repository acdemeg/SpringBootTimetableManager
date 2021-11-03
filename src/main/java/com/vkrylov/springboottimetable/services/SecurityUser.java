package com.vkrylov.springboottimetable.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.vkrylov.springboottimetable.entities.User;
import com.vkrylov.springboottimetable.entities.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails{
	
	private final int id;
	private final String username;
	private final String password;
	private final List<SimpleGrantedAuthority> authorities;
	private final boolean isActive;

	public static UserDetails getFromUser(User user){

		Set<SimpleGrantedAuthority> userAuthorities = (user.getRole().equals(UserRole.USER.name()))
				? UserRole.USER.getAuthorities() : UserRole.ADMIN.getAuthorities();

		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				userAuthorities
		);
	}

	public SecurityUser(int id, String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isActive;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isActive;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isActive;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SecurityUser that = (SecurityUser) o;
		return id == that.id && isActive == that.isActive && username.equals(that.username) && password.equals(that.password) && authorities.equals(that.authorities);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, authorities, isActive);
	}
}
