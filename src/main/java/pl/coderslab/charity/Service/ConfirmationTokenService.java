package pl.coderslab.charity.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.Model.ConfirmationToken;
import pl.coderslab.charity.Model.User;
import pl.coderslab.charity.Repository.ConfirmationTokenRepository;



@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;


    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository, UserServiceImpl userService) {
        this.confirmationTokenRepository = confirmationTokenRepository;

    }

    @Transactional
    public ConfirmationToken findByToken (String token){
        return confirmationTokenRepository.findByConfirmationToken(token);
    }

    @Transactional
    public ConfirmationToken findByUser (User user) {
        return confirmationTokenRepository.findByUser(user);
    }

    @Transactional
    public void save (User user, String token) {
        ConfirmationToken confirmationToken = new ConfirmationToken(token, user);
        confirmationTokenRepository.save(confirmationToken);
    }

}
