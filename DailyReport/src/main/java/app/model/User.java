package app.model;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class User{
	private Integer id; 
	
	@NotNull(message = "{NotEmpty.user.name}")
	private String username; 
	@NotNull(message = "{NotEmpty.password}")
	private String password; 
	private String fullname; 
	private String phone; 
	private Integer role ; 
	private Integer isDeleted; 
	private Integer division_id; 
	
	public User() {
		
	}
	
	public User(String username, String password) {
		this.username = username; 
		this.password = password; 
	}
	
	public User(String username, String password, int role) {
		this.username = username; 
		this.password = password; 
		this.role = role; 
	}
	
	public User(Integer id, String username, String password, String fullname, String phone, int role, int isDeleted, int division_id) {
		this.id = id; 
		this.username = username; 
		this.password = password; 
		this.fullname = fullname; 
		this.phone = phone; 
		this.role = role; 
		this.setIsDeleted(0); 
		this.setDivision_id(division_id); 
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
		
	public Integer getDivision_id() {
		return division_id;
	}

	public void setDivision_id(Integer division_id) {
		this.division_id = division_id;
	}

	public void display() {
		System.out.println("User Info: \n" + "username: " + username + " - password: " + password);  
	}

	public String getRoleString() {
		if (this.role == 1) {
			return "ADMIN";
		} else {
			return "USER";
		}
	}

	@Transient
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(this.getRoleString()));
		return authorities;
	}
}