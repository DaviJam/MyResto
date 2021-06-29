package eu.ensup.myresto.dao;

import eu.ensup.myresto.business.Category;
import eu.ensup.myresto.business.Product;
import eu.ensup.myresto.business.Role;
import eu.ensup.myresto.business.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginDaoTest {
    LoginDao loginDao = new LoginDao();
    UserDao userDao = new UserDao();
    User user = null;

    @Before
    public void SetUp() throws ExceptionDao {
        Connect.openConnection();
        user = new User("dada", "didi", Role.CLIENT, "d@d.fr", "123456", "rue du commerce");
        userDao.create(user);
    }

    @After
    public void TearDown() throws ExceptionDao {
        if(user != null) {
            userDao.delete(user.getEmail());
        }
    }

    @Test
    public void checkPassword() {

        try {
            int ret = loginDao.checkPassword(user.getEmail(), user.getPassword());
            assertThat(ret, greaterThanOrEqualTo(0));
        } catch(ExceptionDao e) {
            fail("Should not have thrown any exception");
        }
    }

}
