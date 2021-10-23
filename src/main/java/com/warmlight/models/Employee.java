package com.warmlight.models;

import java.util.Objects;

/**
 * The bean class is to encapsulate objects into Employee
 *
 */
public class Employee {

	private int employee_id;
	private String firstName;
	private String lastName;
	private String hireDate;
	private String phoneNumber;
	private String emailAddress;
	private int role_id_fk;

	/**
	 * None argument constructor
	 */
	public Employee() {
		super();
	}

	/**
	 * Constructor uses seven fields
	 */
	public Employee(int employee_id, String firstName, String lastName, String hireDate, String phoneNumber,
			String emailAddress, int role_id_fk) {
		super();
		this.employee_id = employee_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hireDate = hireDate;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.role_id_fk = role_id_fk;
	}

	/**
	 * The constructor use six fields
	 */
	public Employee(String firstName, String lastName, String hireDate, String phoneNumber, String emailAddress,
			int role_id_fk) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.hireDate = hireDate;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.role_id_fk = role_id_fk;
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", hireDate=" + hireDate + ", phoneNumber=" + phoneNumber + ", emailAddress=" + emailAddress
				+ ", role_id_fk=" + role_id_fk + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(employee_id, firstName, hireDate, lastName, phoneNumber, emailAddress, role_id_fk);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(emailAddress, other.emailAddress) && employee_id == other.employee_id
				&& Objects.equals(firstName, other.firstName) && Objects.equals(hireDate, other.hireDate)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(phoneNumber, other.phoneNumber)
				&& role_id_fk == other.role_id_fk;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getRole_id_fk() {
		return role_id_fk;
	}

	public void setRole_id_fk(int role_id_fk) {
		this.role_id_fk = role_id_fk;
	}

}
