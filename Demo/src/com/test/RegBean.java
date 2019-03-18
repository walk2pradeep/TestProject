package com.test;


import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class RegBean {
	
	@NotEmpty @Length(min=5, max=30)
	private String uname;
	@NotEmpty @Email 
	private String email;
	@NotNull(message="Enter date of birth boss!") @DateTimeFormat(pattern="dd/MM/yy") @Past
	private Date dob;
	
	public RegBean() {
		System.out.println("in RB no-arg constr");
	}
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		System.out.println("in setUname() of RB "+uname );
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		System.out.println("in setEmail() of RB "+email);
		this.email = email;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegBean other = (RegBean) obj;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RegBean [uname=" + uname + ", email=" + email + ", dob=" + dob
				+ "]";
	}
	
	
}
