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
import org.springframework.beans.factory.annotation.Autowired;
import sg.sneakermarketplace.models.Role;
import sg.sneakermarketplace.models.SiteUser;

/**
 *
 * @author junho
 */
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
        
        SiteUser users = userDao.createUser(user);
        
        assertEquals("John", users.getFirstname());
        assertEquals("Doe", users.getLastname());
        assertEquals(LocalDate.of(1998,01,01), users.getDateofbirth());
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
