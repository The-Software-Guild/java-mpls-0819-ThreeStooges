/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sneakermarketplace.daos.SizeDao;
import sg.sneakermarketplace.models.Size;

/**
 *
 * @author Thomas
 */
@Service
public class SizeService {
    
    @Autowired
    SizeDao sizeDao;

    public List<Size> getAllSizes() {
        return sizeDao.findAll();
    }
    
    
    
}
