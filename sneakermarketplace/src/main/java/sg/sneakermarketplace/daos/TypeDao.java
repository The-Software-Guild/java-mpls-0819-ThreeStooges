/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.daos;

import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.sneakermarketplace.models.ShoeModel;
import sg.sneakermarketplace.models.Type;

/**
 *
 * @author mac
 */
@Repository
@Profile({"production", "test"})
public interface TypeDao extends JpaRepository<Type, Integer>{
    //List<Type> findByModel(ShoeModel model);
}
