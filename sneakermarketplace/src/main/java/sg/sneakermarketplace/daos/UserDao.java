/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.daos;

import java.util.List;
import sg.sneakermarketplace.models.Role;
import sg.sneakermarketplace.models.SiteUser;

/**
 *
 * @author Thomas
 */
public interface UserDao {
    SiteUser getUserById(int id);
    SiteUser getUserByUsername(String username);
    List<SiteUser> getAllUsers();
    void updateUser(SiteUser user);
    void deleteUser(int id);
    SiteUser createUser(SiteUser user);
    
    Role getRoleById(int id);
    Role getRoleByRole(String role);
    List<Role> getAllRoles();
    void deleteRole(int id);
    void updateRole(Role role);
    Role createRole(Role role);
}
