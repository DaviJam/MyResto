package eu.ensup.myresto.service;

import eu.ensup.myresto.dao.UserDao;
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
    UserDao dao;

    @InjectMocks
    UserService service;

    //@Test
    /*public void CreatePersonOk() throws ExceptionDao, ExceptionService {
        int i = service.create(new OrderDTO("james", "d@d.fr","12 rue du rue","021235","David","didi",new Date()));

        assertNotEquals(i, 0);

        PersonDTO person = service.get("d@d.fr");
        System.out.println("################## "+person.getId());
        service.delete(person.getId());
    }*/
}

