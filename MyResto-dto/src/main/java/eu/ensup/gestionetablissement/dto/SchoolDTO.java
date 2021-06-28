package eu.ensup.gestionetablissement.dto;

/**
 * The type School.
 */
public class SchoolDTO
{
	private int id;
	private String surname;
	private String mailAddress;
	private String address;
	private String phoneNumber;
    private int director;

	/**
	 * Instantiates a new School dto.
	 *
	 * @param id          the id
	 * @param surname     the surname
	 * @param mailAddress the mail address
	 * @param address     the address
	 * @param phoneNumber the phone number
	 * @param director    the director
	 */
	public SchoolDTO(int id, String surname, String mailAddress, String address, String phoneNumber, int director)
	{
		super();
		this.id = id;
		this.surname = surname;
		this.mailAddress = mailAddress;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.director = director;
	}

	/**
	 * Instantiates a new School dto.
	 *
	 * @param surname     the surname
	 * @param mailAddress the mail address
	 * @param address     the address
	 * @param phoneNumber the phone number
	 * @param director    the director
	 */
	public SchoolDTO(String surname, String mailAddress, String address, String phoneNumber, int director)
	{
		this(-1, surname, mailAddress, address, phoneNumber, director);
	}

	/**
	 * Instantiates a new School dto.
	 */
	public SchoolDTO()
	{
		this(-1, null, null, null, null, -1);
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets surname.
	 *
	 * @param surname the surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets mail address.
	 *
	 * @return the mail address
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * Sets mail address.
	 *
	 * @param mailAddress the mail address
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * Gets address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets address.
	 *
	 * @param address the address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets phone number.
	 *
	 * @param phoneNumber the phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets director.
	 *
	 * @return the director
	 */
	public int getDirector() {
		return director;
	}

	/**
	 * Sets director.
	 *
	 * @param director the director
	 */
	public void setDirector(int director) {
		this.director = director;
	}
	
	@Override
	public String toString() {
		return "SchoolDTO [id=" + id + ", surname=" + surname + ", mailAddress=" + mailAddress + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", director=" + director + "]";
	}
}
