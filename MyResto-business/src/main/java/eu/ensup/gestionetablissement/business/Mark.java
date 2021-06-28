package eu.ensup.gestionetablissement.business;

/**
 * The type Course.
 */
public class Mark
{
    private int id;
    private int idStudent;
    private int idCourse;
    private float mark;
    private String assessment;
    
    public Mark() {}
    
	/**
	 * @param id
	 * @param idStudent
	 * @param idCourse
	 * @param assessment
	 * @param mark
	 */
	public Mark(int id, int idStudent, int idCourse, float mark, String assessment) {
		super();
		this.id = id;
		this.idStudent = idStudent;
		this.idCourse = idCourse;
		this.mark = mark;
		this.assessment = assessment;
	}
	
	public Mark(int id, int idStudent, int idCourse, float mark)
	{
		this(id, idStudent, idCourse, mark, null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public String getAssessment() {
		return assessment;
	}

	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}

	@Override
	public String toString() {
		return "Mark [id=" + id + ", idStudent=" + idStudent + ", idCourse=" + idCourse
				+ ", mark=" + mark + ", assessment=" + assessment + "]";
	}
}
