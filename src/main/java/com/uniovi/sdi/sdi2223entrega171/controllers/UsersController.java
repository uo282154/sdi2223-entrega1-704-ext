package com.uniovi.sdi.sdi2223entrega171.controllers;

import com.uniovi.sdi.sdi2223entrega171.entities.Log;
import com.uniovi.sdi.sdi2223entrega171.entities.User;
import com.uniovi.sdi.sdi2223entrega171.services.*;
import com.uniovi.sdi.sdi2223entrega171.validators.SignUpFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private SignUpFormValidator signUpFormValidator;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UsersService usersService;

    @Autowired
    private LogService logService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result) {
        signUpFormValidator.validate(user,result);
        if(result.hasErrors()) {
            return "signup";
        }
        user.setRole(rolesService.getRoles()[0]);
        usersService.addUser(user);

        logger.info("New user signed up with email " + user.getEmail());
        String description = "New user signed up with email=" + user.getEmail() + "&name=" + user.getName() + "lastName=" +
                user.getSurname() + "&password=*****&passwordConfirm=*****";
        logService.addLog(Log.LogItemType.ALTA,  description);

        return "redirect:home";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Error al introducir las credenciales");
        }
        if (logout != null) {
            model.addAttribute("message", "Sesión cerrada correctamente");
        }
        return "/login";
    }
    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("user", userDetailsService.getActiveUser());
        return "home";
    }

    @RequestMapping("/user/list")
    public String getList(Model model) {
        List<User> allUsers = usersService.getUsers();
        List<User> users = new ArrayList<User>();
        List<User> admins = new ArrayList<User>();
        for(User u : allUsers) {
            if(!u.getRole().equals("ROLE_ADMIN")) {
                users.add(u);
            }
            else {
                admins.add(u);
            }
        }
        model.addAttribute("adminsList", admins);
        model.addAttribute("usersList", users);
        model.addAttribute("user", userDetailsService.getActiveUser());
        return "user/list";
    }
    @RequestMapping(value = "/user/add")
    public String getUser(Model model) {
        return "user/add";
    }
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String setUser(@ModelAttribute User user) {
        usersService.addUser(user);
        return "redirect:/user/list";
    }
    @RequestMapping("/user/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("user", usersService.getUser(id));
        model.addAttribute("user", userDetailsService.getActiveUser());
        return "user/details";
    }
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request) {
        for(String id : request.getParameterMap().keySet())
            usersService.deleteUser(Long.parseLong(id));
        return "redirect:/user/list";
    }
    @RequestMapping(value = "/user/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        User user = usersService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("user", userDetailsService.getActiveUser());
        return "user/edit";
    }
    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
    public String setEdit(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);
        usersService.updateUser(user);
        return "redirect:/user/details/" + id;
    }
}

