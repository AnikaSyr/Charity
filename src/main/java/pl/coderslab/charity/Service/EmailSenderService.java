package pl.coderslab.charity.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.Model.User;

import javax.mail.MessagingException;

@Service
public class EmailSenderService {


    private final JavaMailSender mailSender;



    public EmailSenderService(JavaMailSender mailSender) {

        this.mailSender = mailSender;
    }

    public void sendEmail (User user) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("apkacharity@gmail.com");
        message.setTo(user.getEmail());
        message.setText("Dziękujemy za zarejestrowanie się. Aby zweryfikować swój adres email kliknij proszę w poniższy link \n" +
                "http://localhost:8080/activation/" + user.getEmail() + "/" + user.getConfirmationToken());
        message.setSubject("Zweryfikuj swój adres email");
        mailSender.send(message);
    }
}
