package eu.ensup.myresto.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import eu.ensup.myresto.business.Course;
import eu.ensup.myresto.dao.CourseDao;
import eu.ensup.myresto.dao.ExceptionDao;
import eu.ensup.myresto.dto.UserDTO;
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
        UserDTO courseDto = service.get(81);
        assertEquals(courseDto.getId(), 80);
        Mockito.verify(dao, Mockito.atLeast(1)).get(81);
    }

    @Test
    public void getCourseTestOk() throws ExceptionDao, ExceptionService {

        UserDTO courseDto = service.get(81);
        assertEquals(courseDto.getId(), 81);
        Mockito.verify(dao, Mockito.atLeast(1)).get(81);
    }
}

