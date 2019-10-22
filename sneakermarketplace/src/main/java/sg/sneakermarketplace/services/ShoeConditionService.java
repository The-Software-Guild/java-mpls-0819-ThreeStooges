/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sneakermarketplace.daos.ShoeConditionDao;
import sg.sneakermarketplace.models.ShoeCondition;

/**
 *
 * @author Thomas
 */
@Service
public class ShoeConditionService {
    @Autowired
    ShoeConditionDao shoeConditionDao;
    
    public List<ShoeCondition> getAllShoeConditions() {
        return shoeConditionDao.findAll();
    }
}
