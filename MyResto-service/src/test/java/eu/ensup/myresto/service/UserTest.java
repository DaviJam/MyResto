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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserTest
{
    @Mock
    UserDao dao;

    @InjectMocks
    UserService service;

    @Test
    public void GetUserOk() throws ExceptionDao, ExceptionService {
        when(dao.get("d@d.f")).thenReturn(new User(80, "Root_Surname", "Root_Firstname", Role.CLIENT, "d@d.fr","123456", "12 rue du rue"));
        UserDTO userDto = service.get("d@d.f");
        assertEquals(userDto.getId(), 80);
        Mockito.verify(dao, Mockito.atLeast(1)).get("d@d.f");
    }

    @Test
    public void CreateUserOk() throws ExceptionDao, ExceptionService {
        UserDTO userDto = new UserDTO("Test_Surname", "Test_Firstname", Role.CLIENT, "d@d.fr", "12345678", "Welcome to my home");
        when(dao.create(any(User.class))).thenReturn(1);
        int ret = service.create(userDto);
        assertEquals(ret, 1);
        Mockito.verify(dao, Mockito.atLeast(1)).create(any(User.class));
    }

    @Test
    public void UpdateUserOk() throws ExceptionDao, ExceptionService {
        UserDTO userDto = new UserDTO("Test_Surname", "Test_Firstname", Role.CLIENT, "d@d.fr", "12345678", "Welcome to my home");
        when(dao.update(any(User.class))).thenReturn(1);
        int ret = service.update(userDto);
        assertEquals(ret, 1);
        Mockito.verify(dao, Mockito.atLeast(1)).update(any(User.class));
    }

    @Test
    public void DeleteUserOk() throws ExceptionDao, ExceptionService {
        //UserDTO userDto = new UserDTO("Test_Surname", "Test_Firstname", Role.CLIENT, "d@d.fr", "12345678", "Welcome to my home");
        when(dao.delete("d@d.f")).thenReturn(1);
        int ret = service.delete("d@d.f");
        assertEquals(ret, 1);
        Mockito.verify(dao, Mockito.atLeast(1)).delete("d@d.f");
    }

}

