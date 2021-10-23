package com.warmlight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.warmlight.daos.EmployeeDao;
import com.warmlight.models.Employee;
import com.warmlight.utils.ConnectionUtil;

/**
 * This unit testing class performs tests for the methods to check their
 * operations whether reasonable and correct.
 *
 */
@TestMethodOrder(OrderAnnotation.class)
public class UnitTesting {

	EmployeeDao ed = new EmployeeDao();
	List<Employee> employees = ed.getEmployees();

	@BeforeAll
	public static void beforeAllTests() {
		System.out.println(">>>Running the Unit Test for checking this application<<<.");
	}

	@BeforeEach
	public void beforeEachTest() {
		System.out.println("\nThe test method start-------------------------");
	}

	@AfterEach
	public void afterEachTest() {
		System.out.println("The test method end---------------------------");
	}

	@Test
	@Order(1)
	public void testDataConnection() throws SQLException {

		System.out.println("Testing the data connection...");
		Connection connection = ConnectionUtil.getConnection();
		assertTrue(connection.prepareStatement("select * from \"UsingAWS\".employees where employee_id = 3")
				.executeQuery().next());
		assertNotNull(connection);
	}

	@Test
	@Order(2)
	public void testGetEmployees() {

		System.out.println("Testing the getEmployees method...");
		int allEmployees = 6;
		assertEquals(allEmployees, employees.size());
		assertTrue(employees.get(5).getLastName().contains("Curry"));
	}

	@Test
	@Order(3)
	public void testAddEmployee() throws SQLException {

		System.out.println("Testing the addEmployee method...");
		Connection connection = ConnectionUtil.getConnection();
		Employee addEmployee = new Employee("Henry", "Lee", "2021-10-10", "608-345-9876", "henryL@gmail.com", 4);
		ed.addEmployee(addEmployee);
		assertTrue(connection.prepareStatement("select employee_id from \"UsingAWS\".employees where  lastName = 'Lee'")
				.executeQuery().next());
	}

	@Test
	@Order(4)
	public void testRemoveEmployee() throws SQLException {

		System.out.println("Testing the removeEmployee method...");
		Connection connection = ConnectionUtil.getConnection();
		int employee_id = 7;
		ed.removeEmployee(employee_id);
		assertFalse(connection.prepareStatement("select * from \"UsingAWS\".employees where  employee_id = 7")
				.executeQuery().next());
	}

	@Test
	@Order(5)
	public void testChangeEmail() throws SQLException {

		System.out.println("Testing the changeEmail method...");
		Connection connection = ConnectionUtil.getConnection();
		int employee_id = 5;
		String new_email = "mary2021@gmail.com";
		ed.changeEmail(employee_id, new_email);
		assertTrue(connection
				.prepareStatement(
						"select employee_id from \"UsingAWS\".employees where emailAddress='mary2021@gmail.com'")
				.executeQuery().next());
	}

	@Test
	@Order(6)
	public void testGetEmployeesByTitle() {

		System.out.println("Testing the getEmployeesByRole method...");
		String roleTitle = "Software Engineer";
		List<Employee> getEmployees = ed.getEmployeesByTitle(roleTitle);
		assertNotNull(getEmployees);
		int roleIdOfSWE = 3;
		int count = 0;
		for (int i = 0; i < getEmployees.size(); i++) {
			if (i < getEmployees.size()) {
				assertEquals(roleIdOfSWE, getEmployees.get(i).getRole_id_fk());
				count++;
			} else {
				return;
			}
		}
		System.out.println(count + " people are working as an Software Engineer.");
	}
}
