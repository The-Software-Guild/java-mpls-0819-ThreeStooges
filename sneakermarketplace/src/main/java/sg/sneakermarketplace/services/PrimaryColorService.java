/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sneakermarketplace.daos.PrimaryColorDao;
import sg.sneakermarketplace.models.PrimaryColor;

/**
 *
 * @author Thomas
 */
@Service
public class PrimaryColorService {
    
    @Autowired
    PrimaryColorDao primaryColorDao;
    
    public List<PrimaryColor> getAllPrimaryColors() {
        return primaryColorDao.findAll();
    }
}
