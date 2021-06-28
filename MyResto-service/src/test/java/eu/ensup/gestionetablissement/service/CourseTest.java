package eu.ensup.gestionetablissement.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import eu.ensup.gestionetablissement.business.Course;
import eu.ensup.gestionetablissement.dao.CourseDao;
import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dto.CourseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class CourseTest
{
    @Spy
    CourseDao dao;

    @InjectMocks
    CourseService service;

    @Test
    public void getCourseSpyTestOk() throws ExceptionDao, ExceptionService {
        when(dao.get(81)).thenReturn(new Course("EPS", 4, 80));
        CourseDTO courseDto = service.get(81);
        assertEquals(courseDto.getId(), 80);
        Mockito.verify(dao, Mockito.atLeast(1)).get(81);
    }

    @Test
    public void getCourseTestOk() throws ExceptionDao, ExceptionService {

        CourseDTO courseDto = service.get(81);
        assertEquals(courseDto.getId(), 81);
        Mockito.verify(dao, Mockito.atLeast(1)).get(81);
    }
}

