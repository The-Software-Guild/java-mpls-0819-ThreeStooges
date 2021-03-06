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
import sg.sneakermarketplace.models.ShoeCondition;

/**
 *
 * @author mac
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoeConditionDaoTest {

    @Autowired
    BidDao bidDao;

    @Autowired
    StatusDao statusDao;

    @Autowired
    ShoeModelDao modelDao;

    @Autowired
    BrandDao brandDao;

    @Autowired
    PrimaryColorDao pColorDao;

    @Autowired
    SecondaryColorDao sColorDao;

    @Autowired
    TypeDao typeDao;

    @Autowired
    ShoeConditionDao condDao;

    @Autowired
    SizeDao sizeDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ListingDao listingDao;

    public ShoeConditionDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        bidDao.deleteAll();
        statusDao.deleteAll();
        modelDao.deleteAll();
        brandDao.deleteAll();
        pColorDao.deleteAll();
        sColorDao.deleteAll();
        typeDao.deleteAll();
        condDao.deleteAll();
        sizeDao.deleteAll();
        listingDao.deleteAll();
    }

    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    public void testAddAndGetShoeConditionById() {
        ShoeCondition tCondition = new ShoeCondition();
        tCondition.setName("test new");
        tCondition = condDao.save(tCondition);
        int conId = tCondition.getId();
        
        ShoeCondition toTest = condDao.getOne(conId);
        
        assertEquals(tCondition, toTest);
        
    }

}
