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

import eu.ensup.myresto.business.Role;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import eu.ensup.myresto.business.User;

/**
 * Unit test for simple App.
 */
public class DaoTest
{
    UserDao daoUser = new UserDao();

    @BeforeEach
    public void SetUp() throws ExceptionDao {
        Connect.openConnection();
    }

    @Test
    @DisplayName("Create a user and check if it exist")
    @Tag("UserDaoTest")
    public void createUser() throws ExceptionDao {
        User u =  new User();
        u.setFirstname("Name_Test");
        u.setSurname("Surname_Test");
        u.setEmail("name-surname@test.fr");
        u.setRole(Role.CLIENT);
        u.setAddress("12 rue du test 91110");
        u.setPassword("12345678");
        daoUser.create(u);
        User u1 = daoUser.get("name-surname@test.fr");
        assertNotNull(u1);
        daoUser.delete(u1.getId());
    }


    @Test
    @DisplayName("get person and check is not nul")
    @Tag("PersonDaoTest")
    public void personNotNull() throws ExceptionDao {
        User p = null;
        p = daoUser.get("r.dupont@gmail.com");
        assertNotNull(p);
        assertThat(p, notNullValue(User.class));
    }

//    @Test
//    @DisplayName("Person should remain null")
//    @Tag("PersonDaoTest")
//    public void personIsNull() {
//        Exception exception = assertThrows(ExceptionDao.class, () -> {
//            daoUser.get(0); //Fail
//        });
//
//        String expectedMessage = "Impossible de récupérer les informations de cette personne. Veuillez contacter votre administrateur.";
//        String actualMessage = exception.getMessage();
//        assertTrue(actualMessage.contains(expectedMessage));
//    }

//    @Test
//    @DisplayName("get person and check his first name is Moulin")
//    @Tag("PersonDaoTest")
//    public void personNameEqual() throws ExceptionDao {
//        Person p = null;
//        p = daoUser.get(121);
//        assertEquals(p.getLastname(), "Moulin");
//        assertThat(p.getId(), is(121));
//    }
//
//    @Test
//    @DisplayName("get person and check his first name is not Parcher")
//    @Tag("PersonDaoTest")
//    public void personNameNotEqual() throws ExceptionDao {
//        Person p = null;
//        p = daoUser.get(117);
//        assertNotEquals(p.getFirstname(), "Parcher");
//        assertThat(p.getId(), not(116));
//    }
//
//
//    @Test
//    @DisplayName("Person role should be greater than 3")
//    @Tag("PersonDaoTest")
//    public void personRoleIsGreaterThan3() throws ExceptionDao {
//        Person p = null;
//        p = daoUser.get(127); //Success
//        assertTrue(p.getRole().getNum() > 3);
//    }
//
//    @Test
//    @DisplayName("Person role should be less than 3")
//    @Tag("PersonDaoTest")
//    public void personRoleIsLessThan3() throws ExceptionDao {
//        Person p = null;
//        p = daoUser.get(7); //Success
//        assertFalse(p.getRole().getNum() > 3);
//    }
//
//    @Test
//    @DisplayName("Should throw because not in database")
//    @Tag("PersonDaoTest")
//    public void PersonNotExistInDatabase()
//    {
//        ExceptionDao exception = assertThrows(ExceptionDao.class , () -> daoUser.get(2));
//        assertNotNull(exception);
//        assertTrue(exception.getMessage().contains("Impossible de récupérer les informations de cette personne."));
//    }
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