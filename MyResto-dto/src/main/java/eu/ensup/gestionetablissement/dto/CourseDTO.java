package eu.ensup.gestionetablissement.dto;

/**
 * The type Course dto.
 */
public class CourseDTO {

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
     * Instantiates a new Course dto.
     */
    public CourseDTO() {
    }

    /**
     * Instantiates a new Course dto.
     *
     * @param courseSubject the course subject
     * @param nbHours       the nb hours
     * @param id            the id
     */
    public CourseDTO(String courseSubject, float nbHours, int id) {
        this.courseSubject = courseSubject;
        this.nbHours = nbHours;
        this.id = id;
    }

    /**
     * Instantiates a new Course dto.
     *
     * @param courseSubject the course subject
     * @param nbHours       the nb hours
     */
    public CourseDTO(String courseSubject, float nbHours) {
        this.courseSubject = courseSubject;
        this.nbHours = nbHours;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseSubject='" + courseSubject + '\'' +
                ", nbHours=" + nbHours +
                ", id=" + id +
                '}';
    }
}