package ua.kpi.jakartaee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.kpi.jakartaee.exceptions.PageNotFoundException;
import ua.kpi.jakartaee.exceptions.UserNotFoundException;
import ua.kpi.jakartaee.repository.UserRepository;
import ua.kpi.jakartaee.repository.model.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .username("JohnDoe")
                .page("john-doe.jsp")
                .build();
    }


    @Test
    void shouldReturnAllUsers() {
        List<User> usersToReturn = List.of(user);
        when(userRepository.findAll()).thenReturn(usersToReturn);

        var actual = userService.getAllUsers();

        assertThat(actual).isNotNull();
        assertThat(actual).hasSize(1);
        assertThat(actual.get(0)).isEqualTo(user);

        verify(userRepository).findAll();
    }

    @Test
    void shouldReturnUserPage() throws UserNotFoundException, PageNotFoundException {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        var actual = userService.getUserPage("/JohnDoe");

        String expected = "/WEB-INF/view/user-pages/john-doe.jsp";
        assertThat(actual).isEqualTo(expected);

        verify(userRepository).findByUsername("JohnDoe");
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUsernameIsNullOrBlank() {
        var actual = catchThrowable(() -> userService.getUserPage(null));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(UserNotFoundException.class);
        verify(userRepository, times(0)).findByUsername(anyString());

        actual = catchThrowable(() -> userService.getUserPage(""));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(UserNotFoundException.class);
        verify(userRepository, times(0)).findByUsername(anyString());
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserNotPresentInRepository() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        var actual = catchThrowable(() -> userService.getUserPage("/JohnSmith"));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(UserNotFoundException.class);
        verify(userRepository).findByUsername("JohnSmith");
    }

    @Test
    void shouldThrowPageNotFoundExceptionWhenPageIsNullOrBlank() {
        var mockUser = mock(User.class);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));
        when(mockUser.getPage()).thenReturn(null);

        var actual = catchThrowable(() -> userService.getUserPage("/JohnSmith"));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(PageNotFoundException.class);
        verify(userRepository).findByUsername("JohnSmith");
        verify(mockUser).getPage();


        when(mockUser.getPage()).thenReturn("");

        actual = catchThrowable(() -> userService.getUserPage("/JohnSmith"));

        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(PageNotFoundException.class);

        verify(userRepository, times(2)).findByUsername("JohnSmith");
        verify(mockUser, times(2)).getPage();
    }

}