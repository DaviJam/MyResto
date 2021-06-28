package eu.ensup.gestionetablissement.business;

/**
 * The type School.
 */
public class School extends Entity
{
	private int id;
    private int director;

    /**
     * Instantiates a new School.
     *
     * @param id          the id
     * @param surname     the surname
     * @param mailAddress the mail address
     * @param address     the address
     * @param phoneNumber the phone number
     * @param director    the director
     */
    public School(int id, String surname, String mailAddress, String address, String phoneNumber, int director) {
        super(surname, mailAddress, address, phoneNumber);
        this.id = id;
        this.director = director;
    }

    /**
     * Instantiates a new School.
     *
     * @param surname     the surname
     * @param mailAddress the mail address
     * @param address     the address
     * @param phoneNumber the phone number
     * @param director    the director
     */
    public School(String surname, String mailAddress, String address, String phoneNumber, int director)
    {
        this(-1, surname, mailAddress, address, phoneNumber, director);
    }

    /**
     * Instantiates a new School.
     */
    public School()
    {
        this(-1, null, null, null, null, -1);
    }

    /**
     * Instantiates a new School.
     *
     * @param id          the id
     * @param surname     the surname
     * @param mailAddress the mail address
     * @param director    the director
     */
    public School(int id, String surname, String mailAddress, int director) {
        super(surname, mailAddress);
        this.id = id;
        this.director = director;
    }

    /**
     * Instantiates a new School.
     *
     * @param surname     the surname
     * @param mailAddress the mail address
     * @param director    the director
     */
    public School(String surname, String mailAddress, int director) {
        super(surname, mailAddress);
        this.id = -1;
        this.director = director;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() { return this.id; }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) { this.id = id; }

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
	public String toString()
	{
		String res = super.toString();
		res = res.replaceAll("Entity", "School");
		res = res.substring(0, res.length()-1);
		res = res + ", id=" + id;
		res = res + ", director=" + director + "]";

		return res;
	}
}
