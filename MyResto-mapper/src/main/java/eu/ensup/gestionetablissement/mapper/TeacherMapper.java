package eu.ensup.gestionetablissement.mapper;

import eu.ensup.gestionetablissement.business.Teacher;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.TeacherDTO;

/**
 * The type Teacher mapper.
 */
public class TeacherMapper extends PersonDTO {

    /**
     * Business to dto teacher dto.
     *
     * @param teacher the teacher
     * @return the teacher dto
     */
    public static TeacherDTO businessToDto(Teacher teacher){

        TeacherDTO teacherDto = new TeacherDTO();

        teacherDto.setId(teacher.getId());
        teacherDto.setLastname(teacher.getLastname());
        teacherDto.setFirstname(teacher.getFirstname());
        teacherDto.setAddress(teacher.getAddress());
        teacherDto.setMailAddress(teacher.getMailAddress());
        teacherDto.setPassword(teacher.getPassword());
        teacherDto.setPhoneNumber(teacher.getPhoneNumber());
        teacherDto.setRole(teacher.getRole());
        teacherDto.setSubjectTaught(teacher.getSubjectTaught());

        return teacherDto;
    };

    /**
     * Dto to business teacher.
     *
     * @param teacherDTO the teacher dto
     * @return the teacher
     */
    public static Teacher dtoToBusiness(TeacherDTO teacherDTO)
    {
        Teacher teacher = new Teacher();

        teacher.setId(teacherDTO.getId());
        teacher.setLastname(teacherDTO.getLastname());
        teacher.setFirstname(teacherDTO.getFirstname());
        teacher.setAddress(teacherDTO.getAddress());
        teacher.setMailAddress(teacherDTO.getMailAddress());
        teacher.setPassword(teacherDTO.getPassword());
        teacher.setPhoneNumber(teacherDTO.getPhoneNumber());
        teacher.setRole(teacherDTO.getRole());

        return teacher;
    };
}
