package pl.coderslab.charity.Service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.Model.Role;
import pl.coderslab.charity.Model.User;
import pl.coderslab.charity.Object.UserDTO;
import pl.coderslab.charity.Repository.RoleRepository;
import pl.coderslab.charity.Repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    private EmailSenderService emailSenderService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.emailSenderService = emailSenderService;
    }


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public User register(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setEnabled(false);
        Role userRole = roleRepository.findByName("ROLE_USER");
        userDTO.setRoles(new HashSet<>(Arrays.asList(userRole)));
        User user = new User();
        modelMapper.map(userDTO, user);
        user.setConfirmationToken(UUID.randomUUID().toString());

        userRepository.save(user);

        try {
            emailSenderService.sendEmail(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


}
