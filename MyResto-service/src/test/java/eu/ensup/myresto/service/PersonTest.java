package eu.ensup.myresto.service;

import eu.ensup.myresto.business.Course;
import eu.ensup.myresto.dao.CourseDao;
import eu.ensup.myresto.dao.ExceptionDao;
import eu.ensup.myresto.dao.PersonDao;
import eu.ensup.myresto.dto.OrderDTO;
import eu.ensup.myresto.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonTest
{
    @Spy
    PersonDao dao;

    @InjectMocks
    PersonService service;

    @Test
    public void CreatePersonOk() throws ExceptionDao, ExceptionService {
        int i = service.create(new OrderDTO("james", "d@d.fr","12 rue du rue","021235","David","didi",new Date()));

        assertNotEquals(i, 0);

        PersonDTO person = service.get("d@d.fr");
        System.out.println("################## "+person.getId());
        service.delete(person.getId());
    }
}

