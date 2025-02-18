package ua.kpi.jakartaee.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ua.kpi.jakartaee.exceptions.PageNotFoundException;
import ua.kpi.jakartaee.exceptions.UserNotFoundException;
import ua.kpi.jakartaee.repository.UserRepository;
import ua.kpi.jakartaee.repository.model.User;

@ApplicationScoped
@Named("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Inject
    @Named("userRepositoryInCodeImpl")
    private UserRepository userRepository;

    @Override
    public String getUserPage(String username) throws UserNotFoundException, PageNotFoundException {
        if (username == null || username.isBlank()) {
            throw new UserNotFoundException();
        }
        username = username.substring(1);

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        String jspPageName = user.getJspPageName();

        if (jspPageName == null || jspPageName.isBlank()) {
            throw new PageNotFoundException("Page with name" + jspPageName + " was not found");
        }

        return "/views/" + jspPageName + ".jsp";
    }
}
