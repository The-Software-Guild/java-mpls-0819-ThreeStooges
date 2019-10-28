/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sg.sneakermarketplace.models.Role;
import sg.sneakermarketplace.models.SiteUser;

/**
 *
 * @author Thomas
 */
@Repository
@Profile({"production", "test"})
public class UserDbDao implements UserDao {

    @Autowired
    JdbcTemplate jdbc;

    private final String BASE_SELECT_USER = "SELECT u.id, firstname, lastname, dateofbirth, phone, email, address, moneybalance, u.username, u.password, u.enabled FROM users u ";

    private final String BASE_SELECT_ROLE = "SELECT r.id, r.role FROM roles r ";
    
    @Override
    public SiteUser getUserById(int id) {
        try {
            final String SELECT_USER_BY_ID = BASE_SELECT_USER + " WHERE u.id = ?";
            SiteUser user = jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), id);
            user.setRoles(getRolesForUser(user.getId()));
            return user;
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    @Override
    public SiteUser getUserByUsername(String username) {
        try {
            final String SELECT_USER_BY_USERNAME = BASE_SELECT_USER + " WHERE u.username = ?";
            SiteUser user = jdbc.queryForObject(SELECT_USER_BY_USERNAME, new UserMapper(), username);
            user.setRoles(getRolesForUser(user.getId()));
            return user;
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SiteUser> getAllUsers() {
        List<SiteUser> users = jdbc.query(BASE_SELECT_USER, new UserMapper());
        users.forEach((user) -> {
            user.setRoles(getRolesForUser(user.getId()));
        });
        return users;
    }

    @Override
    public void updateUser(SiteUser user) {
        final String UPDATE_USER = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ?, phone = ?, email = ?, address = ?, moneybalance = ?, username = ?, password = ?,enabled = ? WHERE id = ?";
        int deleteRowsAffected = jdbc.update(UPDATE_USER, user.getFirstname(), user.getLastname(), user.getDateofbirth(), user.getPhone(), user.getEmail(), user.getAddress(), user.getMoneybalance(), user.getUsername(), user.getPassword(), user.isEnabled(), user.getId());

        // TODO: check that rows affected is 1
        final String DELETE_USER_ROLE = "DELETE FROM users_roles WHERE user_id = ?";
        jdbc.update(DELETE_USER_ROLE, user.getId());
        final String INSERT_USER_ROLE = "INSERT INTO users_roles(user_id, role_id) VALUES(?,?)";
        user.getRoles().forEach((role) -> {
            int insertRowsAffected = jdbc.update(INSERT_USER_ROLE, user.getId(), role.getId());
            //TODO: check that insert rows affected is 1
        });
    }

    @Override
    public void deleteUser(int id) {
        final String DELETE_USER_ROLE = "DELETE FROM users_roles WHERE user_id = ?";
        final String DELETE_USER = "DELETE FROM users WHERE id = ?";
        jdbc.update(DELETE_USER_ROLE, id);
        int rowAffected = jdbc.update(DELETE_USER, id);
        //TODO: check that exactly one row is deleted
    }

    @Override
    @Transactional
    public SiteUser createUser(SiteUser user) {
        final String INSERT_USER = "INSERT INTO users(firstname, lastname, dateofbirth, phone, email, address, moneybalance, username, password, enabled) VALUES(?,?,?,?,?,?,?,?,?,?)";
        int rowsAffected = jdbc.update(INSERT_USER, user.getFirstname(), user.getLastname(), user.getDateofbirth(), user.getPhone(), user.getEmail(), user.getAddress(), user.getMoneybalance(), user.getUsername(), user.getPassword(), user.isEnabled());
        //TODO: check that only one row is inserted
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        user.setId(newId);
        
        final String INSERT_USER_ROLE = "INSERT INTO users_roles(user_id, role_id) VALUES(?,?)";
        user.getRoles().forEach((role) -> {
            int insertRowsAffected = jdbc.update(INSERT_USER_ROLE, user.getId(), role.getId());
            //TODO: check that only one row is inserted each time
        });
        return user;
    }

    private Set<Role> getRolesForUser(int id) {
        final String SELECT_ROLES_FOR_USER = "SELECT r.id, r.role FROM users_roles ur "
                + "JOIN roles r ON ur.role_id = r.id "
                + "WHERE ur.user_id = ?";
        Set<Role> roles = new HashSet(jdbc.query(SELECT_ROLES_FOR_USER, new RoleMapper(), id));
        return roles;
    }

    @Override
    public Role getRoleById(int id) {
        try {
            final String SELECT_ROLE_BY_ID = BASE_SELECT_ROLE + " WHERE r.id = ?";
            return jdbc.queryForObject(SELECT_ROLE_BY_ID, new RoleMapper(), id);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Role getRoleByRole(String role) {
        try {
            final String SELECT_ROLE_BY_ROLE = BASE_SELECT_ROLE + " WHERE r.role = ?";
            return jdbc.queryForObject(SELECT_ROLE_BY_ROLE, new RoleMapper(), role);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Role> getAllRoles() {
        return jdbc.query(BASE_SELECT_ROLE, new RoleMapper());
    }

    @Override
    public void deleteRole(int id) {
        final String DELETE_USER_ROLE = "DELETE FROM users_roles WHERE role_id = ?";      
        final String DELETE_ROLE = "DELETE FROM roles WHERE id = ?";
        jdbc.update(DELETE_USER_ROLE, id);
        int rowsAffected = jdbc.update(DELETE_ROLE, id);
        //TODO: check that exactly one row was deleted
    }

    @Override
    public void updateRole(Role role) {
        final String UPDATE_ROLE = "UPDATE roles SET role = ? WHERE id = ?";
        int rowsAffected = jdbc.update(UPDATE_ROLE, role.getRole(), role.getId());
        //TODO: check that exactly one row was affected
    }

    @Override
    @Transactional
    public Role createRole(Role role) {
        final String INSERT_ROLE = "INSERT INTO roles(role) VALUES(?)";
        int rowsAffected = jdbc.update(INSERT_ROLE, role.getRole());
        //TODO: check that exactly one row is inserted
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        role.setId(newId);
        return role;
    }

    public static final class UserMapper implements RowMapper<SiteUser> {

        @Override
        public SiteUser mapRow(ResultSet rs, int i) throws SQLException {
            SiteUser user = new SiteUser();
            user.setId(rs.getInt("id"));
            user.setFirstname(rs.getString("firstname"));
            user.setLastname(rs.getString("lastname"));
            user.setDateofbirth(rs.getDate("dateofbirth").toLocalDate());
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setAddress(rs.getString("address"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEnabled(rs.getBoolean("enabled"));
            user.setMoneybalance(rs.getBigDecimal("moneybalance"));
            //user.setMoneybalance(rs.getBigDecimal("moneybalance"));
            return user;
        }
    }
    
   public static final class RoleMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int i) throws SQLException {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setRole(rs.getString("role"));
            return role;
        }
    }

}
