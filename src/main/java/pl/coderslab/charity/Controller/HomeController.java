package pl.coderslab.charity.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.Model.Institution;
import pl.coderslab.charity.Service.DonationService;
import pl.coderslab.charity.Service.InstitutionService;

import java.util.List;

@Controller
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @GetMapping(value = "/")
    public String getAll(Model model){
        List<Institution> institutions = institutionService.listAll();
        Integer sum = donationService.sumUp();
        long count = donationService.countAllDonations();
        model.addAttribute("institutions", institutions);
        model.addAttribute("sum", sum);
        model.addAttribute("count", count);
        return "index";}
}
