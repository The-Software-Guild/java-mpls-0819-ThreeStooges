/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.daos;

import java.math.BigDecimal;
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
import sg.sneakermarketplace.models.PrimaryColor;
import sg.sneakermarketplace.models.SecondaryColor;
import sg.sneakermarketplace.models.ShoeModel;

/**
 *
 * @author Thomas
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoeModelDaoTest {
    
    @Autowired
    BrandDao brandDao;
    
    @Autowired
    PrimaryColorDao primaryColorDao;
    
    @Autowired
    SecondaryColorDao secondaryColorDao;
    
    @Autowired
    ShoeModelDao toTest;
    
    public ShoeModelDaoTest() {
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
        
        ShoeModel sModel = new ShoeModel();
        sModel.setName("Test shoe model name");
        
        Brand brand = new Brand();
        brand.setName("Test brand name");
        brand = brandDao.save(brand);
        sModel.setBrand(brand);
        
        sModel.setReleaseYear(2019);
        
        PrimaryColor pColor = new PrimaryColor();
        pColor.setName("Test pColor Name");
        pColor = primaryColorDao.save(pColor);
        sModel.setPrimColor(pColor);
        
        SecondaryColor sColor = new SecondaryColor();
        sColor.setName("Test sColor name");
        sColor = secondaryColorDao.save(sColor);
        sModel.setSecondColor(sColor);
        
        sModel.setMsrpPrice(new BigDecimal("111.11"));
        
        sModel = toTest.save(sModel);
        
        ShoeModel fromDao = toTest.getOne(sModel.getId());
        assertEquals(sModel, fromDao);
        
    }
    
}
