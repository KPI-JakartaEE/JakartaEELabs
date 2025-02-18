package ua.kpi.jakartaee.service;


import ua.kpi.jakartaee.exceptions.PageNotFoundException;
import ua.kpi.jakartaee.exceptions.UserNotFoundException;

public interface UserService {
    String getUserPage(String username) throws PageNotFoundException, UserNotFoundException;
}
