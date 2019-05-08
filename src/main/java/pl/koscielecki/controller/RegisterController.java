package pl.koscielecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import pl.koscielecki.repository.UserRepository;
import pl.koscielecki.service.UserService;
import pl.koscielecki.user.User;
import pl.koscielecki.validation.RegisterValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller

public class RegisterController {

    @Autowired
  UserService userService;
    @Autowired
    MessageSource messageSource;
@Autowired
    UserRepository userRepository;




    @GetMapping(value = "/register")
    public String registerForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, BindingResult result, Model model, Locale locale, HttpServletRequest request){
        String returnPage = null;

        User userExist = userRepository.findOneByEmail(user.getEmail());

        new RegisterValidator().validateEmailExist(userExist, result);

        new RegisterValidator().validate(user, result);

        if (result.hasErrors()) {
            return "register";

        }else{

            userService.saveUser(user);


        }
        return "redirect:"+request.getContextPath()+ "login";
    }
}
