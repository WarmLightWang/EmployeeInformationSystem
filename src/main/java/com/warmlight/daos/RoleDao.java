package com.warmlight.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.warmlight.models.Role;
import com.warmlight.utils.ConnectionUtil;

/**
 * This class will implement and override the methods that are in the
 * RoleDaoInterface class. These logic operations that interact with the user
 * inputs from executing Menu class
 *
 */
public class RoleDao implements RoleDaoInterface {

	Logger log = LogManager.getLogger(RoleDao.class);

	/**
	 * This method will return all roles'information from the database
	 */
	@Override
	public List<Role> getRoles() {

		try (Connection cn = ConnectionUtil.getConnection()) {
			String sql = "select * from \"UsingAWS\".roles;";
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Role> roleList = new ArrayList<>();
			while (rs.next()) {
				Role role = new Role(rs.getInt("role_id"), rs.getString("role_title"), rs.getInt("role_salary"));
				roleList.add(role);
			}
			return roleList;
		} catch (SQLException e) {

		}
		return null;
	}

	/**
	 * This method will return a list of role info from the database by given its
	 * role title
	 */
	@Override
	public List<Role> getRoleInfoByTitle(String roleTitle) {

		RoleDao rd = new RoleDao();
		List<Role> roles = rd.getRoles();
		ArrayList<Object> titles = new ArrayList<>();
		for (Role r : roles) {
			titles.add(r.getRole_title());
		}

		if (!titles.contains(roleTitle)) {
			log.error("The input role title doesn't exist in the database??");
			return new ArrayList<>();
		} else {
			try (Connection cn = ConnectionUtil.getConnection()) {
				String sql = "select * from \"UsingAWS\".roles where role_title = ?;";
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setString(1, roleTitle);
				ResultSet rs = ps.executeQuery();

				List<Role> roleList = new ArrayList<>();
				while (rs.next()) {
					Role r = new Role(rs.getInt("role_id"), rs.getString("role_title"), rs.getInt("role_salary"));
					roleList.add(r);
				}
				return roleList;
			} catch (SQLException e) {
				System.out.println("Getting the role info by title failed??");
				e.printStackTrace();
			}
			return new ArrayList<>();
		}
	}

	/**
	 * This method will change a salary for a certain role title in the database
	 */
	@Override
	public void updateRoleSalary(int salaryInput, String titleInput) {

		RoleDao rd = new RoleDao();
		List<Role> roles = rd.getRoles();
		ArrayList<Object> titles = new ArrayList<>();
		for (Role r : roles) {
			titles.add(r.getRole_title());
		}

		if (!titles.contains(titleInput)) {
			log.error("The input role title doesn't exist in the database??");
			return;
		} else {
			try (Connection cn = ConnectionUtil.getConnection()) {
				String sql = "update \"UsingAWS\".roles set role_salary = ? where role_title = ?;";
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setInt(1, salaryInput);
				ps.setString(2, titleInput);
				ps.executeUpdate();
				System.out.println(titleInput + " 's salary is changing to " + salaryInput);
			} catch (SQLException e) {
				System.out.println("Something went wrong, cannot update the salary for the role??");
				e.printStackTrace();
			}
		}
	}
}
