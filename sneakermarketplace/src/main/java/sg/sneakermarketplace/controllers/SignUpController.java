/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.controllers;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author junho
 */
@Controller
public class SignUpController {
    
    @GetMapping("/profile")
    public String displayProfile(){
       
        return "CreateProfile";
    }
    
    
    @PostMapping("/createProfile")
    public String addUser(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("DOB"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");


        
        return "redirect:/profile";
    }
}
