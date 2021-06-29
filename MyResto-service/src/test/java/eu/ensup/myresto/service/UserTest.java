package eu.ensup.myresto.service;

import eu.ensup.myresto.business.Role;
import eu.ensup.myresto.business.User;
import eu.ensup.myresto.dao.ExceptionDao;
import eu.ensup.myresto.dao.UserDao;
import eu.ensup.myresto.dto.UserDTO;
import eu.ensup.myresto.mapper.UserMapper;
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
public class UserTest
{
    @Spy
    UserDao dao;

    @InjectMocks
    UserService service;

    @Test
    public void CreateUserOk() throws ExceptionDao, ExceptionService {
        when(dao.get("d@d.f")).thenReturn(new User(80, "Root_Surname", "Root_Firstname", Role.CLIENT, "d@d.fr","123456", "12 rue du rue"));

        UserDTO userDto = service.get(81);
        assertEquals(userDto.getId(), 80);
        Mockito.verify(dao, Mockito.atLeast(1)).get(81);
    }
}

