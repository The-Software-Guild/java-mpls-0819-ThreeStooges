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
import sg.sneakermarketplace.models.Brand;

/**
 *
 * @author Thomas
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandDaoTest {
    
    @Autowired
    BrandDao toTest;
    
    public BrandDaoTest() {
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
        
        Brand brand = new Brand();
        brand.setName("Test brand name");
        
        brand = toTest.save(brand);
        
        Brand fromDao = toTest.getOne(brand.getId());
        assertEquals(brand, fromDao);
        
    }
    
}
