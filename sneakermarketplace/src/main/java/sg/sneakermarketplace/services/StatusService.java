/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.sneakermarketplace.daos.StatusDao;
import sg.sneakermarketplace.models.Status;

/**
 *
 * @author Thomas
 */
@Service
public class StatusService {
    @Autowired
    StatusDao statusDao;
    
    public List<Status> getAllStatuses() {
        return statusDao.findAll();
    }
    
    public Status getStatusById(int id) {
        return statusDao.getOne(id);
    }
}
