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
import pl.coderslab.charity.Model.ConfirmationToken;
import pl.coderslab.charity.Model.User;
import pl.coderslab.charity.Object.UserDTO;
import pl.coderslab.charity.Service.ConfirmationTokenService;
import pl.coderslab.charity.Service.UserServiceImpl;

import javax.validation.Valid;

import java.time.LocalDateTime;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class LoginController {


    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final UserServiceImpl userService;
    private final ConfirmationTokenService confirmationTokenService;

    public LoginController(UserServiceImpl userService, ConfirmationTokenService confirmationTokenService) {
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
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
        ra.addFlashAttribute("message", "Email aktywujący konto został wysłany na adres podany podczas rejestracji");

        return "login";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam (required = false) String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Twoje hasło i/lub email są niepoprawne");

        if(logout != null)
            model.addAttribute("msg", "Zostałeś wylogowany");
        return "login";
    }


    @GetMapping("/activation/{token}")
    public String activation (@PathVariable String token, Model model){

        ConfirmationToken confirmationToken = confirmationTokenService.findByToken(token);
        if(confirmationToken == null){
            model.addAttribute("message", "Token jest niepoprawny");
        }else {
            User user = confirmationToken.getUser();

            if(!user.getEnabled()){
                if (confirmationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
                    model.addAttribute("message", "Token jest juz nieaktywny");
                }else{
                    user.setEnabled(true);
                    userService.saveUser(user);
                    model.addAttribute("message", "Twoje konto zostało aktywowane");
                }
            } else{
                model.addAttribute("message", "Twoje konto już jest aktywne");
            }
        }

        return "activation";
    }
}
