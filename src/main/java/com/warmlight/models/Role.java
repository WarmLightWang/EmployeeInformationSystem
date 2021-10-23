package com.warmlight.models;

import java.util.Objects;

/**
 * This bean class is to encapsulate objects into Role
 *
 */
public class Role {

	private int role_id;
	private String role_title;
	private int role_salary;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(int role_id, String role_title, int role_salary) {
		super();
		this.role_id = role_id;
		this.role_title = role_title;
		this.role_salary = role_salary;
	}

	public Role(String role_title, int role_salary) {
		super();
		this.role_title = role_title;
		this.role_salary = role_salary;
	}

	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role_title=" + role_title + ", role_salary=" + role_salary + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(role_id, role_salary, role_title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return role_id == other.role_id && role_salary == other.role_salary
				&& Objects.equals(role_title, other.role_title);
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_title() {
		return role_title;
	}

	public void setRole_title(String role_title) {
		this.role_title = role_title;
	}

	public int getRole_salary() {
		return role_salary;
	}

	public void setRole_salary(int role_salary) {
		this.role_salary = role_salary;
	}

}
