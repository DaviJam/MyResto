package eu.ensup.gestionetablissement.business;

/**
 * The type Director.
 */
public class Director extends Manager {
    /**
     * Instantiates a new Director.
     *
     * @param lastname    the lastname
     * @param mailAddress the mail address
     * @param address     the address
     * @param phoneNumber the phone number
     * @param id          the id
     * @param firstname   the firstname
     * @param password    the password
     */
    public Director(String lastname, String mailAddress, String address, String phoneNumber, int id, String firstname, String password) {
        super(lastname, mailAddress, address, phoneNumber, id, firstname, Role.DIRECTOR, password);
    }

    /**
     * Instantiates a new Director.
     *
     * @param lastname    the lastname
     * @param mailAddress the mail address
     * @param address     the address
     * @param phoneNumber the phone number
     * @param firstname   the firstname
     * @param password    the password
     */
    public Director(String lastname, String mailAddress, String address, String phoneNumber,  String firstname, String password) {
        super(lastname, mailAddress, address, phoneNumber, firstname, Role.DIRECTOR, password);
    }

    /**
     * Instantiates a new Director.
     */
    public Director() {

    }

    @Override
	public String toString() {
		String res = super.toString();
		res = res.replace("Manager", "Director");

		return res;
	}
}
