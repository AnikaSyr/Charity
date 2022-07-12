package pl.coderslab.charity.Service;

import pl.coderslab.charity.Model.User;


public interface UserService {

    User findUserByEmail(String email);

    User saveUser(User user);
}
