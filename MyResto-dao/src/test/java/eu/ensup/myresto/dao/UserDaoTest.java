package eu.ensup.myresto.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.protobuf.Any;
import eu.ensup.myresto.business.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import eu.ensup.myresto.business.User;

/**
 * Unit test for simple App.
 */
public class UserDaoTest
{
    UserDao daoUser = new UserDao();
    User u =  null;

    @Before
    public void SetUp() throws ExceptionDao {
        Connect.openConnection();
        u = new User();
        u.setFirstname("Name_Test");
        u.setSurname("Surname_Test");
        u.setEmail("name-surname@test.fr");
        u.setRole(Role.CLIENT);
        u.setAddress("12 rue du test 91110");
        u.setPassword("12345678");
        daoUser.create(u);
    }

    @After
    public void TearDown() throws ExceptionDao {
        if(u != null) {
            daoUser.delete(u.getEmail());
        }
    }

    @Test
    @DisplayName("Check if user is in database")
    @Tag("UserDaoTest")
    public void checkUser() throws ExceptionDao {
        User u1 = daoUser.get("name-surname@test.fr");
        assertNotNull(u1);
    }


    @Test
    @DisplayName("get person and check is not nul")
    @Tag("UserDaoTest")
    public void personNotNull() throws ExceptionDao {
        User p = null;
        p = daoUser.get("name-surname@test.fr");
        assertNotNull(p);
        assertThat(p, notNullValue(User.class));
    }

    @Test
    @DisplayName("Person should remain null")
    @Tag("UserDaoTest")
    public void personIsNull() {
        Exception exception = assertThrows(ExceptionDao.class, () -> {
            daoUser.get("shiba"); //Fail
        });

        String expectedMessage = "Impossible de récupérer les informations de cette personne. Veuillez contacter votre administrateur.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("get person and check his first name is Surname_Test")
    @Tag("UserDaoTest")
    public void personNameEqual() throws ExceptionDao {
        User p = null;
        p = daoUser.get("name-surname@test.fr");
        assertEquals(p.getSurname(), "Surname_Test");
    }

    @Test
    @DisplayName("Should throw because not in database")
    @Tag("UserDaoTest")
    public void PersonNotExistInDatabase()
    {
        ExceptionDao exception = assertThrows(ExceptionDao.class , () -> daoUser.get("99999"));
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Impossible de récupérer les informations de cette personne."));
    }
//
//    @Test
//    @DisplayName("Courses should be equal")
//    @Tag("CourseDaoTest")
//    public void CompareCourseSame() throws ExceptionDao {
//        Course c = new Course();
//        c.setId(81);
//        c.setCourseSubject("EPS");
//        c.setNbHours(4.0f);
//
//        assertSame(c, c);
//    }
//
//    @Test
//    @DisplayName("Courses should be equal")
//    @Tag("CourseDaoTest")
//    public void CompareCourseNotSame() throws ExceptionDao {
//        Course c = new Course();
//        c.setId(81);
//        c.setCourseSubject("EPS");
//        c.setNbHours(2);
//
//        Course fetchedC= null;
//        fetchedC = daoCourse.get(81); //Success
//        assertNotNull(fetchedC);
//        assertNotSame(fetchedC, c);
//    }
//
////    @Test
////    @DisplayName("Get Course index")
////    @Tag("CourseDaoTest")
////    public void CompareCourseSameIndex() throws ExceptionDao {
////        int cIndex = daoCourse.getIndex("EPS", 4); //Success
////        assertThat(cIndex ,equalTo(81));
////    }
//
//    @Test
//    @DisplayName("Get Course index")
//    @Tag("CourseDaoTest")
//    public void UpdateAndCompareCourseSameNbHours() throws ExceptionDao {
//        Course c = new Course();
//        c.setId(81);
//        c.setCourseSubject("EPS");
//        c.setNbHours(2);
//
//        daoCourse.update(c); //Success
//        Course d = daoCourse.get(81);
//        assertThat(c.getNbHours() ,equalTo(d.getNbHours()));
//    }

}

/*
@org.junit.jupiter.api.Test
void exampleTest() {
    Assertions.assertTrue(trueBool);
    Assertions.assertFalse(falseBool);

    Assertions.assertNotNull(notNullString);
    Assertions.assertNull(notNullString);

    Assertions.assertNotSame(originalObject, otherObject);
    Assertions.assertEquals(4, 4);
    Assertions.assertNotEquals(3, 2);

    Assertions.assertArrayEquals(new int[]{1,2,3}, new int[]{1,2,3}, "Array Equal Test");

    Iterable<Integer> listOne = new ArrayList<>(Arrays.asList(1,2,3,4));
    Iterable<Integer> listTwo = new ArrayList<>(Arrays.asList(1,2,3,4));
    Assertions.assertIterableEquals(listOne, listTwo);

    Assertions.assertTimeout(Duration.ofMillis(100), () -> {
    Thread.sleep(50);
    return "result";
    });

    Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
    throw new IllegalArgumentException("error message");
    });

    Assertions.fail("not found good reason to pass");
}
 */