package ua.kpi.jakartaee.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import ua.kpi.jakartaee.repository.model.User;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named("userRepositoryInCodeImpl")
public class UserRepositoryInCodeImpl implements UserRepository {
    private final List<User> users = List.of(
            User.builder().username("user1").jspPageName("user").build(),
            User.builder().username("user2").jspPageName("user").build(),
            User.builder().username("user3").jspPageName("user").build(),
            User.builder().username("user4").jspPageName("user").build(),
            User.builder().username("user5").jspPageName("user").build()
    );

    public Optional<User> findByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }
}
