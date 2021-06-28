package eu.ensup.gestionetablissement.dto;

/**
 * The type Course.
 */
public class MarkDTO
{
    private int id;
    private int idStudent;
    private int idCourse;
    private float mark;
    private String assessment;
    
    public MarkDTO() {}
    
	/**
	 * @param idStudent
	 * @param idCourse
	 * @param assessment
	 * @param mark
	 */
	public MarkDTO( int idStudent, int idCourse, float mark, String assessment) {
		super();
		this.idStudent = idStudent;
		this.idCourse = idCourse;
		this.mark = mark;
		this.assessment = assessment;
	}
	
	public MarkDTO( int idStudent, int idCourse, float mark)
	{
		this(idStudent, idCourse, mark, null);
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
