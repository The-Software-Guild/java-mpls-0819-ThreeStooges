/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.daos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sg.sneakermarketplace.models.Brand;
import sg.sneakermarketplace.models.Listing;
import sg.sneakermarketplace.models.PrimaryColor;
import sg.sneakermarketplace.models.Purchase;
import sg.sneakermarketplace.models.SecondaryColor;
import sg.sneakermarketplace.models.ShoeCondition;
import sg.sneakermarketplace.models.ShoeModel;
import sg.sneakermarketplace.models.SiteUser;
import sg.sneakermarketplace.models.Size;
import sg.sneakermarketplace.models.Status;
import sg.sneakermarketplace.models.Type;

/**
 *
 * @author junho
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseDaoTest {
    
    @Autowired
    PurchaseDao toTest;
    
    @Autowired
    ListingDao listingdao;
    
    @Autowired
    BrandDao brandDao;
    
    @Autowired
    SizeDao sizeDao;
    
    @Autowired
    StatusDao statusDao;
    
    @Autowired
    PrimaryColorDao pColorDao;
    
    @Autowired
    SecondaryColorDao sColorDao;
    
    @Autowired
    TypeDao typeDao;
    
    @Autowired
    ShoeConditionDao condDao;
    
    @Autowired
    ShoeModelDao modelDao;
            
    @Autowired
    UserDao userDao;
    
    public PurchaseDaoTest() {
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
    public void testPurchaseGoldenPath() {
        
        Purchase purchase = new Purchase();
        
        Listing tListing = new Listing();
        tListing.setBuyNowPrice(new BigDecimal ("119.00"));
         Status tStatus = new Status();
        tStatus.setName("active");
        tStatus = statusDao.save(tStatus);
        
        tListing.setStatus(tStatus);
        
        Brand tBrand = new Brand();
        tBrand.setName("test brand");
        tBrand = brandDao.save(tBrand);
        
        ShoeModel tModel = new ShoeModel();
        tModel.setBrand(tBrand);
        tModel.setName("test model");
        tModel.setReleaseYear(2019);
        
        PrimaryColor tPColor = new PrimaryColor();
        tPColor.setName("test primary color");
        tPColor = pColorDao.save(tPColor);
        
        tModel.setPrimColor(tPColor);
        
        SecondaryColor tSColor = new SecondaryColor();
        tSColor.setName("test secondary color");
        tSColor = sColorDao.save(tSColor);
        
        tModel.setSecondColor(tSColor);
        
        tModel.setMsrpPrice(new BigDecimal("125.00"));
        
        Type type1 = new Type();
        type1.setName("mens");
        type1 = typeDao.save(type1);
        
        Type type2 = new Type();
        type2.setName("women");
        type2 = typeDao.save(type2);
        
        Set<Type> allTypes = new HashSet<Type>();
        allTypes.add(type1);
        allTypes.add(type2);
        
        tModel.setTypes(allTypes);
        
        tModel = modelDao.save(tModel);
        
        tListing.setModel(tModel);
        
        ShoeCondition tCondition = new ShoeCondition();
        tCondition.setName("test new");
        tCondition = condDao.save(tCondition);
        
        tListing.setShoeCondition(tCondition);
        
        Size tSize = new Size();
        tSize.setShoeSize(10);
        tSize = sizeDao.save(tSize);
        
        tListing.setSize(tSize);
        
        tListing.setDescription("Test description");
        tListing.setBuyNowPrice(new BigDecimal("100.00"));
        tListing.setMinStartingPrice(new BigDecimal("90.00"));
        tListing.setSeller(userDao.getUserById(3));
        tListing.setListDate(LocalDate.of(2019, 10, 17));
        tListing.setEndDate(LocalDate.of(2019, 11, 15));
        tListing = listingdao.save(tListing);
        purchase.setListing(tListing);
        
        purchase.setSalePrice(new BigDecimal ("119.00"));
        
        SiteUser sUser = userDao.getUserById(3);
        SiteUser bUser = userDao.getUserById(2);
        
        purchase.setSeller(sUser);
        
        purchase.setBuyer(bUser);
       
        purchase.setDateSold(LocalDate.of(2020, 12, 23));
        
        purchase = toTest.save(purchase);
        
        Purchase fromDao = toTest.findById(purchase.getId()).get();
        
        assertEquals(purchase, fromDao);
        
    }
    
}
