package ua.kpi.jakartaee.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ua.kpi.jakartaee.exceptions.PageNotFoundException;
import ua.kpi.jakartaee.exceptions.UserNotFoundException;
import ua.kpi.jakartaee.repository.UserRepository;
import ua.kpi.jakartaee.repository.model.User;

import java.util.List;

@ApplicationScoped
@Named("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Inject
    @Named("userRepositoryJSONImpl")
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) throws UserNotFoundException {
        if (username == null || username.isBlank()) {
            throw new UserNotFoundException();
        }
        username = username.substring(1);

        return userRepository
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public String getUserPage(String username) throws UserNotFoundException, PageNotFoundException {
        if (username == null || username.isBlank()) {
            throw new UserNotFoundException();
        }
        username = username.substring(1);

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        String page = user.getPage();

        if (page == null || page.isBlank()) {
            throw new PageNotFoundException("Page with name" + page + " was not found");
        }

        return "/WEB-INF/view/user-pages/" + page;
    }
}
