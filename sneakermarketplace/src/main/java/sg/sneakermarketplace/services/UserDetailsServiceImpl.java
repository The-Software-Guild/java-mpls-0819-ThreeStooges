/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sg.sneakermarketplace.daos.UserDao;
import sg.sneakermarketplace.models.SiteUser;

/**
 *
 * @author Thomas
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserDao dao;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
        SiteUser toLoad = dao.getUserByUsername(userName);
        
        Set<GrantedAuthority> convertedRoles = new HashSet<>();
        
        toLoad.getRoles().stream().forEach( r -> convertedRoles.add(new SimpleGrantedAuthority(r.getRole())));
        
        return new User(userName, toLoad.getPassword(), convertedRoles);
        
    }
    
    public SiteUser getUserByUsername(String userName) throws UsernameNotFoundException {
        SiteUser toReturn = dao.getUserByUsername(userName);
        return toReturn;
    }
    
    public SiteUser getUserById(int id) {
        SiteUser toReturn = dao.getUserById(id);
        return toReturn;
    }

    
}
