package ua.kpi.jakartaee.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import ua.kpi.jakartaee.repository.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named("userRepositoryInCodeImpl")
public class UserRepositoryInCodeImpl implements UserRepository {

    private final List<User> users = new ArrayList<>();

    public UserRepositoryInCodeImpl() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("users-db.json")) {
            if (inputStream != null) {
                List<User> usersFromFile = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
                users.addAll(usersFromFile);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    public Optional<User> findByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }
}
