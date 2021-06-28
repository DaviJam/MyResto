package eu.ensup.gestionetablissement.mapper;

import eu.ensup.gestionetablissement.business.Course;
import eu.ensup.gestionetablissement.dto.CourseDTO;

/**
 * The type Course mapper.
 */
public class CourseMapper
{
    /**
     * Business to dto course dto.
     *
     * @param course the course
     * @return the course dto
     */
    public static CourseDTO businessToDto(Course course){

        CourseDTO newCourseDto = new CourseDTO();

        newCourseDto.setId(course.getId());
        newCourseDto.setCourseSubject(course.getCourseSubject());
        newCourseDto.setNbHours(course.getNbHours());

        return newCourseDto;
    };

    /**
     * Dto to business course.
     *
     * @param coursedto the coursedto
     * @return the course
     */
    public static Course dtoToBusiness(CourseDTO coursedto){

        Course newCourse = new Course();

        newCourse.setId(coursedto.getId());
        newCourse.setCourseSubject(coursedto.getCourseSubject());
        newCourse.setNbHours(coursedto.getNbHours());

        return newCourse;
    };
}
