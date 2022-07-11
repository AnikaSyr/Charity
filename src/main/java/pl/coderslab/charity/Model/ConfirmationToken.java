package pl.coderslab.charity.Model;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String confirmationToken;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private LocalDateTime expiryDate;

    @PrePersist
    public void setExpiryDate() {
        this.expiryDate = LocalDateTime.now().plusDays(1);
    }

    public ConfirmationToken() {
    }

    public ConfirmationToken(String confirmationToken, User user) {
        this.confirmationToken = confirmationToken;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
