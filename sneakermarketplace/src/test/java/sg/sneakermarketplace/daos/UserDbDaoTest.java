/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.daos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sg.sneakermarketplace.models.Role;
import sg.sneakermarketplace.models.SiteUser;

/**
 *
 * @author junho
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDbDaoTest {

    @Autowired
    UserDbDao userDao;

    public UserDbDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        for (SiteUser user : userDao.getAllUsers()) {
            userDao.deleteUser(user.getId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getUserById method, of class UserDbDao.
     */
    @Test
    public void testGetUserById() {
    }

    /**
     * Test of getUserByUsername method, of class UserDbDao.
     */
    @Test
    public void testGetUserByUsername() {
    }

    /**
     * Test of getAllUsers method, of class UserDbDao.
     */
    @Test
    public void testGetAllUsers() {
    }

    /**
     * Test of updateUser method, of class UserDbDao.
     */
    @Test
    public void testUpdateUser() {
        SiteUser user = new SiteUser();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setDateofbirth(LocalDate.of(1998, 01, 01));
        user.setPhone("0000000000");
        user.setEmail("First@example.com");
        user.setUsername("OWO");
        user.setPassword("1234");
        Set<Role> buyer = new HashSet();
        buyer.add(userDao.getRoleById(2));
        user.setRoles(buyer);
        
        user = userDao.createUser(user);
        SiteUser fromDao = userDao.getUserById(user.getId());
        assertEquals(user, fromDao);
        
        user.setFirstname("Joseph");
        user.setLastname("Halpert");
        user.setDateofbirth(LocalDate.of(1998, 01, 01));
        user.setPhone("0000000000");
        user.setEmail("One@example.com");
        user.setUsername("UWU");
        user.setPassword("4321");
        
        userDao.updateUser(user);
        
        assertNotEquals(user, fromDao);
        
        fromDao = userDao.getUserById(user.getId());
        
        assertEquals(user, fromDao);

        
        
    }

    /**
     * Test of deleteUser method, of class UserDbDao.
     */
    @Test
    public void testDeleteUser() {
    }

    /**
     * Test of createUser method, of class UserDbDao.
     */
    @Test
    public void testCreateUser() {
        SiteUser user = new SiteUser();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setDateofbirth(LocalDate.of(1998, 01, 01));
        user.setPhone("0000000000");
        user.setEmail("First@example.com");
        user.setUsername("OWO");
        user.setPassword("1234");
        Set<Role> buyer = new HashSet();
        buyer.add(userDao.getRoleById(2));
        user.setRoles(buyer);

        user = userDao.createUser(user);
        SiteUser users = userDao.getUserById(user.getId());

        assertEquals("John", users.getFirstname());
        assertEquals("Doe", users.getLastname());
        assertEquals(LocalDate.of(1998, 01, 01), users.getDateofbirth());
        assertEquals("0000000000", users.getPhone());
        assertEquals("OWO", users.getUsername());
        assertEquals("1234", users.getPassword());
        assertEquals(buyer, users.getRoles());

    }

    /**
     * Test of getRoleById method, of class UserDbDao.
     */
    @Test
    public void testGetRoleById() {
    }

    /**
     * Test of getRoleByRole method, of class UserDbDao.
     */
    @Test
    public void testGetRoleByRole() {
    }

    /**
     * Test of getAllRoles method, of class UserDbDao.
     */
    @Test
    public void testGetAllRoles() {
    }

    /**
     * Test of deleteRole method, of class UserDbDao.
     */
    @Test
    public void testDeleteRole() {
    }

    /**
     * Test of updateRole method, of class UserDbDao.
     */
    @Test
    public void testUpdateRole() {

    }

    /**
     * Test of createRole method, of class UserDbDao.
     */
    @Test
    public void testCreateRole() {
    }

}
