/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.daos;

import javax.transaction.Transactional;
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
import sg.sneakermarketplace.models.Size;

/**
 *
 * @author Thomas
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SizeDaoTest {
    
    @Autowired
    SizeDao toTest;
    
    public SizeDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        toTest.deleteAll();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    public void testSaveAndFindByIdGoldenPath() {
        
        Size size = new Size();
        size.setShoeSize(15);
        
        size = toTest.save(size);
        
        Size fromDao = toTest.getOne(size.getShoeSize());
        assertEquals(size, fromDao);
        
    }
    
}
