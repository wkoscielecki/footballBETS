package pl.koscielecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.koscielecki.repository.UserRepository;

import pl.koscielecki.service.UserService;
import pl.koscielecki.user.UService;
import pl.koscielecki.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository)
    {this.userRepository=userRepository;}

    @Autowired
    UserService userService;




    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("user", new User());
        return "user/form";
    }

    @PostMapping("/form")
    public String form(@Valid User user, BindingResult err,
                       HttpServletRequest request,
                       Model model){
        if(err.hasErrors()){
            model.addAttribute("user",user);
            return "user/form";
        }
        userService.saveUser(user);
        return "redirect:"+request.getContextPath()+"/user/list";
    }


    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }


    @RequestMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable Long id) {
        model.addAttribute(userRepository.findOneById(id));
        return "user/form";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@Valid User user, BindingResult errors, @PathVariable Long id, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return "user/form";
        }
        userService.saveUser(user);
        return "redirect:" + request.getContextPath() + "/user/list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, HttpServletRequest request) {
        userRepository.delete(userRepository.findOneById(id));
        return "redirect:" + request.getContextPath() + "/user/list";
    }


}
