/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.controllers;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sg.sneakermarketplace.daos.UserDao;
import sg.sneakermarketplace.models.Role;
import sg.sneakermarketplace.models.SiteUser;
import sg.sneakermarketplace.services.UserDetailsServiceImpl;

/**
 *
 * @author junho
 */
@Controller
public class SignUpController {

    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserDao userDao;

    @GetMapping("/createProfile")
    public String displayProfile() {

        return "createProfile";
    }

    @PostMapping("/createProfile")
    public String addUser(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("DOB"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        Role roleB = new Role();
        roleB.setId(2);
        roleB.setRole("Buyer");
        Role roleS = new Role();
        roleS.setId(3);
        roleS.setRole("Seller");
        Set<Role> roles = new HashSet();
        roles.add(roleB);
        roles.add(roleS);

        SiteUser newUser = new SiteUser();
        newUser.setFirstname(firstName);
        newUser.setLastname(lastName);
        newUser.setDateofbirth(dateOfBirth);
        newUser.setPhone(phone);
        newUser.setEmail(email);
        newUser.setAddress(address);
        newUser.setMoneybalance(new BigDecimal("0.00"));
        newUser.setUsername(userName);
        newUser.setPassword(encoder.encode(password));
        newUser.setRoles(roles);
        newUser.setEnabled(true);

        newUser = userService.createUser(newUser);

        return "redirect:/dashboard";
    }

    @GetMapping("/editProfile")
    public String displayEditProfile(Principal pUser, Model model) {
        SiteUser user = userService.getUserByUsername(pUser.getName());
 
        model.addAttribute("user", user);
        return "editProfile";
    }

    @PostMapping("/editProfile")
    public String editUser(HttpServletRequest request , Principal user) {
        SiteUser currentUser = userService.getUserByUsername(user.getName());
        
        currentUser.setFirstname(request.getParameter("firstName"));
        currentUser.setLastname(request.getParameter("lastName"));
        currentUser.setDateofbirth(LocalDate.parse(request.getParameter("DOB")));
        currentUser.setPhone(request.getParameter("phone"));
        currentUser.setEmail(request.getParameter("email"));
        currentUser.setAddress(request.getParameter("address"));
        currentUser.setPassword(encoder.encode(request.getParameter("password")));

        userDao.updateUser(currentUser);
        return "redirect:/dashboard";
    }

}
