package eu.ensup.gestionetablissement.mapper;

import eu.ensup.gestionetablissement.business.Student;
import eu.ensup.gestionetablissement.dto.StudentDTO;

/**
 * The type Student mapper.
 */
public class StudentMapper {
    /**
     * Business to dto student dto.
     *
     * @param student the student
     * @return the student dto
     */
    public static StudentDTO businessToDto(Student student)
    {
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId(student.getId());
        studentDTO.setLastname(student.getLastname());
        studentDTO.setFirstname(student.getFirstname());
        studentDTO.setDateOfBirth(student.getDateOfBirth());
        studentDTO.setAddress(student.getAddress());
        studentDTO.setMailAddress(student.getMailAddress());
        studentDTO.setPassword(student.getPassword());
        studentDTO.setPhoneNumber(student.getPhoneNumber());
        studentDTO.setRole(student.getRole());
        studentDTO.setAverage(student.getAverage());


        return studentDTO;
    };

    /**
     * Dto to business student.
     *
     * @param studentDTO the student dto
     * @return the student
     */
    public static Student dtoToBusiness(StudentDTO studentDTO)
    {
        Student student = new Student();

        student.setId(studentDTO.getId());
        student.setLastname(studentDTO.getLastname());
        student.setFirstname(studentDTO.getFirstname());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setAddress(studentDTO.getAddress());
        student.setMailAddress(studentDTO.getMailAddress());
        student.setPassword(studentDTO.getPassword());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setRole(studentDTO.getRole());
        student.setAverage(studentDTO.getAverage());

        return student;
    };
}
