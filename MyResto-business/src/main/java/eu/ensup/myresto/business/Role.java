package eu.ensup.myresto.business;

import java.util.ArrayList;
import java.util.List;

/**
 * The enum Role.
 */
public enum Role
{
	/**
	 * Director role.
	 */
	RESTORER (1, "Restorer"),
	/**
	 * Manager role.
	 */
	CLIENT  (2, "Client");

	private int    numRole;
	private String name;

	private Role(int numRole, String name)
	{
		this.numRole = numRole;
		this.name = name;
	}

	/**
	 * Gets num.
	 *
	 * @return the num
	 */
	public int    getNum()  { return this.numRole; }

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() { return this.name; }

	/**
	 * Gets role by name.
	 *
	 * @param name the name
	 * @return the role by name
	 */
	public Role getRoleByName(String name)
	{
		switch(name)
		{
			case "Restorer": return this.RESTORER;
			default: return this.CLIENT;
		}
	}

	/**
	 * Gets role by num.
	 *
	 * @param num the num
	 * @return the role by num
	 */
	static public Role getRoleByNum(int num)
	{
		switch(num)
		{
			case 1: return RESTORER;
			default: return CLIENT;
		}
	}

	/**
	 * Gets all roles.
	 *
	 * @return the all roles
	 */
	public List<Role> getAllRoles()
	{
		List<Role> lRole = new ArrayList<Role>();

		lRole.add(this.RESTORER);
		lRole.add(this.CLIENT);

		return lRole;
	}
}
