package net.engineeringdigest.JournalApp.service;

import net.engineeringdigest.JournalApp.entity.User;
import net.engineeringdigest.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;

import static org.mockito.Mockito.*;


public class CustomUserDetailsServiceTest {
    // we autowired this so this will be null so give the @springboottest annotation on upper method
    @InjectMocks
    private CustomUserDetailsServiceIMPL userdetail;

    @Mock
    private UserRepository userrepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this); // means we are talking about
        // this class so intialize all mocks of this class
    }

   @Test
   void loadUserByUsernameTest(){
        when(userrepository.findByusername("sakshi")).thenReturn(User.builder().username("sakshi").password("disciplined").roles(Arrays.asList("USER")).build());
        UserDetails user = userdetail.loadUserByUsername("sakshi");
        Assertions.assertNotNull(user);
    }
}
