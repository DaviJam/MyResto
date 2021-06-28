package eu.ensup.gestionetablissement.business;

/**
 * The type Director.
 * Is responsible
 */
public class Manager extends Person{
    /**
     * Instantiates a new Manager.
     *
     * @param lastname    the lastname
     * @param mailAddress the mail address
     * @param address     the address
     * @param phoneNumber the phone number
     * @param id          the id
     * @param firstname   the firstname
     * @param role        the role
     * @param password    the password
     */
    public Manager(String lastname, String mailAddress, String address, String phoneNumber, int id, String firstname, Role role, String password) {
        super(lastname, mailAddress, address, phoneNumber, id, firstname, role, password);
    }

    /**
     * Instantiates a new Manager.
     *
     * @param lastname    the lastname
     * @param mailAddress the mail address
     * @param address     the address
     * @param phoneNumber the phone number
     * @param firstname   the firstname
     * @param role        the role
     * @param password    the password
     */
    public Manager(String lastname, String mailAddress, String address, String phoneNumber, String firstname, Role role, String password) {
        super(lastname, mailAddress, address, phoneNumber, firstname, role, password);
    }

    /**
     * Instantiates a new Manager.
     *
     * @param lastname    the lastname
     * @param mailAddress the mail address
     * @param address     the address
     * @param phoneNumber the phone number
     * @param id          the id
     * @param firstname   the firstname
     * @param password    the password
     */
    public Manager(String lastname, String mailAddress, String address, String phoneNumber, int id, String firstname, String password) {
        super(lastname, mailAddress, address, phoneNumber, id, firstname, Role.MANAGER, password);
    }

    /**
     * Instantiates a new Manager.
     *
     * @param lastname    the lastname
     * @param mailAddress the mail address
     * @param address     the address
     * @param phoneNumber the phone number
     * @param firstname   the firstname
     * @param password    the password
     */
    public Manager(String lastname, String mailAddress, String address, String phoneNumber, String firstname, String password) {
        super(lastname, mailAddress, address, phoneNumber, firstname, Role.MANAGER, password);
    }

    /**
     * Instantiates a new Manager.
     *
     * @param lastname    the lastname
     * @param mailAddress the mail address
     * @param id          the id
     * @param firstname   the firstname
     * @param password    the password
     */
    public Manager(String lastname, String mailAddress, int id, String firstname, String password) {
        super(lastname, mailAddress, id, firstname, password);
    }

    /**
     * Instantiates a new Manager.
     */
    public Manager() {

    }

    @Override
	public String toString() {
		String res = super.toString();
		res = res.replace("Person", "Manager");

		return res;
	}
}
