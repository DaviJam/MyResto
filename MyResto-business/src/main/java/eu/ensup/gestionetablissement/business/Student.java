package eu.ensup.gestionetablissement.business;

import java.util.Date;

/**
 * The type Student.
 */
public class Student extends Person
{
	private Date dateOfBirth;
	private double average;

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public Student(String lastname, String mailAddress, String address, String phoneNumber, int id, String firstname, String password, Date dateOfBirth, double average) {
		super(lastname, mailAddress, address, phoneNumber, id, firstname, Role.STUDENT, password);
		this.dateOfBirth = dateOfBirth;
		this.average = average;
	}

	/**
	 * Instantiates a new Student.
	 */
	public Student(){ }

	/**
	 * Instantiates a new Student.
	 *
	 * @param lastname    the lastname
	 * @param mailAddress the mail address
	 * @param address     the address
	 * @param phoneNumber the phone number
	 * @param firstname   the firstname
	 * @param password    the password
	 * @param dateOfBirth the date of birth
	 */
	public Student(String lastname, String mailAddress, String address, String phoneNumber, String firstname, String password, Date dateOfBirth)
	{
		super(lastname, mailAddress, address, phoneNumber, firstname, Role.STUDENT, password);
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Instantiates a new Student
	 *
	 * @param lastname    the lastname
	 * @param mailAddress the mail address
	 * @param address     the address
	 * @param phoneNumber the phone number
	 * @param id          the id
	 * @param firstname   the firstname
	 * @param password    the password
	 * @param dateOfBirth the date of birth
	 */
	public Student(String lastname, String mailAddress, String address, String phoneNumber, int id, String firstname, String password, Date dateOfBirth)
	{
		super(lastname, mailAddress, address, phoneNumber, id, firstname, Role.STUDENT, password);
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Instantiates a new Student
	 *
	 * @param lastname    the lastname
	 * @param mailAddress the mail address
	 * @param id          the id
	 * @param firstname   the firstname
	 * @param password    the password
	 * @param dateOfBirth the date of birth
	 */
	public Student(String lastname, String mailAddress, int id, String firstname, String password, Date dateOfBirth)
	{
		super(lastname, mailAddress, id, firstname, password);
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Instantiates a new Student
	 *
	 * @param lastname    the lastname
	 * @param mailAddress the mail address
	 * @param id          the id
	 * @param firstname   the firstname
	 * @param password    the password
	 */
	public Student(String lastname, String mailAddress, int id, String firstname, String password)
	{
		this(lastname, mailAddress, id, firstname, password, null);
	}


	/**
	 * Gets date of birth.
	 *
	 * @return date of the student birth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets date of birth.
	 *
	 * @param dateOfBirth the date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		String res = super.toString();
		res = res.replace("Person", "Student");
		res = res.substring(0, res.length()-1);
		res = res + ", dateOfBirth=\" + dateOfBirth + \"]";

		return res;
	}
}