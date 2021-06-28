package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.business.Course;
import eu.ensup.gestionetablissement.dao.CourseDao;
import eu.ensup.gestionetablissement.dao.ExceptionDao;
import eu.ensup.gestionetablissement.dao.PersonDao;
import eu.ensup.gestionetablissement.dto.CourseDTO;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
import eu.ensup.gestionetablissement.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

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
        int i = service.create(new StudentDTO("james", "d@d.fr","12 rue du rue","021235","David","didi",new Date()));

        assertNotEquals(i, 0);

        PersonDTO person = service.get("d@d.fr");
        System.out.println("################## "+person.getId());
        service.delete(person.getId());
    }
}

