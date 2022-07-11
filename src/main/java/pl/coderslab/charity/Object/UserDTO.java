package pl.coderslab.charity.Object;

import org.hibernate.validator.constraints.Length;
import pl.coderslab.charity.Model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

public class UserDTO {

    @NotBlank(message = "Wprowadź email")
    @Email(message = "Wprowadź poprawny adres email")
    private String email;
    @NotBlank(message = "Wprowadź hasło")
    @Length(min=8, max = 20, message = "Hasło musi zawierać od 8 do 20 znaków")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Hasło musi zawierać co najmniej jedną dużą literę, małą, cyfrę oraz znak specjalny")
    private String password;
    @NotBlank(message = "Wprowadź ponownie swoje hasło")
    private String rpassword;
    private boolean enabled;
    private Set<Role> roles;

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRpassword() {
        return rpassword;
    }

    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }

    public UserDTO(String email, String password, String rpassword, boolean enabled, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.rpassword = rpassword;
        this.enabled = enabled;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rpassword='" + rpassword + '\'' +
                '}';
    }
}
