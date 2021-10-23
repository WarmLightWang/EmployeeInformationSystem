package com.warmlight.daos;

import java.util.List;

import com.warmlight.models.Employee;

/**
 * This interface class will be implemented by EmployeeDao class
 *
 */
public interface EmployeeDaoInterface {

	public List<Employee> getEmployees();

	public void addEmployee(Employee emp);

	public void removeEmployee(int empId);

	public void changeEmail(int empId, String newEmail);

	public List<Employee> getEmployeesByTitle(String roleInput);

}
