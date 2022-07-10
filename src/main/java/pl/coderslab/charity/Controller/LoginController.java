package pl.coderslab.charity.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.Object.UserDTO;
import pl.coderslab.charity.Service.UserServiceImpl;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class LoginController {


    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final UserServiceImpl userService;

    public LoginController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }


    @GetMapping("/register")
    public String showSignUpForm(@ModelAttribute UserDTO userDTO, Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam (required = false) String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Twoje hasło i/lub email są niepoprawne");

        if(logout != null)
            model.addAttribute("msg", "Zostałeś wylogowany");
        return "login";
    }



    @RequestMapping(value = "/register", method = POST)
    public String handle(@Valid UserDTO userDTO, BindingResult bindingResult, RedirectAttributes ra) {

        if(userService.findUserByEmail(userDTO.getEmail())!=null){
            bindingResult.addError(new FieldError("userDTO", "email", "Ten email jest już zarejestrowany" ));
        }
        if(userDTO.getPassword() != null && userDTO.getRpassword() != null){
            if (!userDTO.getPassword().equals(userDTO.getRpassword())){
                bindingResult.addError(new FieldError("userDTO", "rpassword", "Hasła muszą być takie same" ));

            }
        }
        if (bindingResult.hasErrors()) {
            logger.error("incorrect data");
            return "register";
        }

       userService.register(userDTO);
        ra.addFlashAttribute("message", "The user has been saved successfully");

        return "login";
    }
}
