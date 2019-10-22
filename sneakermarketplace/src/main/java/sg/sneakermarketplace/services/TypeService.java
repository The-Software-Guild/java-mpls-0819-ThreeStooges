/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sneakermarketplace.daos.TypeDao;
import sg.sneakermarketplace.models.Type;

/**
 *
 * @author Thomas
 */
@Service
public class TypeService {
    @Autowired
    TypeDao typeDao;
    
    public List<Type> getAllTypes() {
        return typeDao.findAll();
    }
}
