package pl.coderslab.charity.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.charity.Model.Category;
import pl.coderslab.charity.Model.Donation;
import pl.coderslab.charity.Model.Institution;
import pl.coderslab.charity.Service.CategoryService;
import pl.coderslab.charity.Service.DonationService;
import pl.coderslab.charity.Service.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("formDto")
public class DonationController {
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final Logger logger = LoggerFactory.getLogger(DonationController.class);

    public DonationController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
    }


    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String makeDonations(Model model) {
        List<Category> categories = categoryService.listAll();
        List<Institution> institutions = institutionService.listAll();

        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categories);
        model.addAttribute("institutions", institutions);
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String handle(@Valid Donation donation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("incorrect data");
            return "form";
        }

        donationService.save(donation);


        return "redirect:/form/confirmation";
    }

    @RequestMapping(value = "/form/confirmation", method = RequestMethod.GET)
    public String confirmation() {
        return "form_confirmation";
    }

}
