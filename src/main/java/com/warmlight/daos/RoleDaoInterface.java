package com.warmlight.daos;

import java.util.List;

import com.warmlight.models.Role;

/**
 * This class will be implemented by the RoleDao class
 *
 */
public interface RoleDaoInterface {

	public List<Role> getRoles();

	public List<Role> getRoleInfoByTitle(String roleTitle);

	public void updateRoleSalary(int salaryInput, String titleInput);

}
