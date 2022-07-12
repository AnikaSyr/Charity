package pl.coderslab.charity.Service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.Model.Donation;
import pl.coderslab.charity.Repository.DonationRepository;

@Service
public class DonationService {

    final DonationRepository donationRepository;


    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public Integer sumUp() {
        return donationRepository.sumUp();
    }

    public long countAllDonations() {
        return donationRepository.count();
    }

    public void save(Donation donation) {
        donationRepository.save(donation);
    }

}
