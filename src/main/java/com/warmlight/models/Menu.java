package com.warmlight.models;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.warmlight.daos.EmployeeDao;
import com.warmlight.daos.RoleDao;

/**
 * This class display the menu options for user inputs and does some logic
 * operations depending on these inputs
 *
 */
public class Menu {

	EmployeeDao ed = new EmployeeDao();
	RoleDao rd = new RoleDao();
	final Logger log = LogManager.getLogger(Menu.class);

	/**
	 * This method that creates and displays the menu flow
	 */
	public void display() {

		System.out.println("*************************************************************");
		System.out.println("         Welcome to the Employee Information System          ");
		System.out.println("*************************************************************");
		Scanner sc = new Scanner(System.in);
		boolean menu = true;

		while (menu) {
			System.out.println();
			System.out.println(">>>>Choose an Option<<<<");
			System.out.println("------------------------");
			System.out.println("employees -> will show all employees");
			System.out.println("add -> will add a new employee");
			System.out.println("remove -> will remove a employee");
			System.out.println("changeEmail -> will change an employee's email address");
			System.out.println("employeeByTitle -> will get an employee by thier title");
			System.out.println("roles -> will show all roles information");
			System.out.println("roleInfo -> will get information about a given role");
			System.out.println("changeSalary -> will change a role's salary");
			System.out.println("exit -> will exit the application");
			String input = sc.nextLine();

			switch (input) {

			case "employees": {
				System.out.println("Showing all employees information from the database:");
				List<Employee> employees = ed.getEmployees();
				for (Employee e : employees) {
					System.out.println(e);
				}
				log.info("Going to print all employees information from the database.");
				break;
			}

			case "add": {
				System.out.println("Enter Employee First Name:");
				String firstName = sc.nextLine();
				System.out.println("Enter Employee Last Name:");
				String lastName = sc.nextLine();
				System.out.println("Enter Employee Phone Number:");
				String phone = sc.nextLine();
				System.out.println("Enter Employee Email Address:");
				String email = sc.nextLine();
				System.out.println(
						"Enter Role Id: 1)Chief Leader 2)Team Leader 3)Software Engineer 4)Administrator 5)Marketing Specilist");
				int roleId = sc.nextInt();
				sc.nextLine();

				// placeholder is for hireDate that will change in addEmployee() method
				Employee newEmployee = new Employee(firstName, lastName, "placeholder", phone, email, roleId);
				ed.addEmployee(newEmployee);
				break;
			}

			case "remove": {
				System.out.println("Enter the employee id of the employee to remove:");
				int empIdInput = sc.nextInt();
				sc.nextLine();

				List<Employee> employees = ed.getEmployees();
				if (empIdInput > employees.size() || empIdInput < 1) {
					System.out.println("The employee doesn't exist. Please try an employee id agian!");
					log.warn("User input for the employee id is wrong!!");
				} else {
					ed.removeEmployee(empIdInput);
					log.warn("Going to delete an employee from the database at id " + empIdInput);
				}
				break;
			}

			case "changeEmail": {
				System.out.println("These are all emplyees on the database roster: ");
				List<Employee> employees = ed.getEmployees();
				for (Employee e : employees) {
					System.out.println(e);
				}

				System.out.println("Enter the ID of the employee whose email address is to change:");
				int empIdInput = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the new email address: ");
				String emailInput = sc.nextLine();
				ed.changeEmail(empIdInput, emailInput);
				break;
			}

			case "employeeByTitle": {
				System.out.println("Enter the role title to search for the employees info: (e.g. Team Leader)");
				String roleInput = sc.nextLine();
				List<Employee> employees = ed.getEmployeesByTitle(roleInput);
				for (Employee e : employees) {
					System.out.println(e);
				}
				break;
			}

			case "roles": {
				System.out.println("Showing all information of roles table from the database.");
				List<Role> rolesList = rd.getRoles();
				for (Role r : rolesList) {
					System.out.println(r);
				}
				break;
			}

			case "roleInfo": {
				System.out.println("Which role title would you like to know more about? (e.g. Software Engineer)");
				String titleInput = sc.nextLine();
				List<Role> role = rd.getRoleInfoByTitle(titleInput);
				for (Role r : role) {
					System.out.println(r);
				}
				break;
			}

			case "changeSalary": {
				System.out.println("Enter a role title to change its salary: (e.g. Team Leader)");
				String titleInput = sc.nextLine();
				System.out.println("Enter a new salary for the role:");
				int salaryInput = sc.nextInt();
				sc.nextLine();
				rd.updateRoleSalary(salaryInput, titleInput);
				break;
			}

			case "exit": {
				System.out.println("Application is terminating. Bye!!");
				menu = false;
				break;
			}

			default: {
				System.out.println("Input doesn't match. Try again!!");
				break;
			}

			}
		}
		sc.close();
	}
}
