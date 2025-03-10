package ua.kpi.jakartaee.service;


import ua.kpi.jakartaee.exceptions.PageNotFoundException;
import ua.kpi.jakartaee.exceptions.UserNotFoundException;
import ua.kpi.jakartaee.repository.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(String username) throws UserNotFoundException;
    String getUserPage(String username) throws PageNotFoundException, UserNotFoundException;
}
