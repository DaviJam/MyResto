package eu.ensup.gestionetablissement.business;

/**
 * The type Course.
 */
public class Course {
    private String courseSubject;
    private float nbHours;
    private int id;

    /**
     * Gets course subject.
     *
     * @return the course subject
     */
    public String getCourseSubject() {
        return courseSubject;
    }

    /**
     * Sets course subject.
     *
     * @param courseSubject the course subject
     */
    public void setCourseSubject(String courseSubject) {
        this.courseSubject = courseSubject;
    }

    /**
     * Gets nb hours.
     *
     * @return the nb hours
     */
    public float getNbHours() {
        return nbHours;
    }

    /**
     * Sets nb hours.
     *
     * @param nbHours the nb hours
     */
    public void setNbHours(float nbHours) {
        this.nbHours = nbHours;
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
     * Instantiates a new Course.
     *
     * @param courseSubject the course subject
     * @param nbHours       the nb hours
     * @param id            the id
     */
    public Course(String courseSubject, float nbHours, int id) {
        this.courseSubject = courseSubject;
        this.nbHours = nbHours;
        this.id = id;
    }

    /**
     * Instantiates a new Course.
     */
    public Course() {
    	this(null, -1, -1);
    }

    /**
     * Instantiates a new Course.
     *
     * @param subject the subject
     * @param nbHours the nb hours
     */
    public Course(String subject, float nbHours) {
		this(subject, nbHours, -1);
	}

	@Override
    public String toString() {
        return "Course{" +
                "courseSubject='" + courseSubject + '\'' +
                ", nbHours=" + nbHours +
                ", id=" + id +
                '}';
    }


}
