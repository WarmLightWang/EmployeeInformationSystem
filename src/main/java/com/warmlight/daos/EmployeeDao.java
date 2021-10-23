package com.warmlight.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.warmlight.models.Employee;
import com.warmlight.models.Role;
import com.warmlight.utils.ConnectionUtil;

/**
 * This class is to implement and override methods that are in
 * EmployeeDaoInterface class. These logic operations that interact with user
 * inputs from executing the Menu class
 * 
 */
public class EmployeeDao implements EmployeeDaoInterface {

	Logger log = LogManager.getLogger(EmployeeDao.class);

	/**
	 * The method will return a List of all employees from the database
	 */
	@Override
	public List<Employee> getEmployees() {

		try (Connection cn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM \"UsingAWS\".employees;";
			Statement s = cn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			List<Employee> employeeList = new ArrayList<>();
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("employee_id"), rs.getString("firstName"),
						rs.getString("lastName"), rs.getString("hireDate"), rs.getString("phoneNumber"),
						rs.getString("emailAddress"), rs.getInt("role_id_fk"));
				employeeList.add(employee);
			}
			return employeeList;
		} catch (SQLException e) {
			System.out.println("Something is wrong with access database??");
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	/**
	 * This method will take a new Employee object and add a new employee to the
	 * database
	 */
	@Override
	public void addEmployee(Employee emp) {

		EmployeeDao ed = new EmployeeDao();
		List<Employee> employees = ed.getEmployees();
		ArrayList<Object> phoneNum = new ArrayList<>();
		for (Employee e : employees) {
			phoneNum.add(e.getPhoneNumber());
		}

		if (phoneNum.contains(emp.getPhoneNumber())) {
			log.error("The provided information is invalid for adding an employee??");
			return;
		} else {
			try (Connection cn = ConnectionUtil.getConnection()) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date dt = new Date();
				String currentDate = df.format(dt);

				String sql = "INSERT INTO \"UsingAWS\".employees(firstName, lastName, hireDate, phoneNumber, "
						+ "emailAddress, role_id_fk) VALUES(?, ?, ?, ?, ?, ?);";
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setString(1, emp.getFirstName());
				ps.setString(2, emp.getLastName());
				ps.setDate(3, java.sql.Date.valueOf(currentDate));
				ps.setString(4, emp.getPhoneNumber());
				ps.setString(5, emp.getEmailAddress());
				ps.setInt(6, emp.getRole_id_fk());
				ps.executeUpdate();
				System.out.println("Employee " + emp.getFirstName() + " " + emp.getLastName() + " created!");
				log.info("Add an employee into the database and whose employee id is " + ed.getEmployees().size());
			} catch (SQLException e) {
				System.out.println("Add a new employee failed??");
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method will delete an employee from the database by given whose employee
	 * id
	 */
	@Override
	public void removeEmployee(int empId) {

		try (Connection cn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM \"UsingAWS\".employees WHERE employee_id = ?;";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, empId);
			ps.executeUpdate();
			System.out.println("Remove out the employee id " + empId + " from the roster.");
		} catch (SQLException e) {
			System.out.println("Delete an employee failed??");
			e.printStackTrace();
		}
	}

	/**
	 * This method will change an employee's email address from the database by
	 * given whose employee id
	 */
	@Override
	public void changeEmail(int empId, String newEmail) {

		EmployeeDao ed = new EmployeeDao();
		List<Employee> employees = ed.getEmployees();
		ArrayList<Object> employeesId = new ArrayList<>();
		for (Employee e : employees) {
			employeesId.add(e.getEmployee_id());
		}

		if (!employeesId.contains(empId)) {
			log.error("The provided information is invalid for changing an email??");
			return;
		} else {
			try (Connection cn = ConnectionUtil.getConnection()) {
				String sql = "UPDATE \"UsingAWS\".employees SET emailAddress = ? WHERE employee_id = ?;";
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setString(1, newEmail);
				ps.setInt(2, empId);
				ps.executeUpdate();
				System.out.println("New email address is changed to " + newEmail + " for employee id at " + empId);
			} catch (SQLException e) {
				System.out.println("Change role failed??");
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method will return the lists of employees information from the database
	 * by given a certain role title
	 * 
	 */
	@Override
	public List<Employee> getEmployeesByTitle(String roleInput) {

		RoleDao rd = new RoleDao();
		List<Role> roles = rd.getRoles();
		ArrayList<Object> titles = new ArrayList<>();
		for (Role r : roles) {
			titles.add(r.getRole_title());
		}

		if (!titles.contains(roleInput)) {
			log.error("The input role title doesn't exist in the database??");
			return new ArrayList<>();
		} else {
			try (Connection cn = ConnectionUtil.getConnection()) {
				String sql = "select * from \"UsingAWS\".employees join \"UsingAWS\".roles on "
						+ "employees.role_id_fk = roles.role_id where roles.role_title = ?;";
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setString(1, roleInput);
				ResultSet rs = ps.executeQuery();
				List<Employee> employeeList = new ArrayList<>();
				while (rs.next()) {
					Employee emp = new Employee(rs.getInt("employee_id"), rs.getString("firstName"),
							rs.getString("lastName"), rs.getString("hireDate"), rs.getString("phoneNumber"),
							rs.getString("emailAddress"), rs.getInt("role_id_fk"));
					employeeList.add(emp);
				}
				return employeeList;
			} catch (SQLException e) {
				System.out.println("Something is wrong with the operations??");
				e.printStackTrace();
			}
			return new ArrayList<>();
		}
	}
}
